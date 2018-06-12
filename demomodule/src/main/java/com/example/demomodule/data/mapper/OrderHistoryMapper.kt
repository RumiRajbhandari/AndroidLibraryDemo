package com.example.demomodule.data.mapper

import com.example.demomodule.data.local.entity.OrderItemEntity
import com.rosia.domain.outletDetail.OrderItem
import javax.inject.Inject

class OrderHistoryMapper @Inject constructor(){

    fun mapOrderItemListToEntity(orderItemList: List<OrderItem>?, outletId:Int):List<OrderItemEntity>?{
        if (orderItemList!=null){
            return orderItemList.map { OrderItemEntity(it.id,outletId,it.skuId,it.date,it.quantity,it.outletId,it.skuName) }
        }
        return null
    }

    fun mapOrderItemEntityListToDomain(orderItemEntityList:List<OrderItemEntity>):List<OrderItem>{
        return orderItemEntityList.map { OrderItem(it.id,it.skuId,it.date,it.quantity,it.outletId,it.skuName) }
    }
}