package com.projectassyifa.dailyactivitybit.screen.kegiatan

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.dailyactivitybit.R
import com.projectassyifa.dailyactivitybit.container.MyApplication
import com.projectassyifa.dailyactivitybit.data.kegiatan.adapter.AdapterActivityRKT
import com.projectassyifa.dailyactivitybit.data.kegiatan.adapter.AdapterTypeActivity
import com.projectassyifa.dailyactivitybit.data.kegiatan.model.TypeActivityModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel.AddTypeActivityVM
import com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel.DataRktViewModel
import com.projectassyifa.dailyactivitybit.data.kegiatan.viewmodel.DataTypeVM
import kotlinx.android.synthetic.main.activity_add_type.*
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*
import javax.inject.Inject

class AddTypeActivity : AppCompatActivity() {

    var dataLogin : SharedPreferences? = null
    var id_kegiatan : String? = null
    var kegiatan : String? = null
    var type_kegiatan :String? = null

    @Inject
    lateinit var dataRktViewModel: DataRktViewModel
    lateinit var adapterActivityRKT: AdapterActivityRKT

    @Inject
    lateinit var addTypeActivityVM: AddTypeActivityVM

    @Inject
    lateinit var dataTypeVM: DataTypeVM
    lateinit var adapterTypeActivity: AdapterTypeActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_type)
        (applicationContext as MyApplication).applicationComponent.inject(this)


        dataLogin = getSharedPreferences(
                getString(R.string.shared_preference_name),
                Context.MODE_PRIVATE
        )

        // rkt spinner
        dataRktViewModel.dataRkt?.observe(this, Observer {
            adapterActivityRKT = AdapterActivityRKT(this, it)
            kegiatan_rkt.adapter = adapterActivityRKT

            //select
            kegiatan_rkt.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    id_kegiatan = it.get(position).id_kegiatan
                    kegiatan = it.get(position).kegiatan
                    type_kegiatan = it.get(position).type_kegiatan
                    if (type_kegiatan == "BUDGETING"){
                        wadah_nominal.isVisible = true
                    } else {
                        wadah_nominal.isVisible = false
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    "no item select"
                }

            }

        })

        var bundle = intent.extras

        id_activity.text = ": ${bundle?.getString("id")}"

        val username = dataLogin?.getString(
                getString(R.string.username),
                getString(R.string.default_value)
        )
        dataRktViewModel.activityRKT(username.toString())

        btn_addType.setOnClickListener {
            val data = TypeActivityModel()
                    data.id_data_activity = bundle?.getString("id").toString()
                    data.id_kegiatan = id_kegiatan.toString()
                    data.kegiatan = kegiatan.toString()
                    data.type_kegiatan = type_kegiatan.toString()
                    data.nominal = nominal.text.toString()
                    data.cretaed_by = username.toString()
println("DATA TYPE ${data.id_data_activity},${data.id_kegiatan},${data.kegiatan},${data.type_kegiatan},${data.nominal}")

            addTypeActivityVM.addtypeAct(data.id_data_activity,data)
            finish();
            startActivity(getIntent());
        }

        //data type
        type_kegiatan_rec.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        dataTypeVM.dataType?.observe(this,Observer{
            adapterTypeActivity = AdapterTypeActivity(it,this)
            type_kegiatan_rec.adapter = adapterTypeActivity

            println("ADAPTER $adapterTypeActivity")
        })

        dataTypeVM.typeAct(bundle?.getString("id").toString())
    }
}