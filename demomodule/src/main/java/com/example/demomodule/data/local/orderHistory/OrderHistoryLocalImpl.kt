package com.example.demomodule.data.local.orderHistory

import com.example.demomodule.data.local.DatabaseManager
import com.example.demomodule.data.local.entity.OrderItemEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class OrderHistoryLocalImpl @Inject constructor(private val databaseManager: DatabaseManager):OrderHistoryLocal {
    override fun getOrderHistory(outletId: Int): Observable<List<OrderItemEntity>> {
        return Observable.fromCallable { databaseManager.getOrderItemDao().getAllOrderItem(id = outletId) }
    }

    override fun insertOrderHistory(orderItemEntityList: List<OrderItemEntity>?):Completable {
        orderItemEntityList?.forEach { databaseManager.getOrderItemDao().insert(it) }
        return Completable.complete()
    }
}