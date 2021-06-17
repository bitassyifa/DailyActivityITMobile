package com.projectassyifa.dailyactivitybit.data.kegiatan.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataActivityModel
import com.projectassyifa.dailyactivitybit.screen.kegiatan.AddTypeActivity
import com.projectassyifa.dailyactivitybit.screen.kegiatan.DetailKegiatan

class AdapterActivityIT ( val listActivity : List<DataActivityModel>,var activity : Activity)
    : RecyclerView.Adapter<ActivityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_activity_it,parent,false)
        return ActivityViewHolder(view)

    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {

        holder.tanggal.text= (position + 1).toString()
        holder.aktivitas.text=listActivity[position].aktifitas
        holder.status.text = listActivity[position].status

        //dtails
        holder.details.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id",listActivity[position].id)
            bundle.putString("tanggal",listActivity[position].tanggal)
            bundle.putString("user_klien",listActivity[position].user_klien)
            bundle.putString("aktifitas",listActivity[position].aktifitas)
            bundle.putString("id_kegiatan",listActivity[position].id_kegiatan)
            bundle.putString("type_kegiatan",listActivity[position].type_kegiatan)
            bundle.putString("kegiatan",listActivity[position].kegiatan)
            bundle.putString("nominal",listActivity[position].nominal)
            bundle.putString("foto1",listActivity[position].foto1)
            bundle.putString("foto2",listActivity[position].foto2)
            bundle.putString("foto3",listActivity[position].foto3)
            bundle.putString("remark",listActivity[position].remark)
            bundle.putString("status",listActivity[position].status)
            bundle.putString("proses_keterangan",listActivity[position].proses_keterangan)
            bundle.putString("proses_bukti1",listActivity[position].proses_bukti1)
            bundle.putString("proses_bukti2",listActivity[position].proses_bukti2)
            bundle.putString("proses_bukti3",listActivity[position].proses_bukti3)
            bundle.putString("proses_tanggal",listActivity[position].proses_tanggal)
            bundle.putString("solved_keterangan",listActivity[position].solved_keterangan)
            bundle.putString("solved_bukti1",listActivity[position].solved_bukti1)
            bundle.putString("solved_bukti2",listActivity[position].solved_bukti2)
            bundle.putString("solved_bukti3",listActivity[position].solved_bukti3)
            bundle.putString("solved_tanggal",listActivity[position].solved_tanggal)
            bundle.putString("closed_keterangan",listActivity[position].closed_keterangan)
            bundle.putString("closed_bukti1",listActivity[position].closed_bukti1)
            bundle.putString("closed_bukti2",listActivity[position].closed_bukti2)
            bundle.putString("closed_bukti3",listActivity[position].solved_bukti3)
            bundle.putString("closed_tanggal",listActivity[position].closed_tanggal)
            bundle.putString("urut_status",listActivity[position].urut_status)
            bundle.putString("created_by",listActivity[position].created_by)

            val move = Intent(activity, DetailKegiatan::class.java)
            move.putExtras(bundle)
            activity.startActivity(move)

        }
        holder.type.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id",listActivity[position].id)
            val move = Intent(activity,AddTypeActivity::class.java)
            move.putExtras(bundle)
            activity.startActivity(move)
        }
    }

    override fun getItemCount(): Int {
       return listActivity.size
    }
}

class ActivityViewHolder (v: View) :RecyclerView.ViewHolder(v){
    var tanggal = v.findViewById<TextView>(R.id.tanggal)
    var aktivitas = v.findViewById<TextView>(R.id.aktivitas)
    var status = v.findViewById<TextView>(R.id.status)
    var details = v.findViewById<ImageButton>(R.id.btn_details)
    var type = v.findViewById<ImageButton>(R.id.btn_tambahType)
}
