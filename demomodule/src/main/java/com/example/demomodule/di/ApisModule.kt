package com.example.demomodule.di

import android.content.Context
import com.example.demomodule.data.remote.ApiInterceptors
import com.example.demomodule.data.remote.RetrofitApiServices
import com.example.demomodule.pref.SharedPreferencesManager
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


@Module
open class ApisModule {


    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    internal fun provideSharePreference(context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    @Provides
    @Singleton
    internal fun getHttpClient(apiInterceptors: ApiInterceptors): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(apiInterceptors)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }
//TODO change base url
    @Provides
    @Singleton
    open fun remoteRepo(gson: Gson, client: OkHttpClient): RetrofitApiServices {
        return Retrofit.Builder()
                .baseUrl("http://89.31.63.171/api/v1/")
                .client(client) //client is for logging the request and response
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build().create(RetrofitApiServices::class.java)
    }


}