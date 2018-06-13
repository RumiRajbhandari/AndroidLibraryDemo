package com.rosia.orderhistory

import com.example.demomodule.outletDetail.formatMilliSecondToMonth
import com.rosia.data.OrderHistorysRepositoryImpl
import com.rosia.domain.outletDetail.OrderHistory
import com.rosia.domain.outletDetail.OrderItem
import com.rosia.exceptions.ErrorMessageFactory
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class OrdersHistoryPresenters @Inject constructor(var view: OrdersHistoryContract.View, var repository: OrderHistorysRepositoryImpl) : OrdersHistoryContract.Presenters {
    private var disposable: Disposable? = null

    override fun start() {
        getOrderItemLocal()
    }


    override fun stop() {
        disposable?.dispose()
    }

    override fun getOrderItemLocal() {
        disposable = repository.getOrderHistoryLocal(view.getOutletId())
                .subscribe({
                    println("on next order history$it")
                    view.showOrderHistorySuccess(groupOrderHistoryByMonth(it))
                }, {
                    println("on error order history"+it.message)
                    view.showError(ErrorMessageFactory.createMessage(it))
                })
    }

    private fun groupOrderHistoryByMonth(orderItemList: List<OrderItem>):List<OrderHistory>{
        val group=orderItemList.groupBy { orderItemList-> formatMilliSecondToMonth(orderItemList.date) }
        println("group is $group")
        val orderHistoryList= mutableListOf<OrderHistory>()
        group.forEach{
            var orderHistory=OrderHistory(it.key, it.value)
            orderHistoryList.add(orderHistory)
        }
        return orderHistoryList

    }


}