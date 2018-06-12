package com.example.demomodule.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "orderItem",
        foreignKeys = [(ForeignKey(entity = OutletDetailEntity::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("outletId"),
                onDelete = ForeignKey.CASCADE))])
class OrderItemEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("salesOrderId")
        val id: Int,

        @ColumnInfo(name = "outletId")
        val outletId: Int,

        @ColumnInfo(name = "skuId")
        @SerializedName("skuId")
        val skuId: Int,

        @ColumnInfo(name = "transactionTimeStamp")
        @SerializedName("transactionTimeStamp")
        val date: Long,

        @ColumnInfo(name = "price")
        @SerializedName("price")
        val quantity: Double,

        @ColumnInfo(name = "outletCatId")
        @SerializedName("outletCatId")
        val outletCategoryId: Int,

        @ColumnInfo(name = "skuName")
        var skuName: String? = null
)