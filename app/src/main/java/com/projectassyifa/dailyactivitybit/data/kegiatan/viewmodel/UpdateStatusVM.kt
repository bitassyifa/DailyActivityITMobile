package com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel

import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.repository.UpdateStatusRepo
import javax.inject.Inject

class UpdateStatusVM @Inject constructor(var updateStatusRepo: UpdateStatusRepo) {
    var updateStatus = updateStatusRepo.updateStatus
    fun updateStatus(id : String , dataActivityModel: DataActivityModel){
        updateStatusRepo.updateStatus(id,dataActivityModel)
    }
}