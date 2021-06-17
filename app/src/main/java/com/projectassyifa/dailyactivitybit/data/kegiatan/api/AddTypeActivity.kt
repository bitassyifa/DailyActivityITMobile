package com.projectassyifa.dailyactivitybit.data.kegiatan.api

import com.projectassyifa.dailyactivitybit.data.kegiatan.model.TypeActivityModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface AddTypeActivity {
    @POST("rest-api/api/ams/type_kegiatan/{id_data_activity}")
    fun addTypeAct(@Path("id_data_activity")id_data_activity : String,
            @Body typeActivityModel : TypeActivityModel) : Call<ResponseAPI>
}