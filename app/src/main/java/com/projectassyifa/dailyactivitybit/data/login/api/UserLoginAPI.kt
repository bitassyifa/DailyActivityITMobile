package com.projectassyifa.dailyactivitybit.data.login.api

import com.projectassyifa.dailyactivitybit.data.login.model.UserLoginModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserLoginAPI {
    @POST("hrd/api/")
    fun loginUser(@Body userLoginModel : UserLoginModel):Call<ResponseAPI>
}