package com.rosia.domain.outletDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rumi on 5/20/18.
 */
class OutletEntity (
        @SerializedName("outletDetail") @Expose val outletDetail: OutletDetail,
        @SerializedName("callHistoryData") @Expose val callHistoryData: CallHistoryData
)