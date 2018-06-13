package com.rosia.data.source.repository

import com.rosia.domain.outletDetail.OrdersItem
import io.reactivex.Observable

interface OrderHistorysRepository {
    fun getOrderHistory(outletId: Int): Observable<List<OrdersItem>>
    fun getOrderHistoryLocal(outletId: Int):Observable<List<OrdersItem>>
}