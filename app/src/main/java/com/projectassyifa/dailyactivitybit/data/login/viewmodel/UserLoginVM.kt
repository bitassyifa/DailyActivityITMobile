package com.projectassyifa.dailyactivitybit.data.login.viewmodel

import android.content.Context
import com.projectassyifa.dailyactivitybit.data.login.model.UserLoginModel
import com.projectassyifa.dailyactivitybit.data.login.repository.UserLoginRepo
import javax.inject.Inject

class UserLoginVM  @Inject constructor(var userLoginRepo: UserLoginRepo){
    var userLogin = userLoginRepo.userLogin
    var userLoginResponse = userLoginRepo.userLoginResponse

    fun loginUser(userLoginModel: UserLoginModel, context: Context){
        userLoginRepo.loginUser(userLoginModel,context)
        println("DATA VM ${userLoginModel.username},${userLoginModel.password}")
    }
}