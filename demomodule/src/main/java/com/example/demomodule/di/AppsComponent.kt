package com.example.demomodule.di

import android.app.Application
import com.example.demomodule.AppComponentBase
import com.example.demomodule.DemoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by subrat on 3/25/18.
 */

/**
 * This is a Dagger component. Refer to [com.rosia] for the list of Dagger components
 * used in this application.
 *
 *
 * Even though Dagger allows annotating a [Component] as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in [ ].
 * //[AndroidSupportInjectionModule]
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules = [(ApisModule::class),
    (AndroidSupportInjectionModule::class),
    (AppModule::class),
    (ActivitiesBindingModule::class)])
interface AppsComponent : AndroidInjector<AppComponentBase>{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) : AppsComponent.Builder
        fun build() : AppsComponent
    }
}