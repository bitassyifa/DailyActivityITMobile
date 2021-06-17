package com.projectassyifa.dailyactivitybit.screen.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.container.MyApplication
import com.projectassyifa.dailyactivitybit.data.login.model.UserLoginModel
import com.projectassyifa.dailyactivitybit.data.login.viewmodel.UserLoginVM
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login_layout.*
import javax.inject.Inject


class LoginLayout : Fragment(), View.OnClickListener {
    var dataLogin : SharedPreferences? = null
    lateinit var navController : NavController

    @Inject
    lateinit var userLoginVM: UserLoginVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApplication).applicationComponent.inject(this)
        dataLogin = activity?.getSharedPreferences(
                getString(R.string.shared_preference_name),
                Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_layout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //login validation
        if (dataLogin?.contains(getString(R.string.username))!! && dataLogin?.contains(getString(R.string.login_method_key))!!)
        {
           //jika ada sesion langsung ke home
            view.findNavController().navigate(R.id.action_loginLayout_to_homeActivity)
        }
        navController = Navigation.findNavController(view)
        btn_login.setOnClickListener(this)
        userLoginVM.userLogin.observe(
                viewLifecycleOwner,androidx.lifecycle.Observer {
            if (it.status != true) {
                Toast.makeText(
                        this.context,
                        "Username or password salah",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this.context, "Login Success", Toast.LENGTH_SHORT).show()
                userLoginVM.userLoginResponse.observe(viewLifecycleOwner, Observer {
                    if (it != null){
                        with(dataLogin?.edit()) {
                            this?.putString(getString(R.string.username), it.email)
                            this?.putString(getString(R.string.id_pegawai), it.id_pegawai)
                            this?.putString(getString(R.string.nama_pegawai), it.nama_pegawai)
                            this?.putString(getString(R.string.nip), it.nip)
                            this?.putString(getString(R.string.foto), it.foto)
                            this?.putString(getString(R.string.no_tlp), it.no_telp)
                            this?.putString(getString(R.string.unit), it.unit)
                            this?.putString(getString(R.string.agent), it.agent)
                            this?.putString(
                                    getString(R.string.login_method_key),
                                    "appLogin"
                            )
                            this?.commit()
                        }
                              navController.navigate(R.id.action_loginLayout_to_homeActivity)
                    }
                })
            }
        }
        )
    }

    override fun onClick(v: View?) {
        when(v) {
            btn_login -> {
                val userLoginModel = UserLoginModel(
                        username = id_input.text.toString(),
                        password=  password_input.text.toString()
                )
                if (id_input.toString() == ""  && password_input.text.toString() == ""
                ){
                    Toast.makeText(this.context, "Isi semua form", Toast.LENGTH_SHORT).show()
                } else {
                    println("INI DATA ${userLoginModel.username},${userLoginModel.password}")
                    userLoginVM.loginUser(userLoginModel, requireContext())
                }
            }
        }
    }

}