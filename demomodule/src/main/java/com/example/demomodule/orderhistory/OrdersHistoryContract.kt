package com.rosia.orderhistory

import com.rosia.base.BasesPresenter
import com.rosia.base.BasesView
import com.rosia.domain.outletDetail.OrderHistory

interface OrdersHistoryContract {

    interface View : BasesView<Presenters> {
        fun showOrderHistorySuccess(orderHistoryItems: List<OrderHistory>)
        fun getOutletId(): Int
        fun showNetworkNotAvailableError()
        fun showError(message: String)
    }

    interface Presenters : BasesPresenter {
        fun getOrderItemLocal()
    }
}