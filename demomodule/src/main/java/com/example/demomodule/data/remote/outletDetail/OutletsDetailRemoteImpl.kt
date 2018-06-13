package com.rosia.data.source.remote.outletDetail

import com.example.demomodule.data.remote.BaseResponses
import com.example.demomodule.data.remote.RetrofitApiServices
import com.rosia.domain.outletDetail.CallsHistoryResponseModel
import com.rosia.domain.outletDetail.OutletsResponseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rumi on 5/17/18.
 */
class OutletsDetailRemoteImpl @Inject constructor(private var apiServices: RetrofitApiServices):OutletsDetailRemote {
    override fun getOutletDetail(id: Int): Observable<BaseResponses<OutletsResponseModel>> {
        return apiServices.getOutletDetail(id)
    }

    override fun getCallHistory(outletId: Int): Observable<BaseResponses<CallsHistoryResponseModel>> {
        return apiServices.getCallHistoryData(outletId,3)
    }

}