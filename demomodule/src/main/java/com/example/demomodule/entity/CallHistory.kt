package com.rosia.domain.outletDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CallHistory (
        @SerializedName("id")
        @Expose val id: Int,

        @SerializedName("outletId")
        @Expose val outletId: Int,

        @SerializedName("price")
        @Expose val amount: Double,

        @SerializedName("status")
        @Expose val status: String,

        @SerializedName("syncTime")
        @Expose val date: Long
)