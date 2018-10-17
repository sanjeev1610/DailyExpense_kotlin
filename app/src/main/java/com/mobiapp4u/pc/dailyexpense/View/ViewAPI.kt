package com.mobiapp4u.pc.dailyexpense.View

import com.mobiapp4u.pc.dailyexpense.bean.IncExp

interface ViewAPI {
    fun insertIncExp(status:String)
    fun readIncExp(list:MutableList<IncExp>)
    fun updateIncExp(status:String)
    fun deleteIncExp(status:String)
}