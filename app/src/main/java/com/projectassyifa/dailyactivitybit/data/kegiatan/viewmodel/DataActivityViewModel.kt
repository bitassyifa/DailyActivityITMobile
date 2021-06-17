package com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel

import androidx.lifecycle.MutableLiveData
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.repository.DataActivityRepo
import javax.inject.Inject

class DataActivityViewModel  @Inject constructor(var dataActivityRepo: DataActivityRepo){
    val dataActivity : MutableLiveData<List<DataActivityModel>>? = dataActivityRepo.dataActivity
    fun activityIT(username : String){
        dataActivityRepo.activityIT(username)
        println("USERNAME VM $username")
    }
}