package com.rosia.data.source.repository

import com.rosia.domain.outletDetail.CallHistoryData
import com.rosia.domain.outletDetail.CallHistoryResponseModel
import com.rosia.domain.outletDetail.OutletDetail
import com.rosia.domain.outletDetail.OutletResponseModel
import io.reactivex.Observable

/**
 * Created by rumi on 5/17/18.
 */
interface OutletDetailRepository {
    fun getOutletDetail(id :Int): Observable<OutletResponseModel>
    fun getCallHistory(outletId: Int): Observable<CallHistoryResponseModel>
}