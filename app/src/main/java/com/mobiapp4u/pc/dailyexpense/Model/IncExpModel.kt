package com.mobiapp4u.pc.dailyexpense.Model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.mobiapp4u.pc.dailyexpense.Presentor.PresenterAPI
import com.mobiapp4u.pc.dailyexpense.View.MainActivity
import com.mobiapp4u.pc.dailyexpense.View.ViewAPI
import com.mobiapp4u.pc.dailyexpense.bean.IncExp

class IncExpModel:PresenterAPI{

    private var api:ViewAPI? = null
    private var dBase:SQLiteDatabase? = null

    constructor(api:ViewAPI){
       this.api = api
        var mActivity = api as MainActivity
        dBase = mActivity.openOrCreateDatabase("incexp_db", Context.MODE_PRIVATE, null)
        dBase!!.execSQL("create table if not exists manage_incexp(_id integer primary key autoincrement,date varchar(100),money number,_desc varchar(500),type varchar(100))")

    }

    override fun insertIncExp(bean: IncExp) {
     var cv = ContentValues()
        cv.put("date",bean.date)
        cv.put("money",bean.money)
        cv.put("_desc",bean.desc)
        cv.put("type",bean.type)

        var status = dBase!!.insert("manage_incexp", null, cv)
        if (status != (-1).toLong()){
            api!!.insertIncExp("Data Inserted")
        }else{
            api!!.insertIncExp("Data Insertion failed")
        }
    }

    override fun readIncExp() {
        var c = dBase!!.query("manage_incexp",null,null,null,null,null,null)
        var list = mutableListOf<IncExp>()
        while(c.moveToNext()){
            var bean = IncExp(c.getString(1),c.getInt(2),c.getString(3),c.getString(4))
            list.add(bean)


        }
        api!!.readIncExp(list)

    }

    override fun updateIncExp(bean: IncExp) {
        var cv = ContentValues()
        cv.put("date",bean.date)
        cv.put("money",bean.money)
        cv.put("_desc",bean.desc)
        cv.put("type",bean.type)

        var status = dBase!!.update("manage_incexp",cv,"date=?", arrayOf(bean.date))
        if(status > 0 ){
            api!!.updateIncExp("Data Updated")
        }else{
            api!!.updateIncExp("Data Not Updated or RECORD NOT THERE")

        }

    }

    override fun deleteIncExp(date:String) {
        var status =  dBase!!.delete("manage_incexp","date=?",
                arrayOf(date))
        if(status>0){
           api!!.deleteIncExp("Selected record Deleted")
        }else{
            api!!.deleteIncExp("Selected record NOT DELETED or NOT THERE IN")

        }
    }


}












