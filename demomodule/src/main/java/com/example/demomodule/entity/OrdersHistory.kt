package com.rosia.domain.outletDetail

import com.google.gson.annotations.SerializedName

data class OrdersHistory(
        @SerializedName("date") val date: String,
        @SerializedName("orders") val orders: List<OrdersItem>?
)
