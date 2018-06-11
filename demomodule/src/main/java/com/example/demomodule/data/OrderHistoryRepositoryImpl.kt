package com.rosia.data

import com.example.demomodule.data.remote.NotNullMapper
import com.rosia.data.source.remote.OrderHistoryRemote
import com.rosia.data.source.repository.OrderHistoryRepository
import com.rosia.di.qualifiers.Remote
import com.rosia.domain.outletDetail.OrderHistory
import com.rosia.domain.outletDetail.OrderHistoryResponseModel
import com.rosia.domain.outletDetail.OrderItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrderHistoryRepositoryImpl @Inject constructor(@Remote private var orderHistoryRemoteRepository: OrderHistoryRemote) : OrderHistoryRepository {

    override fun getOrderHistory(routeId: Int): Observable<OrderHistoryResponseModel> {
        var orderItemList= mutableListOf<OrderItem>(OrderItem(1,1,2322222,44.4,223,"Ariel 1 kg"), OrderItem(1,1,8373635,44.99,223,"Ariel 1 kg"))
        var orderHistoryList= mutableListOf(OrderHistory("2017-01-01",orderItemList),OrderHistory("2017-02-01",orderItemList))
//        return orderHistoryRemoteRepository.getOrderHistory(routeId)
//                .flatMap(NotNullMapper())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
        return Observable.just(OrderHistoryResponseModel(orderItemList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}