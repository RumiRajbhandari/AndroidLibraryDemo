package com.rosia.domain.outletDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rumi on 5/20/18.
 */
class OutletsEntity (
        @SerializedName("outletsDetail") @Expose val outletsDetail: OutletsDetail,
        @SerializedName("callsHistoryData") @Expose val callsHistoryData: CallsHistoryData
)