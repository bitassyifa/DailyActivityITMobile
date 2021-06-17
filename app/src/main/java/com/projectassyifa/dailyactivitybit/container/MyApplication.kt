package com.projectassyifa.dailyactivitybit.container

import android.app.Application

class MyApplication :Application() {
    val applicationComponent : ApplicationComponent = DaggerApplicationComponent.create()
}