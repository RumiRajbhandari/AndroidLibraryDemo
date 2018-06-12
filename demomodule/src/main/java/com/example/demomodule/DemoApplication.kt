package com.example.demomodule

import com.example.demomodule.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by rumi on 6/10/18.
 */
class DemoApplication: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initializeStetho()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()

    }

    private fun initializeStetho(){
        val builder = Stetho.newInitializerBuilder(this)
        builder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        builder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        Stetho.initialize(builder.build())
    }
}