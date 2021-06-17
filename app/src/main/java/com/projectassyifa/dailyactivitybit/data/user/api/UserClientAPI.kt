package com.projectassyifa.dailyactivitybit.data.user.api

import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserClientAPI {
    @GET("rest-api/api/ams/employee")
    fun employee() : Call<ResponseAPI>
}