package com.rosia.base

import android.content.Context

/**
 * Created by subrat on 3/24/18.
 */
interface BaseView<T : BasePresenter> {
   fun getContext(): Context
}