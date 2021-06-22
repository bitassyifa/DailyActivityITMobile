package com.projectassyifa.dailyactivitybit.screen.kegiatan

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.os.Handler
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.activity.HomeActivity
import com.projectassyifa.dailyactivitybit.container.MyApplication
import com.projectassyifa.dailyactivitybit.data.kegiatan.adapter.AdapterActivityRKT
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataRktModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.InsertActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel.DataActivityViewModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel.DataRktViewModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel.InsertActivityViewModel
import com.projectassyifa.dailyactivitybit.data.user.adapter.AdapterListEmployee
import com.projectassyifa.dailyactivitybit.data.user.model.UserClientModel
import com.projectassyifa.dailyactivitybit.data.user.viewmodel.UserClientVM
import com.toptoche.searchablespinnerlibrary.SearchableListDialog
import com.zues.searchable_spinner.SearchableSpinner
import kotlinx.android.synthetic.main.activity_insert_daily.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import javax.inject.Inject

class InsertDailyActivity : AppCompatActivity() {

    var selectClient : String? = null
    var dataLogin : SharedPreferences? = null

    var posisi : Int?  = 0
    private val cameraRequestId  = 1222
   @Inject
   lateinit var dataRktViewModel: DataRktViewModel
   lateinit var adapterActivityRKT: AdapterActivityRKT

   @Inject
   lateinit var userClientVM: UserClientVM
   lateinit var adapterListEmployee: AdapterListEmployee

   @Inject
   lateinit var insertActivityViewModel: InsertActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_daily)

        (applicationContext as MyApplication).applicationComponent.inject(this)


        dataLogin = getSharedPreferences(
                getString(R.string.shared_preference_name),
                Context.MODE_PRIVATE
        )

        // rkt spinner
//        dataRktViewModel.dataRkt?.observe(this, Observer {
//           adapterActivityRKT = AdapterActivityRKT(this,it)
//            kegiatan_rkt.adapter = adapterActivityRKT
//        })

        val username = dataLogin?.getString(
                getString(R.string.username),
                getString(R.string.default_value)
        )
        dataRktViewModel.activityRKT(username.toString())

        // user client
        userClientVM.dataEmployee?.observe(this, Observer {
            adapterListEmployee = AdapterListEmployee(it,this)
            println("IT NIH $it")
//       user_client.setItems(listOf(adapterListEmployee.dataSource.get(6).nama_pegawai))
//            user_client.adapter = adapterListEmployee
//            val employes = listOf(adapterListEmployee)


            var jml = adapterListEmployee.dataSource
            var tampung : ArrayList<String> = ArrayList()
                jml.forEach {
//                    println("NAMA PEGAWAI : ${it.nama_pegawai}")
                    tampung.add(it.nama_pegawai)

//                    println("TAMPUNG $tampung")
                    user_client.setItems(tampung)
                }
//            println("TAMPUNG $tampung")

//       user_client.setItems(employeeList())
            user_client.setOnItemSelectListener(object : SearchableSpinner.SearchableItemListener {
                override fun onItemSelected(view: View?, position: Int) {
                    showToast("Selected")
                    selectClient = it.get(position).email
                }
                override fun onSelectionClear() {
                    showToast("Selection is clear")
                }
                private fun showToast(s: String) {
                    Toast.makeText(baseContext, s, Toast.LENGTH_SHORT).show()
                }
            })

            //select
//            user_client.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    selectClient = it.get(position).email
//                }
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//
//                }
//
//            }
        })


        userClientVM.employee()


        //select user client


        if (ContextCompat.checkSelfPermission(
                        applicationContext, Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA),
                    cameraRequestId
            )
        // chose file
        chose_file.setOnClickListener {
            EasyImage.openCamera(this,1)
        }

        // add activity
        btn_save.setOnClickListener {
            val data = InsertActivityModel()
            data.user_klien = selectClient.toString()
            data.aktifitas = input_activity.text.toString()
            data.created_by = username.toString()
            if (fileImage == null){
                Toast.makeText(
                        this,
                        "Tidak ada file yang di pilih!!",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
               
               insertActivityViewModel.addActivity(data,fileImage!!)
                println("DATA ${data.user_klien} , ${data.aktifitas},${data.created_by}  ,${fileImage!!.name}")
                finish();
                startActivity(getIntent());
                val intent = Intent(this, ReportActivity::class.java)
                startActivity(intent)
            }
            
        }

    }
    var fileImage: File? = null
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
                nama_file.setText(imageFile?.name)
                val requestOptions = RequestOptions().error(R.drawable.upload)
                Glide.with(this@InsertDailyActivity)
                        .load(imageFile)
                        .apply(requestOptions)
                        .into(image_upload)
            }

        });
    }
}
