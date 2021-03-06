package com.example.demomodule.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by subrat on 3/25/18.
 */

/**
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppsComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 * [ ].
 */
@Module
abstract class AppModule {
    //expose Application as an injectable context
    @Binds
    internal abstract fun bindContext(application: Application): Context


}