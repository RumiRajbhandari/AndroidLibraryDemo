package com.rosia.data

import com.example.demomodule.data.remote.NotNullMapper
import com.rosia.data.source.remote.outletDetail.OutletDetailRemote
import com.rosia.data.source.repository.OutletDetailRepository
import com.rosia.di.qualifiers.Remote
import com.rosia.domain.outletDetail.CallHistory
import com.rosia.domain.outletDetail.CallHistoryResponseModel
import com.rosia.domain.outletDetail.OutletDetail
import com.rosia.domain.outletDetail.OutletResponseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rumi on 5/17/18.
 */
class OutletDetailRepositoryImpl @Inject constructor(@Remote private var outletDetailRemote: OutletDetailRemote) : OutletDetailRepository {
    override fun getOutletDetail(id: Int): Observable<OutletResponseModel> {
//        return outletDetailRemote.getOutletDetail(id)
//                .flatMap(NotNullMapper())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
        var outletDetail=OutletDetail(1,"Kirana Pasal",1,"Rumi",1,"85.3173964","27.6909657","982783947",1)
        var outletResponseModel=OutletResponseModel(outletDetail)
        return Observable.just(outletResponseModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCallHistory(outletId: Int): Observable<CallHistoryResponseModel> {
//        return outletDetailRemote.getCallHistory(outletId)
//                .flatMap(NotNullMapper())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//
        var callHistory=CallHistory(1,1,123.13,"Order",2344)
        var callHistory2=CallHistory(1,1,345.13,"No Sales",2344)
        var callHistory3=CallHistory(1,1,6789.13,"No order",2344)
        var callHistory4=CallHistory(1,1,73638.13,"Order",2344)
        var callHistoryList= mutableListOf<CallHistory>()
        callHistoryList.add(callHistory)
        callHistoryList.add(callHistory2)
        callHistoryList.add(callHistory3)
        callHistoryList.add(callHistory4)
        var callHistoryResponseModel=CallHistoryResponseModel(callHistoryList)
        return Observable.just(callHistoryResponseModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

}