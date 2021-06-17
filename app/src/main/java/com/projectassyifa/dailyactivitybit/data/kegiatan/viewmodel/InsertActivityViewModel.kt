package com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel

import androidx.lifecycle.ViewModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.InsertActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.repository.InsertActivityRepo
import java.io.File
import javax.inject.Inject

class InsertActivityViewModel @Inject constructor(var insertActivityRepo: InsertActivityRepo) : ViewModel(){

    fun addActivity(insertActivityModel : InsertActivityModel,file:File){
        insertActivityRepo.addActivity(insertActivityModel,file)
    }
}