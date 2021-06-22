package com.projectassyifa.dailyactivitybit.data.kegiatan.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.TypeActivityModel

class AdapterTypeActivity (val listType : List<TypeActivityModel>,var activity :Activity)
    :  RecyclerView.Adapter<TypeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_type_activity,parent,false)
        return TypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
//        holder.no.text = (position+1).toString()
        holder.kegiatan.text = listType[position].kegiatan
        holder.nominal.text = listType[position].nominal
    }

    override fun getItemCount(): Int {
       return listType.size
    }
}

class TypeViewHolder (v: View) :RecyclerView.ViewHolder(v){

//    var no = v.findViewById<TextView>(R.id.no)
    var kegiatan = v.findViewById<TextView>(R.id.kegiatan1)
    var nominal = v.findViewById<TextView>(R.id.nominal)


}
