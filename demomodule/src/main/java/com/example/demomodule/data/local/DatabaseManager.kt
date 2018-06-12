package com.example.demomodule.data.local

import android.content.Context
import com.example.demomodule.data.local.dao.CallHistoryDao
import com.example.demomodule.data.local.dao.OrderItemDao
import com.example.demomodule.data.local.dao.OutletDetailDao
import javax.inject.Inject

class DatabaseManager  @Inject constructor(context: Context) {
    val instance = EvolveDatabase.getInstance(context)
    fun getOutletDao(): OutletDetailDao {
        return instance.outletDetailDao()
    }
    fun getCallHistoryDao(): CallHistoryDao {
        return instance.callHistoryDao()
    }
    fun getOrderItemDao(): OrderItemDao {
        return instance.orderItemDao()
    }
}