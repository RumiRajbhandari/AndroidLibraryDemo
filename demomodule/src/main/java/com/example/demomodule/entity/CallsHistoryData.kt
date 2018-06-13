package com.rosia.domain.outletDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rumi on 5/20/18.
 */
class CallsHistoryData (
//        @SerializedName("lastCalled") @Expose val lastCalled: String,
        @SerializedName("callsHistoryList") @Expose val callsHistoryList: List<CallsHistory>
)