package com.rosia.data.source.repository

import com.rosia.domain.outletDetail.*
import io.reactivex.Observable

/**
 * Created by rumi on 5/17/18.
 */
interface OutletDetailsRepository {
    fun getOutletDetail(id :Int): Observable<OutletsResponseModel>
    fun getCallHistory(outletId: Int): Observable<CallsHistoryResponseModel>
    fun getOrderHistory(routeId: Int): Observable<OrdersHistoryResponseModel>
    fun getOutletDetailLocal(id :Int): Observable<OutletsDetail>
    fun getCallHistoryLocal(outletId: Int): Observable<List<CallsHistory>>
}