package com.rosia.domain.outletDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rumi on 5/20/18.
 */
class CallHistoryData (
//        @SerializedName("lastCalled") @Expose val lastCalled: String,
        @SerializedName("callHistoryList") @Expose val callHistoryList: List<CallHistory>
)