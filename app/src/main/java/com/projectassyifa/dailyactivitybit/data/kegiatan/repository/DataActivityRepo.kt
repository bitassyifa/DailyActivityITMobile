package com.projectassyifa.dailyactivitybit.data.kegiatan.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projectassyifa.dailyactivitybit.data.kegiatan.api.DataActivityAPI
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class DataActivityRepo @Inject constructor(val dataActivityAPI: DataActivityAPI) {
    var dataActivity : MutableLiveData<List<DataActivityModel>> = MutableLiveData()

    fun activityIT(username : String) {
        dataActivityAPI.activityIT(username).enqueue(object : Callback<ResponseAPI> {
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                val responData = response.body()
                val res = responData?.data
                if (res == null) {
                    println("NAMA FILE NULL")
                } else {
                    val gson = Gson()
                    val listKegiatan: Type = object : TypeToken<List<DataActivityModel>?>() {}.type
                    val kegiatanOutputList: List<DataActivityModel> =
                            gson.fromJson(gson.toJson(res), listKegiatan)
                    dataActivity.value = kegiatanOutputList

                    val responBody : ResponseBody? = null


                    responBody?.close()
                    println("reponse $res")
                    println("output $kegiatanOutputList")
                }
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                println(t.localizedMessage)
                println("gagal")
            }

        })
    }
}