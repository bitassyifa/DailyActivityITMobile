package com.projectassyifa.dailyactivitybit.screen.kegiatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projectassyifa.dailyactivitybit.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*

class DetailKegiatan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kegiatan)

        var bundle = intent.extras

        id.text = ": ${bundle?.getString("id")}"
        user_klien.text =": ${bundle?.getString("user_klien")}"
        status.text = ": ${bundle?.getString("status")}"
        //open
        aktivitas_open.text = ": ${bundle?.getString("aktifitas")}"
        tanggal_open.text = ": ${bundle?.getString("tanggal")}"
        var linkFotoOpen = "http://202.62.9.138/bit/upload/${bundle?.getString("foto1")}"
        Picasso.with(this)
                .load(linkFotoOpen)
                .placeholder(R.drawable.img_notfound)
                .error(R.drawable.img_notfound)
                .into(foto_awal)

        //proses
        aktivitas_proses.text = ": ${bundle?.getString("proses_keterangan")}"
        tanggal_proses.text = ": ${bundle?.getString("proses_tanggal")}"
        var linkFotoProses= "http://202.62.9.138/bit/upload/${bundle?.getString("proses_bukti1")}"
        Picasso.with(this)
                .load(linkFotoProses)
                .placeholder(R.drawable.img_notfound)
                .error(R.drawable.img_notfound)
                .into(foto_proses)

        //solved
        aktivitas_solved.text = ": ${bundle?.getString("solved_keterangan")}"
        tanggal_solved.text = ": ${bundle?.getString("solved_tanggal")}"
        var linkFotoSolved= "http://202.62.9.138/bit/upload/${bundle?.getString("solved_bukti1")}"
        Picasso.with(this)
                .load(linkFotoSolved)
                .placeholder(R.drawable.img_notfound)
                .error(R.drawable.img_notfound)
                .into(foto_solved)

        //close
        aktivitas_close.text = ": ${bundle?.getString("closed_keterangan")}"
        tanggal_close.text = ": ${bundle?.getString("closed_tanggal")}"
        var linkFotoClose= "http://202.62.9.138/bit/upload/${bundle?.getString("closed_bukti1")}"
        Picasso.with(this)
                .load(linkFotoClose)
                .placeholder(R.drawable.img_notfound)
                .error(R.drawable.img_notfound)
                .into(foto_close)
    }
}