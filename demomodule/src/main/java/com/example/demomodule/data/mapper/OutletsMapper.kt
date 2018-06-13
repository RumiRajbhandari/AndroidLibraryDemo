package com.example.demomodule.data.mapper

import com.example.demomodule.data.local.entity.CallHistoryEntity
import com.example.demomodule.data.local.entity.OutletDetailEntity
import com.rosia.domain.outletDetail.CallsHistory
import com.rosia.domain.outletDetail.OutletsDetail
import javax.inject.Inject

class OutletsMapper @Inject constructor() {
    fun mapToEntity(it: OutletsDetail):OutletDetailEntity{
        return OutletDetailEntity(it.id,
                it.name,
                it.rmapRoId,
                it.ownerName,
                it.outletCategoryId,
                it.longitude,
                it.latitude,
                it.contact,
                it.routeId)
    }

    fun mapEntityToDomain(it:OutletDetailEntity):OutletsDetail{
        return OutletsDetail(it.id,
                it.name,
                it.rmapRoId,
                it.ownerName,
                it.outletCategoryId,
                it.longitude,
                it.latitude,
                it.contact,
                it.routeId)
    }

    fun mapCallHistoryToEntity(callsHistoryList:List<CallsHistory>):List<CallHistoryEntity>{
        return callsHistoryList.map {  CallHistoryEntity(it.id,
                it.outletId,
                it.amount,
                it.status,
                it.date)
        }
    }

    fun mapCallHistoryEntityToDomain(callHistoryEntityList:List<CallHistoryEntity>):List<CallsHistory>{
        return callHistoryEntityList.map { CallsHistory(it.id,
                it.outletId,
                it.amount,
                it.status,
                it.date) }
    }

}