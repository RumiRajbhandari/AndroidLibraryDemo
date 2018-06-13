package com.rosia.domain.outletDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rumi on 5/17/18.
 */
class OutletDetail (
        @SerializedName("retailOutletId")
        @Expose val id: Int,

        @SerializedName("reatilOutletName")
        @Expose val name: String,

        @SerializedName("rmapRoId")
        @Expose val rmapRoId: Int,

        @SerializedName("ownerName")
        @Expose val ownerName: String,

        @SerializedName("outletCategoryId")
        @Expose val outletCategoryId: Int,

        @SerializedName("lng")
        @Expose val longitude: String,

        @SerializedName("lat")
        @Expose val latitude: String,

        @SerializedName("contact")
        @Expose val contact: String,

        @SerializedName("routeId")
        @Expose val routeId: Int
)