package com.projectassyifa.dailyactivitybit.data.user.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projectassyifa.dailyactivitybit.data.user.api.UserClientAPI
import com.projectassyifa.dailyactivitybit.data.user.model.UserClientModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class UserClientRepository @Inject constructor(val userClientAPI: UserClientAPI) {

   var dataEmployee : MutableLiveData<List<UserClientModel>> = MutableLiveData()

    fun employee(){
        userClientAPI.employee().enqueue(object : Callback<ResponseAPI> {
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                val responData = response.body()
                val res = responData?.data
                if (res == null) {
                    println("nama file null")
                } else {
                    val gson = Gson()
                    val listEmployee : Type = object : TypeToken<List<UserClientModel>?>() {}.type
                    val employeeOutputList : List<UserClientModel> = gson.fromJson(gson.toJson(res),listEmployee)

                    dataEmployee.value = employeeOutputList

                }
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                println(t.localizedMessage)
                println("gagal")
            }


        })
    }

}