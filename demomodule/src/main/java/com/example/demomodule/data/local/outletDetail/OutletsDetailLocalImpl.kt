package com.example.demomodule.data.local.outletDetail

import com.example.demomodule.data.local.DatabaseManager
import com.example.demomodule.data.local.entity.CallHistoryEntity
import com.example.demomodule.data.local.entity.OutletDetailEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class OutletsDetailLocalImpl @Inject constructor(private val databaseManager: DatabaseManager):OutletsDetailLocal {
    override fun getOutletDetail(id: Int):Observable<OutletDetailEntity> {
        return Observable.fromCallable{
            databaseManager.getOutletDao().get(id)
        }
    }

    override fun getCallHistoryList(id: Int): Observable<List<CallHistoryEntity>> {
        return Observable.fromCallable{
            databaseManager.getCallHistoryDao().getAllCallHistory(id)
        }
    }

    override fun insertOutletDetail(outletDetailList: OutletDetailEntity):Completable {
        databaseManager.getOutletDao().insert(outletDetailList)
        return Completable.complete()
    }

    override fun insertCallHistoryEntity(callHistoryEntityList: List<CallHistoryEntity>):Completable {
        callHistoryEntityList.forEach { databaseManager.getCallHistoryDao().insert(it) }
        return Completable.complete()
    }

}