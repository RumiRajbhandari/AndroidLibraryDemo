package com.rosia.data.source.repository

import com.rosia.domain.outletDetail.CallHistory
import com.rosia.domain.outletDetail.OrderItem
import com.rosia.domain.outletDetail.OutletDetail
import io.reactivex.Observable

/**
 * Created by rumi on 5/17/18.
 */
interface OutletDetailRepository {
    fun getOutletDetail(id :Int): Observable<OutletDetail>
    fun getCallHistory(outletId: Int): Observable<List<CallHistory>>
    fun getOrderHistory(routeId: Int): Observable<List<OrderItem>>
    fun getOutletDetailLocal(id :Int): Observable<OutletDetail>
    fun getCallHistoryLocal(outletId: Int): Observable<List<CallHistory>>
}