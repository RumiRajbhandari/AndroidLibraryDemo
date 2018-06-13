package com.rosia.domain.outletDetail

import com.google.gson.annotations.SerializedName

/**
 * Created by nabin on 4/23/18.
 */
class OrdersItem(
        @SerializedName("salesOrderId")
        val id: Int,

        @SerializedName("skuId")
        val skuId: Int,

        @SerializedName("transactionTimeStamp")
        val date: Long,

        @SerializedName("price")
        val quantity: Double,

        @SerializedName("outletCatId")
        val outletId: Int,

        var skuName: String? = null

)