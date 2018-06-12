package com.example.demomodule.data.mapper

import com.example.demomodule.data.local.entity.CallHistoryEntity
import com.example.demomodule.data.local.entity.OutletDetailEntity
import com.rosia.domain.outletDetail.CallHistory
import com.rosia.domain.outletDetail.OutletDetail
import javax.inject.Inject

class OutletMapper @Inject constructor() {
    fun mapToEntity(it: OutletDetail):OutletDetailEntity{
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

    fun mapEntityToDomain(it:OutletDetailEntity):OutletDetail{
        return OutletDetail(it.id,
                it.name,
                it.rmapRoId,
                it.ownerName,
                it.outletCategoryId,
                it.longitude,
                it.latitude,
                it.contact,
                it.routeId)
    }

    fun mapCallHistoryToEntity(callHistoryList:List<CallHistory>):List<CallHistoryEntity>{
        return callHistoryList.map {  CallHistoryEntity(it.id,
                it.outletId,
                it.amount,
                it.status,
                it.date)
        }
    }

    fun mapCallHistoryEntityToDomain(callHistoryEntityList:List<CallHistoryEntity>):List<CallHistory>{
        return callHistoryEntityList.map { CallHistory(it.id,
                it.outletId,
                it.amount,
                it.status,
                it.date) }
    }

}