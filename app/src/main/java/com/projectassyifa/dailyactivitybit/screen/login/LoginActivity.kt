package com.projectassyifa.dailyactivitybit.screen.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.activity.HomeActivity
import com.projectassyifa.dailyactivitybit.container.MyApplication
import com.projectassyifa.dailyactivitybit.data.login.model.UserLoginModel
import com.projectassyifa.dailyactivitybit.data.login.viewmodel.UserLoginVM
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
}