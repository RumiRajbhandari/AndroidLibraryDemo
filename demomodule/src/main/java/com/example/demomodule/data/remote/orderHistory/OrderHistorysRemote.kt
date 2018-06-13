package com.rosia.data.source.remote

import com.example.demomodule.data.remote.BaseResponses
import com.rosia.domain.outletDetail.OrderHistoryResponseModel
import io.reactivex.Observable

interface OrderHistorysRemote {
    fun getOrderHistory(routeId: Int): Observable<BaseResponses<OrderHistoryResponseModel>>
}