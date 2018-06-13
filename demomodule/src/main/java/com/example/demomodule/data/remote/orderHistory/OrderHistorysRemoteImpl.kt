package com.rosia.data.source.remote

import com.example.demomodule.data.remote.BaseResponses
import com.example.demomodule.data.remote.RetrofitApiServices
import com.rosia.domain.outletDetail.OrderHistoryResponseModel
import io.reactivex.Observable
import javax.inject.Inject


class OrderHistorysRemoteImpl @Inject constructor(private var apiServices: RetrofitApiServices):OrderHistorysRemote {

    override fun getOrderHistory(routeId: Int): Observable<BaseResponses<OrderHistoryResponseModel>> {
        return apiServices.getOrderHistoryData(routeId, 12)
    }

}