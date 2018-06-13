package com.rosia.data

import com.example.demomodule.data.local.orderHistory.OrderHistoryLocal
import com.example.demomodule.data.local.outletDetail.OutletDetailLocal
import com.example.demomodule.data.mapper.OrderHistoryMapper
import com.example.demomodule.data.mapper.OutletMapper
import com.example.demomodule.data.remote.NotNullMapper
import com.rosia.data.source.remote.OrderHistoryRemote
import com.rosia.data.source.remote.outletDetail.OutletDetailRemote
import com.rosia.data.source.repository.OutletDetailRepository
import com.rosia.di.qualifiers.Local
import com.rosia.di.qualifiers.Remote
import com.rosia.domain.outletDetail.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rumi on 5/17/18.
 */
class OutletDetailRepositoryImpl @Inject constructor(@Remote private var outletDetailRemote: OutletDetailRemote,
                                                     @Local private var outletDetailLocal: OutletDetailLocal,
                                                     @Remote private var orderHistoryRemote: OrderHistoryRemote,
                                                     @Local private var orderHistoryLocal: OrderHistoryLocal,
                                                     private var outletMapper: OutletMapper,
                                                     private var orderHistoryMapper: OrderHistoryMapper) : OutletDetailRepository {
    override fun getOutletDetail(id: Int): Observable<OutletResponseModel> {
//        return outletDetailRemote.getOutletDetail(id)
//                .flatMap(NotNullMapper())
//                .doOnNext { it -> outletDetailLocal.insertOutletDetail(outletMapper.mapToEntity(it.outletDetail)) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
        var outletDetail=OutletDetail(1,"Kirana Pasal",1,"Rumi",1,"85.3173964","27.6909657","982783947",1)
        var outletResponseModel=OutletResponseModel(outletDetail)

        return Observable.just(outletResponseModel)
                .doOnNext{it->outletDetailLocal.insertOutletDetail(outletMapper.mapToEntity(it.outletDetail))}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCallHistory(outletId: Int): Observable<CallHistoryResponseModel> {
//        return outletDetailRemote.getCallHistory(outletId)
//                .flatMap(NotNullMapper())
//                .doOnNext { it -> outletDetailLocal.insertCallHistoryEntity(outletMapper.mapCallHistoryToEntity(it.calls)) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//
        var callHistory=CallHistory(1,1,123.13,"Order",2344)
        var callHistory2=CallHistory(2,1,345.13,"No Sales",2344)
        var callHistory3=CallHistory(3,1,6789.13,"No order",2344)
        var callHistory4=CallHistory(4,2,73638.13,"Order",2344)
        var callHistoryList= mutableListOf<CallHistory>()
        callHistoryList.add(callHistory)
        callHistoryList.add(callHistory2)
        callHistoryList.add(callHistory3)
        callHistoryList.add(callHistory4)
        var callHistoryResponseModel=CallHistoryResponseModel(callHistoryList)
        return Observable.just(callHistoryResponseModel)
                .doOnNext{it->outletDetailLocal.insertCallHistoryEntity(outletMapper.mapCallHistoryToEntity(it.calls))}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    override fun getOrderHistory(outletId: Int): Observable<OrderHistoryResponseModel> {
        var orderItemList= mutableListOf<OrderItem>(OrderItem(9,1,2322222,44.4,1,"Ariel 1 kg"),
                OrderItem(10,2,1528784900960,4.99,2,"Ariel 4 kg"),
                OrderItem(11,5,1528784900960,292.0,1,"Ariel 2 kg"),
                OrderItem(8,6,1528784900960,88.0,1,"Ariel 3 kg"))
        var orderHistoryResponseModel=OrderHistoryResponseModel(orderItemList)

        return Observable.just(orderHistoryResponseModel)
                .doOnNext{it->orderHistoryLocal.insertOrderHistory(orderHistoryMapper.mapOrderItemListToEntity(it.orderHistoryList,outletId))}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

//        return orderHistoryRemote.getOrderHistory(outletId)
//                .flatMap(NotNullMapper())
//                .doOnNext { it -> orderHistoryLocal.insertOrderHistory(orderHistoryMapper.mapOrderItemListToEntity(it.orderHistoryList, outletId)) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getOutletDetailLocal(id: Int): Observable<OutletDetail> {
        return outletDetailLocal.getOutletDetail(id)
                .map { outletMapper.mapEntityToDomain(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    override fun getCallHistoryLocal(outletId: Int): Observable<List<CallHistory>> {
        return outletDetailLocal.getCallHistoryList(outletId)
                .map { outletMapper.mapCallHistoryEntityToDomain(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}