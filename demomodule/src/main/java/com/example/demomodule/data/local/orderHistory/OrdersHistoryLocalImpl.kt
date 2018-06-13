package com.example.demomodule.data.local.orderHistory

import com.example.demomodule.data.local.DatabasesManager
import com.example.demomodule.data.local.entity.OrderItemEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class OrdersHistoryLocalImpl @Inject constructor(private val databasesManager: DatabasesManager):OrdersHistoryLocal {
    override fun getOrderHistory(outletId: Int): Observable<List<OrderItemEntity>> {
        return Observable.fromCallable { databasesManager.getOrderItemDao().getAllOrderItem(id = outletId) }
    }

    override fun insertOrderHistory(orderItemEntityList: List<OrderItemEntity>?):Completable {
        orderItemEntityList?.forEach { databasesManager.getOrderItemDao().insert(it) }
        return Completable.complete()
    }
}