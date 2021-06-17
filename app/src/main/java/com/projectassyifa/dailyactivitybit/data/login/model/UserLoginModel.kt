package com.projectassyifa.dailyactivitybit.data.login.model

class UserLoginModel (
    var email : String ="default",
    var username: String = "default",
    var password: String = "default"
){}

class UserLoginResponseModel (
    var id_pegawai : String = " ",
    var email : String ="",
    var nama_pegawai : String = " ",
    var foto : String = " ",
    var no_telp : String = " ",
    var unit : String = " ",
    var agent : String = "",
    var nip : String = " "
){}