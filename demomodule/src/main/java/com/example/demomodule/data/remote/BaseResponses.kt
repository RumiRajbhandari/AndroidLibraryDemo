package com.example.demomodule.data.remote

/**
 * Created by rumi on 6/7/18.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponses<T> (
        @SerializedName("status") @Expose var statusCode: Int = 0,
        @SerializedName("success") @Expose var success: Boolean,
        @SerializedName("data") @Expose var response: T? = null)