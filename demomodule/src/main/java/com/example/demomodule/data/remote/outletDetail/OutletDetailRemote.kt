package com.rosia.data.source.remote.outletDetail

import com.example.demomodule.data.remote.BaseResponses
import com.rosia.domain.outletDetail.CallHistoryResponseModel
import com.rosia.domain.outletDetail.OutletResponseModel
import io.reactivex.Observable

/**
 * Created by rumi on 5/17/18.
 */
interface OutletDetailRemote {
    fun getOutletDetail(id :Int):Observable<BaseResponses<OutletResponseModel>>
    fun getCallHistory(outletId: Int):Observable<BaseResponses<CallHistoryResponseModel>>
}