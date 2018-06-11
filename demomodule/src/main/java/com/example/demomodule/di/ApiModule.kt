package com.example.demomodule.di

import com.example.demomodule.data.remote.ApiInterceptor
import com.example.demomodule.data.remote.RetrofitApiService
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by subrat on 3/24/18.
 */

@Module
open class ApiModule {


    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return Gson()
    }

//    @Singleton
//    @Provides
//    internal fun provideSharePreference(context: Context): SharedPreferenceManager {
//        return SharedPreferenceManager(context)
//    }

    @Provides
    @Singleton
    internal fun getHttpClient(apiInterceptor: ApiInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(apiInterceptor)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }
//TODO change base url
    @Provides
    @Singleton
    open fun remoteRepo(gson: Gson, client: OkHttpClient): RetrofitApiService {
        return Retrofit.Builder()
                .baseUrl("http://89.31.63.246/api/v1/")
                .client(client) //client is for logging the request and response
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build().create(RetrofitApiService::class.java)
    }


}