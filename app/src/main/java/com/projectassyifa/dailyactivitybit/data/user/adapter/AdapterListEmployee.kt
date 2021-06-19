package com.projectassyifa.dailyactivitybit.data.user.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.data.user.model.UserClientModel

import java.util.*
import kotlin.collections.ArrayList

//
class AdapterListEmployee (var dataSource :List<UserClientModel>,val context: Context) : BaseAdapter(){
    private val  inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return  dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
       val view : View
       val vh : ItemHolder
       if (convertView == null) {
           view = inflater.inflate(R.layout.adapter_employee,parent,false)
           vh = ItemHolder(view)
           view?.tag = vh
       } else {
           view = convertView
           vh= view.tag as ItemHolder
       }


        vh.nama_pegawai.text = dataSource.get(position).nama_pegawai

        println("LOG ${vh.nama_pegawai.text}")
//        vh.unit.text = dataSource.get(position).unit

        return view
    }
    private class ItemHolder(row: View?) {
        val nama_pegawai: TextView
        //        val unit: TextView
        init {
            nama_pegawai = row?.findViewById(R.id.nama_pegawai) as TextView
//            unit = row?.findViewById(R.id.unit) as TextView
        }
    }
}

//class AdapterListEmployee (val listEmployee : List<UserClientModel> , var activity : Activity)
//    : RecyclerView.Adapter<EmployeeVH>(){
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeVH {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_employee,parent,false)
//        return EmployeeVH(view)
//    }
//
//    override fun onBindViewHolder(holder: EmployeeVH, position: Int) {
//            holder.nama_pegawai.text = listEmployee[position].nama_pegawai
//    }
//
//    override fun getItemCount(): Int {
//        return listEmployee.size
//    }
//
//}
//
//class EmployeeVH  (v: View) :RecyclerView.ViewHolder(v){
//    var nama_pegawai = v.findViewById<TextView>(R.id.nama_pegawai)
//}
