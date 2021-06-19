package com.projectassyifa.dailyactivitybit.data.kegiatan.api

import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface UpdateStatusAPI {

    @POST("rest-api/api/ams/updateStatus/{id}")
    fun updateStatus(@Path("id") id : String ,
    @Body dataActivityModel: DataActivityModel) : Call<ResponseAPI>
}