package com.projectassyifa.dailyactivitybit.data.kegiatan.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.projectassyifa.dailyactivitybit.data.kegiatan.api.UpdateStatusAPI
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.utils.ResponseAPI
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class UpdateStatusRepo @Inject constructor(val updateStatusAPI: UpdateStatusAPI) {
    var updateStatus = MutableLiveData<ResponseAPI>()

    fun updateStatus(id : String , dataActivityModel: DataActivityModel,file : File){

        val id = id
        val status = convert(dataActivityModel.status)
        val solved_keterangan = convert(dataActivityModel.solved_keterangan)
        val proses_keterangan = convert(dataActivityModel.proses_keterangan)

        if (dataActivityModel.status == "PROCESS"){

            val proses_bukti1 = convertFileProses(file)

            updateStatusAPI.updateStatus(id,status,proses_keterangan,proses_bukti1,solved_keterangan ,null).enqueue(object  :Callback<ResponseAPI>{
                override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                    val res = response.body()
                    println("MASUK REPO UPDATE STTUS ${response}")
                    res?.status
                    val stringResponse = Gson().toJson(res)
                    val updateStatusContent = Gson().fromJson<ResponseAPI>(stringResponse,ResponseAPI::class.java)
                    updateStatus.value=updateStatusContent
                }

                override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                    t.printStackTrace()
                    println("UPDATE STATUS GAGAL")
                }

            })
        } else if (dataActivityModel.status == "SOLVED"){

            val solved_bukti1 = convertFileSolved(file)
            updateStatusAPI.updateStatus(id,status,proses_keterangan,null,solved_keterangan,solved_bukti1).enqueue(object :Callback<ResponseAPI>{
                override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                    val res = response.body()
                    println("MASUK REPO UPDATE STTUS ${response}")
                    res?.status
                    val stringResponse = Gson().toJson(res)
                    val updateStatusContent = Gson().fromJson<ResponseAPI>(stringResponse,ResponseAPI::class.java)
                    updateStatus.value=updateStatusContent
                }

                override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                    t.printStackTrace()
                    println("UPDATE STATUS GAGAL")
                }

            })
        }

    }
    private fun convert(string: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(),string)
    }
    private fun convertFileProses(file:File): MultipartBody.Part{
        val reqFile : RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(),file)
        return MultipartBody.Part.createFormData("proses_bukti1",file.name,reqFile)
    }
    private fun convertFileSolved(file:File): MultipartBody.Part{
        val reqFile : RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(),file)
        return MultipartBody.Part.createFormData("solved_bukti1",file.name,reqFile)
    }
}