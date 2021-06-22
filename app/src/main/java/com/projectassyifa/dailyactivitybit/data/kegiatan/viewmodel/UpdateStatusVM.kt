package com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel

import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.repository.UpdateStatusRepo
import java.io.File
import javax.inject.Inject

class UpdateStatusVM @Inject constructor(var updateStatusRepo: UpdateStatusRepo) {
    var updateStatus = updateStatusRepo.updateStatus

    fun updateStatus(id : String , dataActivityModel: DataActivityModel,file : File){

        updateStatusRepo.updateStatus(id,dataActivityModel,file)
    }
}