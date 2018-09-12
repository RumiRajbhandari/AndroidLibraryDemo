package com.example.demomodule

import com.example.demomodule.di.DaggerAppsComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

abstract class AppComponentBase : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initConfig()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppsComponent.builder().application(this).build()
    }

    abstract fun initConfig()


}