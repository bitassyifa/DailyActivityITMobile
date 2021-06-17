package com.projectassyifa.dailyactivitybit.data.kegiatan.api

import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataActivityAPI {
    @GET("rest-api/api/ams/activity/{username}")
    fun activityIT(@Path("username")username : String ) :Call<ResponseAPI>
}