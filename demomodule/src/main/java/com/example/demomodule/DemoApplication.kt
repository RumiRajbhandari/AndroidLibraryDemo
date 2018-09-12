package com.example.demomodule

import com.example.demomodule.di.DaggerAppsComponent

/**
 * Created by rumi on 6/10/18.
 */
open class DemoApplication : AppComponentBase() {

    override fun initConfig() {
//        DaggerAppsComponent.builder().application(this).build()

    }

//    override fun onCreate() {
//        super.onCreate()
//        initializeStetho()
//    }
//
//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        return DaggerAppsComponent.builder().application(this).build()
//
//    }
//
//    private fun initializeStetho(){
//        val builder = Stetho.newInitializerBuilder(this)
//        builder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//        builder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//        Stetho.initialize(builder.build())
//    }
}