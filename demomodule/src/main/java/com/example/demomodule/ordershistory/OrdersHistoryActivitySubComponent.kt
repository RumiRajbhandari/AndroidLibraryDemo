package com.rosia.orderhistory

import dagger.Subcomponent
import dagger.android.AndroidInjector



@Subcomponent
interface OrdersHistoryActivitySubComponent : AndroidInjector<OrdersHistoryActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<OrdersHistoryActivity>()
}