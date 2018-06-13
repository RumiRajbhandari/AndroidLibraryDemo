package com.rosia.domain.outletDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rumi on 5/27/18.
 */
class OrdersHistoryResponseModel (
        @SerializedName("list") @Expose val ordersHistoryList:List<OrdersItem>
)