package com.rosia.orderhistory

import com.example.demomodule.data.local.DatabaseManager
import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocal
import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocalImpl
import com.example.demomodule.data.mapper.OrdersHistoryMapper
import com.example.demomodule.data.remote.RetrofitApiServices
import com.rosia.data.OrderHistorysRepositoryImpl
import com.rosia.data.source.remote.OrderHistorysRemote
import com.rosia.data.source.remote.OrderHistorysRemoteImpl
import com.rosia.data.source.repository.OrderHistorysRepository
import com.rosia.di.qualifiers.Local
import com.rosia.di.qualifiers.Remote
import com.rosia.di.scopes.ActivityScoped
import dagger.Module
import dagger.Provides


@Module
class OrdersHistoryActivityModule {

    @ActivityScoped
    @Provides
    fun provideOrderHistoryActivity(ordersHistoryActivity: OrdersHistoryActivity): OrdersHistoryContract.View {
        return ordersHistoryActivity
    }

    @ActivityScoped
    @Provides
    fun provideOrderHistoryPresenter(view: OrdersHistoryContract.View, repository: OrderHistorysRepositoryImpl): OrdersHistoryContract.Presenters {
        return OrdersHistoryPresenters(view, repository)
    }

    @Provides
    @Remote
    fun provideUserRemoteDataSource(retrofitApiServices: RetrofitApiServices): OrderHistorysRemote {
        return OrderHistorysRemoteImpl(retrofitApiServices)
    }

    @Local
    @Provides
    fun provideOrderHistoryLocalDataSource(databaseManager: DatabaseManager):OrdersHistoryLocal{
        return OrdersHistoryLocalImpl(databaseManager)
    }

    @Provides
    internal fun provideOrderHistoryRepository(orderHistorysRemote: OrderHistorysRemote, ordersHistoryLocal: OrdersHistoryLocal, ordersHistoryMapper: OrdersHistoryMapper): OrderHistorysRepository {
        return OrderHistorysRepositoryImpl(orderHistorysRemote, ordersHistoryLocal,ordersHistoryMapper)
    }
}