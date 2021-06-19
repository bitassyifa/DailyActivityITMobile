package com.projectassyifa.dailyactivitybit.container

import com.projectassyifa.dailyactivitybit.screen.kegiatan.AddTypeActivity
import com.projectassyifa.dailyactivitybit.screen.kegiatan.InsertDailyActivity
import com.projectassyifa.dailyactivitybit.screen.kegiatan.ReportActivity
import com.projectassyifa.dailyactivitybit.screen.kegiatan.UpdateStatusActivity
import com.projectassyifa.dailyactivitybit.screen.login.LoginActivity
import com.projectassyifa.dailyactivitybit.screen.login.LoginLayout
import dagger.Component

@Component(modules = [NetworkModul::class])
interface ApplicationComponent {
    fun inject(loginLayout: LoginLayout)
    fun inject(reportActivity: ReportActivity)
    fun inject(insertDailyActivity: InsertDailyActivity)
    fun inject(addTypeActivity: AddTypeActivity)
    fun inject(updateStatusActivity: UpdateStatusActivity)

}