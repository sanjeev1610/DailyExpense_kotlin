package com.mobiapp4u.pc.dailyexpense.Presentor

import com.mobiapp4u.pc.dailyexpense.bean.IncExp

interface PresenterAPI {
    fun insertIncExp(bean:IncExp)
    fun readIncExp()
    fun updateIncExp(bean:IncExp)
    fun deleteIncExp(date:String)
}