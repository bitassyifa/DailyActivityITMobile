package com.projectassyifa.dailyactivitybit.data.user.viewmodel

import androidx.lifecycle.MutableLiveData
import com.projectassyifa.dailyactivitybit.data.user.model.UserClientModel
import com.projectassyifa.dailyactivitybit.data.user.repository.UserClientRepository
import javax.inject.Inject

class UserClientVM @Inject constructor(val userClientRepository: UserClientRepository){
    val dataEmployee : MutableLiveData<List<UserClientModel>>? = userClientRepository.dataEmployee

    fun employee(){
        userClientRepository.employee()
    }
}