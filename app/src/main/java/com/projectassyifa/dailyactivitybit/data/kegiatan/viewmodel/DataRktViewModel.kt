package com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel

import androidx.lifecycle.MutableLiveData
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataRktModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.repository.DataRktRepo
import javax.inject.Inject

class DataRktViewModel @Inject constructor(var dataRktRepo: DataRktRepo){
    val dataRkt : MutableLiveData<List<DataRktModel>>? = dataRktRepo.dataRkt
    fun activityRKT(username : String){
        dataRktRepo.activityRKT(username)
        println("USERNAME VM $username")
    }
}