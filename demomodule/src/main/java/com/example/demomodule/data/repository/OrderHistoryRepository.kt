package com.rosia.data.source.repository

import com.rosia.domain.outletDetail.OrderItem
import io.reactivex.Observable

interface OrderHistoryRepository {
    fun getOrderHistory(outletId: Int): Observable<List<OrderItem>>
    fun getOrderHistoryLocal(outletId: Int):Observable<List<OrderItem>>
}