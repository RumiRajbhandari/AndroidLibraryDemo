package com.example.demomodule.di

import com.rosia.di.scopes.ActivityScoped
import com.rosia.orderhistory.OrderHistoryActivity
import com.rosia.orderhistory.OrderHistoryActivityModule
import com.rosia.outletdetail.OutletDetailActivity
import com.rosia.outletdetail.OutletDetailActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(OutletDetailActivityModule::class)])
    internal abstract fun outletDetailActivity(): OutletDetailActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(OrderHistoryActivityModule::class)])
    internal abstract fun orderHistoryActivity(): OrderHistoryActivity


}