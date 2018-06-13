package com.example.demomodule.data.remote

import android.content.Context
import com.example.demomodule.outletsDetail.isNetworkAvailable
import com.example.demomodule.pref.SharedPreferencesManager
import com.rosia.exceptions.FailedResponseException
import com.rosia.exceptions.NetworkNotAvailableException
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiInterceptors @Inject constructor(private val context: Context, private val sharedPreferencesManager: SharedPreferencesManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable(context)) {
            throw NetworkNotAvailableException()
        }

        val requestBuilder = chain.request().newBuilder()
        if (!sharedPreferencesManager.userAccessToken.isEmpty()) {
            println("access token is "+sharedPreferencesManager.userAccessToken)
            requestBuilder.addHeader("Authorization", "Bearer ${sharedPreferencesManager.userAccessToken}")
        }

        var response = chain.proceed(requestBuilder.build())
        if (response.isSuccessful) {
            return response
        } else if (response.code() == 401) {
            throw FailedResponseException(response.code(), "Unauthorized user")

        } else {
            var errorResponse = JSONObject(response.body()?.string()).getJSONObject("error")
            throw FailedResponseException(errorResponse.getInt("code"), errorResponse.getString("message"))
        }

    }

}