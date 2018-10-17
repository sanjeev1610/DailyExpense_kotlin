package com.mobiapp4u.pc.dailyexpense.View

import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mobiapp4u.pc.dailyexpense.Model.IncExpModel
import com.mobiapp4u.pc.dailyexpense.Presentor.PresenterAPI
import com.mobiapp4u.pc.dailyexpense.R
import com.mobiapp4u.pc.dailyexpense.bean.IncExp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewAPI {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.holo_red_dark)))

        insertBtn.setOnClickListener {
            if(etDate.text.isEmpty() || etMoney.text.isEmpty() || etDesc.text.isEmpty()){
                Toast.makeText(this@MainActivity,"Please Enter the Data in All Fields",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            var api = IncExpModel(this)
            api.insertIncExp(IncExp(etDate.text.toString(), etMoney.text.toString().toInt() ,etDesc.text.toString(), spinnetType.selectedItem.toString() ))


        }
        readBtn.setOnClickListener {
            var api = IncExpModel(this)
            api.readIncExp()

        }

        updateBtn.setOnClickListener {
            if(etDate.text.isEmpty() || etMoney.text.isEmpty() || etDesc.text.isEmpty()){
                Toast.makeText(this@MainActivity,"Please Enter the Data in All Fields",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            var api = IncExpModel(this)
            api.updateIncExp(IncExp(etDate.text.toString(), etMoney.text.toString().toInt() ,etDesc.text.toString(), spinnetType.selectedItem.toString() ))

        }

        deleteBtn.setOnClickListener {
            if(etDate.text.isEmpty()){
                Toast.makeText(this@MainActivity,"Please Enter the Date Field",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            var api = IncExpModel(this)
           // api.deleteIncExp(IncExp(etDate.text.toString(), etMoney.text.toString().toInt() ,etDesc.text.toString(), spinnetType.selectedItem.toString() ))
             api.deleteIncExp(etDate.text.toString())
        }

    }

    override fun insertIncExp(status: String) {
     Toast.makeText(this@MainActivity,"$status",Toast.LENGTH_LONG).show()
        listView.adapter = null
        etDate.setText("")
        etMoney.setText("")
        etDesc.setText("")
        tvExpSum.text = ""
        tvIncSum.text = ""
    }

    override fun readIncExp(list: MutableList<IncExp>) {
        if(list == null){
            Toast.makeText(this,"No Data Available Plz Insert the Data",Toast.LENGTH_LONG).show()
            return
        }
        var tempList = mutableListOf<String>()
        var incSum = 0
        var expSum = 0
        for (lst in list){
            tempList.add("DATE: "+lst.date+"\n"+"MONEY: "+lst.money+"\n"+"DESC: "+lst.desc+"\n"+"TYPE: "+lst.type)
            if(lst.type.equals("Income")){
                incSum += lst.money
            }else{
                expSum += lst.money
            }
        }

     var adapter = ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,tempList)
        listView.adapter = adapter
        tvIncSum.text = incSum.toString()
        tvExpSum.text = expSum.toString()

    }

    override fun updateIncExp(status: String) {
        Toast.makeText(this,"$status",Toast.LENGTH_LONG).show()
        listView.adapter = null
        etDate.setText("")
        etMoney.setText("")
        etDesc.setText("")
        tvExpSum.text = ""
        tvIncSum.text = ""

    }

    override fun deleteIncExp(status: String) {
        Toast.makeText(this,"$status",Toast.LENGTH_LONG).show()
        listView.adapter = null
        etDate.setText("")
        etMoney.setText("")
        etDesc.setText("")
        tvExpSum.text = ""
        tvIncSum.text = ""

    }

}
