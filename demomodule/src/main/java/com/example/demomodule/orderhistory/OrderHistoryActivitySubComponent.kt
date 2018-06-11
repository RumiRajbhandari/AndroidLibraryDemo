package com.rosia.orderhistory

import dagger.Subcomponent
import dagger.android.AndroidInjector



@Subcomponent
interface OrderHistoryActivitySubComponent : AndroidInjector<OrderHistoryActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<OrderHistoryActivity>()
}