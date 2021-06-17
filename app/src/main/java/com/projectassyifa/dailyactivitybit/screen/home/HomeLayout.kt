package com.projectassyifa.dailyactivitybit.screen.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.screen.kegiatan.ReportActivity
import kotlinx.android.synthetic.main.fragment_home_layout.*


class HomeLayout : Fragment() {

    var dataLogin: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataLogin = activity?.getSharedPreferences(
            getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_layout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_report.setOnClickListener {
            var move = Intent(this.context,ReportActivity::class.java)
            startActivity(move)
        }
        val nama = dataLogin?.getString(
            getString(R.string.nama_pegawai),
            getString(R.string.default_value)
        )
        val agent = dataLogin?.getString(
                getString(R.string.agent),
                getString(R.string.default_value)
        )
        username.text = "Selamat Datang, $nama"
    }
}