package com.example.demomodule.data.mapper

import com.example.demomodule.data.local.entity.OrderItemEntity
import com.rosia.domain.outletDetail.OrdersItem
import javax.inject.Inject

class OrdersHistoryMapper @Inject constructor(){

    fun mapOrderItemListToEntity(ordersItemList: List<OrdersItem>?, outletId:Int):List<OrderItemEntity>?{
        if (ordersItemList!=null){
            return ordersItemList.map { OrderItemEntity(it.id,outletId,it.skuId,it.date,it.quantity,it.outletId,it.skuName) }
        }
        return null
    }

    fun mapOrderItemEntityListToDomain(orderItemEntityList:List<OrderItemEntity>):List<OrdersItem>{
        return orderItemEntityList.map { OrdersItem(it.id,it.skuId,it.date,it.quantity,it.outletId,it.skuName) }
    }
}