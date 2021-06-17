package com.projectassyifa.dailyactivitybit.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.data.login.viewmodel.UserLoginVM
import com.projectassyifa.dailyactivitybit.screen.kegiatan.InsertDailyActivity
import com.projectassyifa.dailyactivitybit.utils.LoadingDialog
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    lateinit var  navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },3000)

        btn_add.setOnClickListener {
            val move = Intent(this,InsertDailyActivity::class.java)
            startActivity(move)
        }
        bottomNavView.background = null
        bottomNavView.menu.getItem(1).isEnabled =false

        navController = (nav_main_host_fragment_container as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavView,navController)
        bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.report -> {
                    navController.navigate(R.id.global_to_homeLayout)
                    true
                }
                R.id.profil -> {
                    navController.navigate(R.id.global_to_profilLayout)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }
}