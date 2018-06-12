package com.example.demomodule.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.demomodule.data.local.entity.OutletDetailEntity
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface OutletDetailDao {
    @Query("select * from outlet where id=:id")
    fun get(id:Int): OutletDetailEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(outletDetailEntity: OutletDetailEntity)
}