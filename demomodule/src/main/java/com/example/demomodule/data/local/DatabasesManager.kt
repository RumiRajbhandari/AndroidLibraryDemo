package com.example.demomodule.data.local

import android.content.Context
import com.example.demomodule.data.local.dao.CallHistoryDao
import com.example.demomodule.data.local.dao.OrderItemDao
import com.example.demomodule.data.local.dao.OutletDetailDao
import javax.inject.Inject

class DatabasesManager  @Inject constructor(context: Context) {
    val instance = EvolveDatabases.getInstance(context)
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