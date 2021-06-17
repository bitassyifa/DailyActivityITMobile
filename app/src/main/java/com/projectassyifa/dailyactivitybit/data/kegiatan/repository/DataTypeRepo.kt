package com.projectassyifa.dailyactivitybit.data.kegiatan.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projectassyifa.dailyactivitybit.data.kegiatan.api.DataTypeAPI
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataRktModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.TypeActivityModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class DataTypeRepo @Inject constructor(val dataTypeAPI: DataTypeAPI) {

    var dataType : MutableLiveData<List<TypeActivityModel>> = MutableLiveData()

    fun typeAct(id_data_activity : String){
        dataTypeAPI.typeAct(id_data_activity).enqueue(object : Callback<ResponseAPI> {
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                val responData = response.body()
                val res = responData?.data
                if (res == null) {
                    println("NAMA FILE NULL")
                } else {
                    val gson = Gson()
                    val listKegiatan: Type = object : TypeToken<List<TypeActivityModel>?>() {}.type
                    val kegiatanOutputList: List<TypeActivityModel> =
                            gson.fromJson(gson.toJson(res), listKegiatan)
                   dataType.value = kegiatanOutputList

                    val responBody : ResponseBody? = null


                    responBody?.close()
                    println("reponse $res")
                    println("output $kegiatanOutputList")
                }
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                println(t.localizedMessage)
                println("server downn")
            }


        })
    }
}