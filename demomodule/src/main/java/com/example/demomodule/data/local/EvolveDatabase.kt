package com.example.demomodule.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.demomodule.data.local.dao.CallHistoryDao
import com.example.demomodule.data.local.dao.OrderItemDao
import com.example.demomodule.data.local.dao.OutletDetailDao
import com.example.demomodule.data.local.entity.CallHistoryEntity
import com.example.demomodule.data.local.entity.OrderItemEntity
import com.example.demomodule.data.local.entity.OutletDetailEntity

@Database(entities = arrayOf(OutletDetailEntity::class,
        CallHistoryEntity::class,
        OrderItemEntity::class),version = 1)
abstract class EvolveDatabase: RoomDatabase() {

    companion object {
        var instance:EvolveDatabase?=null
        fun getInstance(context: Context):EvolveDatabase
        {
            if (instance==null){
                instance= createInstance(context)
            }
            return instance as EvolveDatabase
        }
        private fun createInstance(context: Context): EvolveDatabase {
            return Room.databaseBuilder(context, EvolveDatabase::class.java, "evolve.db")
                    .allowMainThreadQueries()
                    .build()
        }
    }

    abstract fun outletDetailDao():OutletDetailDao
    abstract fun callHistoryDao():CallHistoryDao
    abstract fun orderItemDao():OrderItemDao
}