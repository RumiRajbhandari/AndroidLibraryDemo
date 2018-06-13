package com.rosia.base

import android.content.Context

/**
 * Created by subrat on 3/24/18.
 */
interface BasesView<T : BasesPresenter> {
   fun getContext(): Context
}