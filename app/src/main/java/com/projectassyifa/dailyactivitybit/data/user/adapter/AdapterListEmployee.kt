package com.projectassyifa.dailyactivitybit.data.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.TextView
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.data.user.model.UserClientModel
import tech.hibk.searchablespinnerlibrary.SearchableItem
import tech.hibk.searchablespinnerlibrary.SearchableListAdapter
import java.util.*
import kotlin.collections.ArrayList

class AdapterListEmployee (val context: Context,var dataSource :List<UserClientModel>) : BaseAdapter(){
    private val  inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//    var searchlistItems : MutableList<UserClientModel>? = null
//    var suggestions : MutableList<UserClientModel> = ArrayList()
//    var filter : SearchableListAdapter.CustomFilter? = null

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
       if (convertView == null) {
           view = inflater.inflate(R.layout.adapter_employee,parent,false)
           vh = ItemHolder(view)
           view?.tag = vh
       } else {
           view = convertView
           vh= view.tag as ItemHolder
       }
        vh.nama_pegawai.text = dataSource.get(position).nama_pegawai
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
//    }
//     @JvmName("getFilter1")
//     fun getFilter(): SearchableListAdapter.CustomFilter? {
//        return filter
//    }
//
//    inner class CustomFilter : Filter() {
//        override fun performFiltering(constraint: CharSequence): FilterResults {
//            suggestions.clear()
//            constraint.let {
//                for (i in searchlistItems?.indices!!) {
//                    if (searchlistItems!![i].nama_pegawai.toLowerCase(Locale.ENGLISH).contains(constraint)) { // Compare item in original searchListItems if it contains constraints.
//                        suggestions.add(searchlistItems!![i]) // If TRUE add item in Suggestions.
//                    }
//                }
//            }
//            val results = FilterResults() // Create new Filter Results and return this to publishResults;
//            results.values = suggestions
//            results.count = suggestions.size
//            return results
//        }
//
//        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//            if (results != null) {
//                if (results.count > 0) {
//                    notifyDataSetChanged()
//                } else {
//                    notifyDataSetInvalidated()
//                }
//            }
//        }
    }
}