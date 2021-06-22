package com.projectassyifa.dailyactivitybit.screen.kegiatan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.activity.HomeActivity
import com.projectassyifa.dailyactivitybit.container.MyApplication
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel.UpdateStatusVM
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*
import kotlinx.android.synthetic.main.activity_update_status.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import javax.inject.Inject

class UpdateStatusActivity : AppCompatActivity() {
    var dataLogin : SharedPreferences? = null
    var choice : String? = null

    @Inject
    lateinit var updateStatusVM: UpdateStatusVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_status)
        dataLogin = getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
        var bundle = intent.extras

        aktivitas.text = bundle?.getString("aktifitas")
        (applicationContext as MyApplication).applicationComponent.inject(this)
        pilih_status.setOnCheckedChangeListener { group, checkedId ->
            var rb: RadioButton = findViewById<RadioButton>(checkedId)
            if (rb != null) {
                choice = rb.text.toString()
                if (choice == "PROCESS") {
                    wadahProses.isVisible = true
                    wadahSolved.isVisible = false
                } else if (choice == "SOLVED") {
                    wadahProses.isVisible = false
                    wadahSolved.isVisible = true
                }
            }
        }
        chose_file.setOnClickListener {
            EasyImage.openCamera(this, 1)
        }

        if (choice == null) {
            wadahProses.isVisible = false
            wadahSolved.isVisible = false
        }

        btn_simpan.setOnClickListener {
            val data = DataActivityModel()
            data.status = choice.toString()
            data.proses_keterangan = proses_ket.text.toString()
            data.solved_keterangan = solved_ket.text.toString()
            if (fileImage == null) {
                Toast.makeText(
                    this,
                    "Tidak ada file yang di pilih!!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (choice == "PROCESS") {
                    data.solved_keterangan = ""
                    println("${data.status},${data.proses_keterangan},${data.solved_keterangan},${fileImage!!.name}")
                    updateStatusVM.updateStatus(bundle?.getString("id").toString(),data,
                        fileImage!!
                    )

                    val move = Intent(this,HomeActivity::class.java)
                    startActivity(move)
                } else if (choice == "SOLVED") {
                    data.proses_keterangan =""
                    println("${data.status},${data.proses_keterangan},${data.solved_keterangan},${fileImage!!.name}")
                    updateStatusVM.updateStatus(bundle?.getString("id").toString(),data,
                        fileImage!!
                    )
                    val move = Intent(this,HomeActivity::class.java)
                    startActivity(move)
                }
                println("SAVE")


            }

        }
    }
    var fileImage : File?= null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode,resultCode,data,this,object : DefaultCallback(){
            var selectedImage = data?.data
            override fun onImagePicked(
                imageFile: File?,
                source: EasyImage.ImageSource?,
                type: Int
            ) {
                fileImage = imageFile
                var namaImage =imageFile?.name
                println("HASIL IMAGE $fileImage")
                println("NAMA IMAGE $namaImage")
                nama_file1.setText(imageFile?.name)
                val requestOptions = RequestOptions().error(R.drawable.upload)
                Glide.with(this@UpdateStatusActivity)
                    .load(imageFile)
                    .apply(requestOptions)
                    .into(image_upload1)
            }

        });
    }
}