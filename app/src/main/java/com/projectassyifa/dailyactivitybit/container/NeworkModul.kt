package com.projectassyifa.dailyactivitybit.container

import com.projectassyifa.dailyactivitybit.config.Connect
import com.projectassyifa.dailyactivitybit.data.kegiatan.api.*
import com.projectassyifa.dailyactivitybit.data.login.api.UserLoginAPI
import com.projectassyifa.dailyactivitybit.data.user.api.UserClientAPI
import dagger.Module
import dagger.Provides
import retrofit2.create

@Module
class NetworkModul {
    @Provides
    fun provideUserLoginAPI(): UserLoginAPI {
        return Connect.urlLogin().create(UserLoginAPI::class.java)
    }
    @Provides
    fun provideDataActivityAPI(): DataActivityAPI {
        return Connect.urlGlobal().create(DataActivityAPI::class.java)
    }
    @Provides
    fun provideDataRktAPI(): DataRktAPI {
        return Connect.urlGlobal().create(DataRktAPI::class.java)
    }
    @Provides
    fun provideUserClientAPI(): UserClientAPI {
        return Connect.urlGlobal().create(UserClientAPI::class.java)
    }
    @Provides
    fun provideAddActivityAPI(): AddActivityAPI {
        return Connect.urlGlobal().create(AddActivityAPI::class.java)
    }
    @Provides
    fun provideAddTypeActivity(): AddTypeActivity {
        return Connect.urlGlobal().create(AddTypeActivity::class.java)
    }
    @Provides
    fun provideDataTypeAPI(): DataTypeAPI {
        return Connect.urlGlobal().create(DataTypeAPI::class.java)
    }
}