package com.example.demomodule.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "callHistory")
class CallHistoryEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @Expose
        val id: Int,

        @ColumnInfo(name = "outletId")
        @Expose
        val outletId: Int,

        @ColumnInfo(name = "price")
        @Expose
        val amount: Double,

        @ColumnInfo(name = "status")
        @Expose
        val status: String,

        @ColumnInfo(name = "syncTime")
        @Expose
        val date: Long
)