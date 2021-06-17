package com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel

import com.projectassyifa.dailyactivitybit.data.kegiatan.model.TypeActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.repository.TypeActivityRepo
import javax.inject.Inject

class AddTypeActivityVM @Inject constructor(var typeActivityRepo: TypeActivityRepo) {

    var addType = typeActivityRepo.addType
    fun addtypeAct(id_data_activity : String,typeActivityModel: TypeActivityModel){
        typeActivityRepo.addTypeAct(id_data_activity,typeActivityModel)
    }

}