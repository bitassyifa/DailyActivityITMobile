package com.projectassyifa.dailyactivitybit.screen.kegiatan

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.container.MyApplication
import com.projectassyifa.dailyactivitybit.data.kegiatan.adapter.AdapterActivityIT
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel.DataActivityViewModel
import com.projectassyifa.dailyactivitybit.utils.LoadingDialog
import kotlinx.android.synthetic.main.activity_report.*
import javax.inject.Inject

class ReportActivity : AppCompatActivity() {

    var dataLogin : SharedPreferences? = null

    @Inject
    lateinit var dataActivityViewModel: DataActivityViewModel
    lateinit var adapterActivityIT: AdapterActivityIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },3000)
        (applicationContext as MyApplication).applicationComponent.inject(this)
        dataLogin = getSharedPreferences(
                getString(R.string.shared_preference_name),
                Context.MODE_PRIVATE
        )

        //tampil data
        kegiatanRecycleView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        dataActivityViewModel.dataActivity?.observe(this,Observer{
            adapterActivityIT = AdapterActivityIT(it,this)
            kegiatanRecycleView.adapter = adapterActivityIT
            println("INI DATA ADAPTER $adapterActivityIT")
        })

        val username = dataLogin?.getString(
                getString(R.string.username),
                getString(R.string.default_value)
        )
        dataActivityViewModel.activityIT(username.toString())


    }
}