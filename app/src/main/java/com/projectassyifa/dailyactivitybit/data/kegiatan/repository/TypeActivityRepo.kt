package com.projectassyifa.dailyactivitybit.data.kegiatan.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.projectassyifa.dailyactivitybit.data.kegiatan.api.AddActivityAPI
import com.projectassyifa.dailyactivitybit.data.kegiatan.api.AddTypeActivity
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.TypeActivityModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TypeActivityRepo @Inject constructor(val addTypeActivityAPI: AddTypeActivity) {

    var addType = MutableLiveData<ResponseAPI>()

    fun addTypeAct(id_data_activity: String,typeActivityModel: TypeActivityModel){
        addTypeActivityAPI.addTypeAct(id_data_activity,typeActivityModel).enqueue(object : Callback<ResponseAPI> {
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                val res = response?.body()
                val ResponseData = Gson().toJson(res)
                val TypeActivityResponse = Gson().fromJson<ResponseAPI>(ResponseData,ResponseAPI::class.java)
                addType.value = TypeActivityResponse
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                println(t.localizedMessage)
                println("gagal")
            }

        })
    }
}