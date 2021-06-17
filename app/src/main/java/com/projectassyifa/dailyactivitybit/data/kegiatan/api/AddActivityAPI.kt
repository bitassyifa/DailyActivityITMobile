package com.projectassyifa.dailyactivitybit.data.kegiatan.api


import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AddActivityAPI {
    @Multipart
    @POST("rest-api/api/ams/activity")
    fun addActivity(@Part("user_klien") user_klien: RequestBody,
                    @Part("aktifitas") aktifitas : RequestBody,
                    @Part("created_by") created_by : RequestBody,
                    @Part foto1:MultipartBody.Part? = null
    ): Call<ResponseAPI>

}