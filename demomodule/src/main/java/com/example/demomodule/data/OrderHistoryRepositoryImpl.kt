package com.rosia.data

import com.example.demomodule.data.local.orderHistory.OrderHistoryLocal
import com.example.demomodule.data.mapper.OrderHistoryMapper
import com.rosia.data.source.remote.OrderHistoryRemote
import com.rosia.data.source.repository.OrderHistoryRepository
import com.rosia.di.qualifiers.Local
import com.rosia.di.qualifiers.Remote
import com.rosia.domain.outletDetail.OrderHistory
import com.rosia.domain.outletDetail.OrderItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrderHistoryRepositoryImpl @Inject constructor(@Remote private var orderHistoryRemote: OrderHistoryRemote,
                                                     @Local private var orderHistoryLocal: OrderHistoryLocal,
                                                     private var orderHistoryMapper: OrderHistoryMapper) : OrderHistoryRepository {

    override fun getOrderHistory(outletId: Int): Observable<List<OrderItem>> {
        var orderItemList = mutableListOf<OrderItem>(OrderItem(1, 1, 2322222, 44.4, 223, "Ariel 1 kg"), OrderItem(2, 2, 8373635, 44.99, 223, "Ariel 1 kg"))
        var orderHistoryList = mutableListOf(OrderHistory("2017-01-01", orderItemList), OrderHistory("2017-02-01", orderItemList))
//        return orderHistoryRemote.getOrderHistory(routeId)
//                .flatMap(NotNullMapper())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
        return Observable.just(orderItemList.toList())
                .doOnNext { it -> orderHistoryLocal.insertOrderHistory(orderHistoryMapper.mapOrderItemListToEntity(it,outletId = outletId)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getOrderHistoryLocal(outletId: Int): Observable<List<OrderItem>> {
        return orderHistoryLocal.getOrderHistory(outletId)
                .map { it -> orderHistoryMapper.mapOrderItemEntityListToDomain(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}