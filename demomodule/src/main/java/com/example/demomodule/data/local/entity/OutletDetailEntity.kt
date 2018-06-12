package com.example.demomodule.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "outlet")
class OutletDetailEntity (
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    val id: Int,

    @SerializedName("reatilOutletName")
    @ColumnInfo(name = "name")
    @Expose
    val name: String,

    @SerializedName("rmapRoId")
    @ColumnInfo(name = "rmapRoId")
    @Expose
    val rmapRoId: Int,

    @SerializedName("ownerName")
    @ColumnInfo(name = "ownerName")
    @Expose
    val ownerName: String,

    @SerializedName("outletCategoryId")
    @ColumnInfo(name = "outletCategoryId")
    @Expose
    val outletCategoryId: Int,

    @SerializedName("lng")
    @ColumnInfo(name = "longitude")
    @Expose
    val longitude: String,

    @SerializedName("lat")
    @ColumnInfo(name = "latitude")
    @Expose
    val latitude: String,

    @SerializedName("contact")
    @ColumnInfo(name = "contact")
    @Expose
    val contact: String,

    @SerializedName("routeId")
    @ColumnInfo(name = "routeId")
    @Expose
    val routeId: Int
)