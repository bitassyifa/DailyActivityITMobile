package com.projectassyifa.dailyactivitybit.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Connect {
    companion object {
        val BASE_URL_GLOBAL =  "http://202.62.9.138/bit/"
        val BASE_URL_LOGIN =  "http://202.62.9.138:1234/"
        fun urlGlobal(): Retrofit {
            val koneksi = Retrofit
                .Builder()
                .baseUrl(BASE_URL_GLOBAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  koneksi
        }
        fun urlLogin(): Retrofit {
            val koneksi = Retrofit
                    .Builder()
                    .baseUrl(BASE_URL_LOGIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return  koneksi
        }
    }
}