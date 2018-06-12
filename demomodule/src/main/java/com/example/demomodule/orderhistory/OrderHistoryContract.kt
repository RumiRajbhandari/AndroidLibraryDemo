package com.rosia.orderhistory

import com.rosia.base.BasePresenter
import com.rosia.base.BaseView
import com.rosia.domain.outletDetail.OrderHistory

interface OrderHistoryContract {

    interface View : BaseView<Presenter> {
        fun showOrderHistorySuccess(orderHistoryItems: List<OrderHistory>)
        fun getOutletId(): Int
        fun showNetworkNotAvailableError()
        fun showError(message: String)
    }

    interface Presenter : BasePresenter {
        fun getOrderItemLocal()
    }
}