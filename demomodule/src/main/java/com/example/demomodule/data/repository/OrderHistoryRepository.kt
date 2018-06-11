package com.rosia.data.source.repository

import com.rosia.domain.outletDetail.OrderHistoryResponseModel
import io.reactivex.Observable

interface OrderHistoryRepository {
    fun getOrderHistory(routeId: Int): Observable<OrderHistoryResponseModel>
}