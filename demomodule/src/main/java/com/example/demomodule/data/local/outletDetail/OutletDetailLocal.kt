package com.example.demomodule.data.local.outletDetail

import com.example.demomodule.data.local.entity.CallHistoryEntity
import com.example.demomodule.data.local.entity.OrderItemEntity
import com.example.demomodule.data.local.entity.OutletDetailEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface OutletDetailLocal {
    fun getOutletDetail(id :Int): Observable<OutletDetailEntity>
    fun getCallHistoryList(id :Int): Observable<List<CallHistoryEntity>>
    fun insertOutletDetail(outletDetailList: OutletDetailEntity):Completable
    fun insertCallHistoryEntity(callHistoryEntityList: List<CallHistoryEntity>):Completable

}