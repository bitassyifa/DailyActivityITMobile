package com.projectassyifa.dailyactivitybit.data.login.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.projectassyifa.dailyactivitybit.data.login.api.UserLoginAPI
import com.projectassyifa.dailyactivitybit.data.login.model.UserLoginModel
import com.projectassyifa.dailyactivitybit.data.login.model.UserLoginResponseModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class UserLoginRepo @Inject constructor( val userLoginApi : UserLoginAPI) {
    var userLogin = MutableLiveData<ResponseAPI>()
    var userLoginResponse = MutableLiveData<UserLoginResponseModel>()


    fun loginUser(userLoginModel: UserLoginModel,context: Context){
        println("USER LOGIN ${userLoginModel.username},${userLoginModel.password}")
        userLoginApi.loginUser(userLoginModel).enqueue(object : Callback<ResponseAPI> {
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                println("USER LOGIN repo ${userLoginModel.username},${userLoginModel.password}")
                println("RESPONSE BODY ${response.body()}")
                println("RESPONSE code ${response.code()}")
                if (response.code()==200){
                    val res = response.body()
                    val resData = res?.data
                    val gson = Gson()
                    val listOfMyClassObject: Type = object : TypeToken<List<UserLoginResponseModel>>() {}.type
                    val outputList: List<UserLoginResponseModel> = gson.fromJson(gson.toJson(resData), listOfMyClassObject)


                    println("ini outputlist")
                    println(outputList)


                    userLogin.value = res
                    userLoginResponse.value = outputList[0]

                    println("masuk respone nih")
                    println(userLogin.value)
                    Toast.makeText(context,"Login Success ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context,"Login Failed", Toast.LENGTH_SHORT).show()
                    println("USER NOT FOUND")
                }
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                t.printStackTrace()
                print("MASUK ONFAILURE")
            }

        })
    }
}