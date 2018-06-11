package com.rosia.data.source.remote

import com.example.demomodule.data.remote.BaseResponse
import com.rosia.domain.outletDetail.OrderHistoryResponseModel
import io.reactivex.Observable

interface OrderHistoryRemote {
    fun getOrderHistory(routeId: Int): Observable<BaseResponse<OrderHistoryResponseModel>>
}