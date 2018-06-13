package com.rosia.data.source.repository

import com.rosia.domain.outletDetail.*
import io.reactivex.Observable

/**
 * Created by rumi on 5/17/18.
 */
interface OutletDetailRepository {
    fun getOutletDetail(id :Int): Observable<OutletResponseModel>
    fun getCallHistory(outletId: Int): Observable<CallHistoryResponseModel>
    fun getOrderHistory(routeId: Int): Observable<OrderHistoryResponseModel>
    fun getOutletDetailLocal(id :Int): Observable<OutletDetail>
    fun getCallHistoryLocal(outletId: Int): Observable<List<CallHistory>>
}