package com.rosia.domain.outletDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rumi on 5/27/18.
 */
class CallsHistoryResponseModel (
//        @SerializedName("calls") @Expose val calls:CallsHistoryData
        @SerializedName("calls") @Expose val calls:List<CallsHistory>
)