package com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel

import androidx.lifecycle.MutableLiveData
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.TypeActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.repository.DataTypeRepo
import javax.inject.Inject

class DataTypeVM @Inject constructor(var dataTypeRepo: DataTypeRepo) {
    val dataType : MutableLiveData<List<TypeActivityModel>>? = dataTypeRepo.dataType

    fun typeAct(id_data_activity : String){
        dataTypeRepo.typeAct(id_data_activity)
    }
}