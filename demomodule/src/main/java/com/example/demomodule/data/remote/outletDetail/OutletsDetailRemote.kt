package com.rosia.data.source.remote.outletDetail

import com.example.demomodule.data.remote.BaseResponses
import com.rosia.domain.outletDetail.CallsHistoryResponseModel
import com.rosia.domain.outletDetail.OutletsResponseModel
import io.reactivex.Observable

/**
 * Created by rumi on 5/17/18.
 */
interface OutletsDetailRemote {
    fun getOutletDetail(id :Int):Observable<BaseResponses<OutletsResponseModel>>
    fun getCallHistory(outletId: Int):Observable<BaseResponses<CallsHistoryResponseModel>>
}