package com.example.demomodule.data.remote

import android.content.Context
import com.example.demomodule.outletDetail.isNetworkAvailable
import com.rosia.exceptions.FailedResponseException
import com.rosia.exceptions.NetworkNotAvailableException
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by subrat on 3/25/18.
 */
@Singleton
class ApiInterceptor @Inject constructor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable(context)) {
            throw NetworkNotAvailableException()
        }

        val requestBuilder = chain.request().newBuilder()
//        if (!sharedPreferenceManager.userAccessToken.isEmpty()) {
//            requestBuilder.addHeader("Authorization", "Bearer ${sharedPreferenceManager.userAccessToken}")
//        }
        var response= chain.proceed(requestBuilder.build())
        if(response.isSuccessful){
            return response
        }else if(response.code()==401){
            throw FailedResponseException(response.code(),"Unauthorized user")

        }
        else{
            var errorResponse=JSONObject(response.body()?.string()).getJSONObject("error")
            throw FailedResponseException(errorResponse.getInt("code"),errorResponse.getString("message"))
        }

    }

}