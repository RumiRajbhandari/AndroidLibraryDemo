package com.rosia.data.source.remote

import com.example.demomodule.data.remote.BaseResponse
import com.example.demomodule.data.remote.RetrofitApiService
import com.rosia.domain.outletDetail.OrderHistoryResponseModel
import io.reactivex.Observable
import javax.inject.Inject


class OrderHistoryRemoteImpl @Inject constructor(private var apiService: RetrofitApiService):OrderHistoryRemote {

    override fun getOrderHistory(routeId: Int): Observable<BaseResponse<OrderHistoryResponseModel>> {
        //TODO change routeId
        return apiService.getOrderHistoryData(2, 12)
    }

}