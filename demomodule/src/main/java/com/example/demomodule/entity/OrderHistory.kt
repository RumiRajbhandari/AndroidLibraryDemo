package com.rosia.domain.outletDetail

import com.google.gson.annotations.SerializedName

data class OrderHistory(
        @SerializedName("date") val date: String,
        @SerializedName("orders") val orders: List<OrderItem>?
)
