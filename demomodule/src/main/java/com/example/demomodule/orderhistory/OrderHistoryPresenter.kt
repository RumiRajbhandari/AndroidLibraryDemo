package com.rosia.orderhistory

import com.example.demomodule.outletDetail.formatMilliSecondToMonth
import com.rosia.data.OrderHistoryRepositoryImpl
import com.rosia.domain.outletDetail.OrderHistory
import com.rosia.domain.outletDetail.OrderItem
import com.rosia.exceptions.ErrorMessageFactory
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class OrderHistoryPresenter @Inject constructor(var view: OrderHistoryContract.View, var repository: OrderHistoryRepositoryImpl) : OrderHistoryContract.Presenter {
    private var disposable: Disposable? = null

    override fun start() {
        disposable = repository.getOrderHistory(view.getOutletId())
                .subscribe({
                    println("on next order history$it")
                    view.showOrderHistorySuccess(groupOrderHistoryByMonth(it.orderHistoryList))
//                    groupOrderHistoryByMonth(it.orderHistoryList)
                }, {
                    println("on error order history"+it.message)
                    view.showError(ErrorMessageFactory.createMessage(it))
                })
    }


    override fun stop() {
        disposable?.dispose()
    }

    fun groupOrderHistoryByMonth(orderItemList: List<OrderItem>):List<OrderHistory>{
        val group=orderItemList.groupBy { orderItemList-> formatMilliSecondToMonth(orderItemList.date) }
        val orderHistoryList= mutableListOf<OrderHistory>()
        println("order history is "+group)
        group.forEach{
            var orderHistory=OrderHistory(it.key, it.value)
            orderHistoryList.add(orderHistory)

        }
        return orderHistoryList

    }


}