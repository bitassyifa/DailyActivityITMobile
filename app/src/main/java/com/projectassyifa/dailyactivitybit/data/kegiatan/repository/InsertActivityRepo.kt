package com.projectassyifa.dailyactivitybit.data.kegiatan.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.projectassyifa.dailyactivitybit.data.kegiatan.api.AddActivityAPI
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.InsertActivityModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Part
import java.io.File
import javax.inject.Inject

class InsertActivityRepo @Inject constructor(val addActivityAPI: AddActivityAPI) {
    var activityResponse = MutableLiveData<ResponseAPI>()

    fun addActivity(data:InsertActivityModel,file: File){

        val user_klien = convert(data.user_klien)
        val aktifitas = convert(data.aktifitas)
        val created_by = convert(data.created_by)
        val foto1 = convertFile(file)

        addActivityAPI.addActivity(user_klien, aktifitas, created_by, foto1).enqueue(object : Callback<ResponseAPI> {
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                val res = response.body()
                println("MASUK REPO ADD ${res}")
                res?.status
                val stringResponse = Gson().toJson(res)
                val dataKegiatanContent = Gson().fromJson<ResponseAPI>(stringResponse,ResponseAPI::class.java)
                activityResponse.value =dataKegiatanContent
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                t.printStackTrace()
                println("ADD KEGIATAN GAGAL")
            }


        })
    }

    private fun convert (string :String ) : RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(),string)
    }

    private fun convertFile(file:File): MultipartBody.Part{
        val reqFile : RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(),file)
        return MultipartBody.Part.createFormData("foto1",file.name,reqFile)
    }
}


