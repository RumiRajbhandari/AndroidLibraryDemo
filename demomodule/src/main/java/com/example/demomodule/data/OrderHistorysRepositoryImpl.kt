package com.rosia.data

import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocal
import com.example.demomodule.data.mapper.OrdersHistoryMapper
import com.rosia.data.source.remote.OrderHistorysRemote
import com.rosia.data.source.repository.OrderHistorysRepository
import com.rosia.di.qualifiers.Locals
import com.rosia.di.qualifiers.Remotes
import com.rosia.domain.outletDetail.OrdersHistory
import com.rosia.domain.outletDetail.OrdersItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrderHistorysRepositoryImpl @Inject constructor(@Remotes private var orderHistorysRemote: OrderHistorysRemote,
                                                      @Locals private var ordersHistoryLocal: OrdersHistoryLocal,
                                                      private var ordersHistoryMapper: OrdersHistoryMapper) : OrderHistorysRepository {

    override fun getOrderHistory(outletId: Int): Observable<List<OrdersItem>> {
        var orderItemList = mutableListOf<OrdersItem>(OrdersItem(1, 1, 2322222, 44.4, 223, "Ariel 1 kg"), OrdersItem(2, 2, 8373635, 44.99, 223, "Ariel 1 kg"))
        var orderHistoryList = mutableListOf(OrdersHistory("2017-01-01", orderItemList), OrdersHistory("2017-02-01", orderItemList))
//        return orderHistorysRemote.getOrderHistory(routeId)
//                .flatMap(NotNullMappers())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
        return Observable.just(orderItemList.toList())
                .doOnNext { it -> ordersHistoryLocal.insertOrderHistory(ordersHistoryMapper.mapOrderItemListToEntity(it,outletId = outletId)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getOrderHistoryLocal(outletId: Int): Observable<List<OrdersItem>> {
        return ordersHistoryLocal.getOrderHistory(outletId)
                .map { it -> ordersHistoryMapper.mapOrderItemEntityListToDomain(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}