package com.projectassyifa.dailyactivitybit.data.kegiatan.api

import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface UpdateStatusAPI {
    @Multipart
    @POST("rest-api/api/ams/updateStatus/{id}")
    fun updateStatus(@Path("id") id: String,
                     @Part("status") status: RequestBody,
                     @Part("proses_keterangan") proses_keterangan: RequestBody,
                     @Part proses_bukti1: MultipartBody.Part? = null,
                     @Part("solved_keterangan") solved_keterangan: RequestBody,
                     @Part solved_bukti1: MultipartBody.Part? = null
    ) : Call<ResponseAPI>
}