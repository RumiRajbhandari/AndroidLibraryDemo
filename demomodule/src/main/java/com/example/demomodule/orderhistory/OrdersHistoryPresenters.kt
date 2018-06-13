package com.rosia.orderhistory

import com.example.demomodule.outletDetail.formatMilliSecondToMonth
import com.rosia.data.OrderHistorysRepositoryImpl
import com.rosia.domain.outletDetail.OrdersHistory
import com.rosia.domain.outletDetail.OrdersItem
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

    private fun groupOrderHistoryByMonth(ordersItemList: List<OrdersItem>):List<OrdersHistory>{
        val group=ordersItemList.groupBy { orderItemList-> formatMilliSecondToMonth(orderItemList.date) }
        println("group is $group")
        val orderHistoryList= mutableListOf<OrdersHistory>()
        group.forEach{
            var orderHistory=OrdersHistory(it.key, it.value)
            orderHistoryList.add(orderHistory)
        }
        return orderHistoryList

    }


}