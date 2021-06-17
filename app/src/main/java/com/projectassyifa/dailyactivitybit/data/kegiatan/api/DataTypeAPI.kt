package com.projectassyifa.dailyactivitybit.data.kegiatan.api

import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataTypeAPI {
    @GET("rest-api/api/ams/type_kegiatan/{id_data_activity}")
    fun typeAct(@Path("id_data_activity") id_data_activity:String): Call<ResponseAPI>
}