package com.example.demomodule.di

import com.rosia.di.scopes.ActivityScope
import com.rosia.orderhistory.OrdersHistoryActivity
import com.rosia.orderhistory.OrdersHistoryActivityModule
import com.rosia.outletdetail.OutletsDetailsActivity
import com.rosia.outletdetail.OutletDetailsActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivitiesBindingModule is on,
 * in our case that will be AppsComponent. The beautiful part about this setup is that you never need to tell AppsComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppsComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScope
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
abstract class ActivitiesBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(OutletDetailsActivityModule::class)])
    internal abstract fun outletDetailActivity(): OutletsDetailsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(OrdersHistoryActivityModule::class)])
    internal abstract fun orderHistoryActivity(): OrdersHistoryActivity


}