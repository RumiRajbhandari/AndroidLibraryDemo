package com.example.demomodule.data.local.orderHistory

import com.example.demomodule.data.local.entity.OrderItemEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface OrdersHistoryLocal {
    fun getOrderHistory(outletId: Int): Observable<List<OrderItemEntity>>
    fun insertOrderHistory(orderItemEntityList:List<OrderItemEntity>?):Completable

}