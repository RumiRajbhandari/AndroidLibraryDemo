package com.rosia.domain.outletDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rumi on 5/27/18.
 */
class OrderHistoryResponseModel (
        @SerializedName("list") @Expose val orderHistoryList:List<OrderItem>
)