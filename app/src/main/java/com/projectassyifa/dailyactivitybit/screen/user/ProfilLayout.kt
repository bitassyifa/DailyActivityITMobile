package com.projectassyifa.dailyactivitybit.screen.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.screen.login.LoginActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*
import kotlinx.android.synthetic.main.fragment_home_layout.*
import kotlinx.android.synthetic.main.fragment_profil_layout.*

class ProfilLayout : Fragment(),View.OnClickListener {

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
        return inflater.inflate(R.layout.fragment_profil_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       btn_logout.setOnClickListener(this)
        val nama = dataLogin?.getString(
            getString(R.string.nama_pegawai),
            getString(R.string.default_value)
        )
        val id = dataLogin?.getString(
            getString(R.string.id_pegawai),
            getString(R.string.default_value)
        )
        val noTlp = dataLogin?.getString(
            getString(R.string.no_tlp),
            getString(R.string.default_value)
        )
        val foto = dataLogin?.getString(
            getString(R.string.foto),
            getString(R.string.default_value)
        )
        val unit = dataLogin?.getString(
            getString(R.string.unit),
            getString(R.string.default_value)
        )

        var linkFoto= "http://202.62.9.138:1234/hrd/php/foto/$foto"
        Picasso.with(context)
            .load(linkFoto)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.profil)
            .error(R.drawable.profil)
            .into(profile_image)
        println("LINK FOTO PROFIL $linkFoto")

        namaPegawai.text= ": $nama"
        idPegawai.text= ": $id"
//        noTlpPegawai.text = ": $noTlp"
        text_unit.text =": $unit"
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_logout -> {
                println("klik logout")
                with(dataLogin?.edit()) {
                    this?.clear()
                    this?.apply()
                    Intent(getContext(), LoginActivity::class.java).apply {
                        addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                    }.also {  startActivity(it) }
                }
            }
        }
    }


}