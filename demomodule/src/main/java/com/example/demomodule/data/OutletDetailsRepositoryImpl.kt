package com.rosia.data

import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocal
import com.example.demomodule.data.local.outletDetail.OutletsDetailLocal
import com.example.demomodule.data.mapper.OrdersHistoryMapper
import com.example.demomodule.data.mapper.OutletsMapper
import com.rosia.data.source.remote.OrderHistorysRemote
import com.rosia.data.source.remote.outletDetail.OutletsDetailRemote
import com.rosia.data.source.repository.OutletDetailsRepository
import com.rosia.di.qualifiers.Locals
import com.rosia.di.qualifiers.Remotes
import com.rosia.domain.outletDetail.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rumi on 5/17/18.
 */
class OutletDetailsRepositoryImpl @Inject constructor(@Remotes private var outletsDetailRemote: OutletsDetailRemote,
                                                      @Locals private var outletsDetailLocal: OutletsDetailLocal,
                                                      @Remotes private var orderHistorysRemote: OrderHistorysRemote,
                                                      @Locals private var ordersHistoryLocal: OrdersHistoryLocal,
                                                      private var outletsMapper: OutletsMapper,
                                                      private var ordersHistoryMapper: OrdersHistoryMapper) : OutletDetailsRepository {
    override fun getOutletDetail(id: Int): Observable<OutletsResponseModel> {
//        return outletsDetailRemote.getOutletsDetail(id)
//                .flatMap(NotNullMappers())
//                .doOnNext { it -> outletsDetailLocal.insertOutletDetail(outletsMapper.mapToEntity(it.outletsDetail)) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
        var outletDetail=OutletsDetail(1,"Kirana Pasal",1,"Rumi",1,"85.3173964","27.6909657","982783947",1)
        var outletResponseModel=OutletsResponseModel(outletDetail)

        return Observable.just(outletResponseModel)
                .doOnNext{it->outletsDetailLocal.insertOutletDetail(outletsMapper.mapToEntity(it.outletsDetail))}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCallHistory(outletId: Int): Observable<CallsHistoryResponseModel> {
//        return outletsDetailRemote.getCallHistory(outletId)
//                .flatMap(NotNullMappers())
//                .doOnNext { it -> outletsDetailLocal.insertCallHistoryEntity(outletsMapper.mapCallHistoryToEntity(it.calls)) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//
        var callHistory=CallsHistory(1,1,123.13,"Order",2344)
        var callHistory2=CallsHistory(2,1,345.13,"No Sales",2344)
        var callHistory3=CallsHistory(3,1,6789.13,"No order",2344)
        var callHistory4=CallsHistory(4,2,73638.13,"Order",2344)
        var callHistoryList= mutableListOf<CallsHistory>()
        callHistoryList.add(callHistory)
        callHistoryList.add(callHistory2)
        callHistoryList.add(callHistory3)
        callHistoryList.add(callHistory4)
        var callHistoryResponseModel=CallsHistoryResponseModel(callHistoryList)
        return Observable.just(callHistoryResponseModel)
                .doOnNext{it->outletsDetailLocal.insertCallHistoryEntity(outletsMapper.mapCallHistoryToEntity(it.calls))}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    override fun getOrderHistory(outletId: Int): Observable<OrdersHistoryResponseModel> {
        var orderItemList= mutableListOf<OrdersItem>(OrdersItem(9,1,2322222,44.4,1,"Ariel 1 kg"),
                OrdersItem(10,2,1528784900960,4.99,2,"Ariel 4 kg"),
                OrdersItem(11,5,1528784900960,292.0,1,"Ariel 2 kg"),
                OrdersItem(8,6,1528784900960,88.0,1,"Ariel 3 kg"))
        var orderHistoryResponseModel=OrdersHistoryResponseModel(orderItemList)

        return Observable.just(orderHistoryResponseModel)
                .doOnNext{it->ordersHistoryLocal.insertOrderHistory(ordersHistoryMapper.mapOrderItemListToEntity(it.ordersHistoryList,outletId))}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

//        return orderHistorysRemote.getOrderHistory(outletId)
//                .flatMap(NotNullMappers())
//                .doOnNext { it -> ordersHistoryLocal.insertOrderHistory(ordersHistoryMapper.mapOrderItemListToEntity(it.ordersHistoryList, outletId)) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getOutletDetailLocal(id: Int): Observable<OutletsDetail> {
        return outletsDetailLocal.getOutletDetail(id)
                .map { outletsMapper.mapEntityToDomain(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    override fun getCallHistoryLocal(outletId: Int): Observable<List<CallsHistory>> {
        return outletsDetailLocal.getCallHistoryList(outletId)
                .map { outletsMapper.mapCallHistoryEntityToDomain(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}