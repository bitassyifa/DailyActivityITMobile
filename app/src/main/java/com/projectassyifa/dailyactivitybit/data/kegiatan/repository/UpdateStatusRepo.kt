package com.projectassyifa.dailyactivitybit.data.kegiatan.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.projectassyifa.dailyactivitybit.data.kegiatan.api.UpdateStatusAPI
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UpdateStatusRepo @Inject constructor(val updateStatusAPI: UpdateStatusAPI) {
    var updateStatus = MutableLiveData<ResponseAPI>()

    fun updateStatus(id : String , dataActivityModel: DataActivityModel){
        updateStatusAPI.updateStatus(id,dataActivityModel).enqueue(object : Callback<ResponseAPI> {
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                val res = response?.body()
                val responData = Gson().toJson(res)
                val updateStatusRes = Gson().fromJson<ResponseAPI>(responData,ResponseAPI::class.java)
                updateStatus.value = updateStatusRes
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                println(t.localizedMessage)
                println("gagal")
            }

        })
    }
}