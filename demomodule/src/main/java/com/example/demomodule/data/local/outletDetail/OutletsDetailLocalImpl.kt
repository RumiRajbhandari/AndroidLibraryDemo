package com.example.demomodule.data.local.outletDetail

import com.example.demomodule.data.local.DatabasesManager
import com.example.demomodule.data.local.entity.CallHistoryEntity
import com.example.demomodule.data.local.entity.OutletDetailEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class OutletsDetailLocalImpl @Inject constructor(private val databasesManager: DatabasesManager):OutletsDetailLocal {
    override fun getOutletDetail(id: Int):Observable<OutletDetailEntity> {
        return Observable.fromCallable{
            databasesManager.getOutletDao().get(id)
        }
    }

    override fun getCallHistoryList(id: Int): Observable<List<CallHistoryEntity>> {
        return Observable.fromCallable{
            databasesManager.getCallHistoryDao().getAllCallHistory(id)
        }
    }

    override fun insertOutletDetail(outletDetailList: OutletDetailEntity):Completable {
        databasesManager.getOutletDao().insert(outletDetailList)
        return Completable.complete()
    }

    override fun insertCallHistoryEntity(callHistoryEntityList: List<CallHistoryEntity>):Completable {
        callHistoryEntityList.forEach { databasesManager.getCallHistoryDao().insert(it) }
        return Completable.complete()
    }

}