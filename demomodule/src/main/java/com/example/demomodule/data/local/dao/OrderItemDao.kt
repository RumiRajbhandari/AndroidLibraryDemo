package com.example.demomodule.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.demomodule.data.local.entity.OrderItemEntity
import io.reactivex.Observable

@Dao
interface OrderItemDao {
    @Query("select * from orderItem where outletId =:id")
    fun getAllOrderItem(id:Int): List<OrderItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderItemEntity: OrderItemEntity)
}