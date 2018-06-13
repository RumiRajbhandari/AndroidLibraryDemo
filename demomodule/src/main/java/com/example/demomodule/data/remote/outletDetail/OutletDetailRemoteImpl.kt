package com.rosia.data.source.remote.outletDetail

import com.example.demomodule.data.remote.BaseResponses
import com.example.demomodule.data.remote.RetrofitApiServices
import com.rosia.domain.outletDetail.CallHistoryResponseModel
import com.rosia.domain.outletDetail.OutletResponseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rumi on 5/17/18.
 */
class OutletDetailRemoteImpl @Inject constructor(private var apiServices: RetrofitApiServices):OutletDetailRemote {
    override fun getOutletDetail(id: Int): Observable<BaseResponses<OutletResponseModel>> {
        return apiServices.getOutletDetail(id)
    }

    override fun getCallHistory(outletId: Int): Observable<BaseResponses<CallHistoryResponseModel>> {
        return apiServices.getCallHistoryData(outletId,3)
    }

}