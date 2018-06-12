package com.example.demomodule.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.demomodule.data.local.entity.CallHistoryEntity

@Dao
interface CallHistoryDao {
    @Query("select * from callHistory where outletId=:id")
    fun getAllCallHistory(id:Int):List<CallHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(callHistory: CallHistoryEntity)
}