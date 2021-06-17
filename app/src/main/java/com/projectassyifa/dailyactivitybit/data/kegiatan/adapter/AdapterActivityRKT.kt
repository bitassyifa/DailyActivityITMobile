package com.projectassyifa.dailyactivitybit.data.kegiatan.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.DataRktModel


class AdapterActivityRKT (val context : Context,var dataSource :List<DataRktModel>) : BaseAdapter() {
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view : View
        val vh : ItemHolder
        if (convertView == null ){
            view = inflater.inflate(R.layout.adapter_rkt,parent,false)
            vh=ItemHolder(view)
            view?.tag =vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
//        vh.id.text = dataSource.get(position).id_kegiatan
        vh.kegiatan.text = "${dataSource.get(position).kegiatan} (${dataSource.get(position).type_kegiatan})"
        return view

    }

    private class ItemHolder(row: View?) {
//        val id: TextView
        val kegiatan: TextView
        init {
//            id = row?.findViewById(R.id.id_kegiatan) as TextView
            kegiatan = row?.findViewById(R.id.kegiatan) as TextView
        }
    }

}
