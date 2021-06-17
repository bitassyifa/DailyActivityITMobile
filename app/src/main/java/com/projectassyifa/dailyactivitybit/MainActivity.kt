package com.projectassyifa.dailyactivitybit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.projectassyifa.dailyactivitybit.screen.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent( this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}