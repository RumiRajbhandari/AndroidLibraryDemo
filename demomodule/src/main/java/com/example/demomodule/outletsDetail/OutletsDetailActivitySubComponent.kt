package com.rosia.outletdetail

import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by subrat on 4/16/18.
 */

@Subcomponent
interface OutletsDetailActivitySubComponent : AndroidInjector<OutletsDetailsActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<OutletsDetailsActivity>()
}