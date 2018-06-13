package com.rosia.orderhistory

import com.example.demomodule.data.local.DatabasesManager
import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocal
import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocalImpl
import com.example.demomodule.data.mapper.OrdersHistoryMapper
import com.example.demomodule.data.remote.RetrofitApiServices
import com.rosia.data.OrderHistorysRepositoryImpl
import com.rosia.data.source.remote.OrderHistorysRemote
import com.rosia.data.source.remote.OrderHistorysRemoteImpl
import com.rosia.data.source.repository.OrderHistorysRepository
import com.rosia.di.qualifiers.Locals
import com.rosia.di.qualifiers.Remotes
import com.rosia.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides


@Module
class OrdersHistoryActivityModule {

    @ActivityScope
    @Provides
    fun provideOrderHistoryActivity(ordersHistoryActivity: OrdersHistoryActivity): OrdersHistoryContract.View {
        return ordersHistoryActivity
    }

    @ActivityScope
    @Provides
    fun provideOrderHistoryPresenter(view: OrdersHistoryContract.View, repository: OrderHistorysRepositoryImpl): OrdersHistoryContract.Presenters {
        return OrdersHistoryPresenters(view, repository)
    }

    @Provides
    @Remotes
    fun provideUserRemoteDataSource(retrofitApiServices: RetrofitApiServices): OrderHistorysRemote {
        return OrderHistorysRemoteImpl(retrofitApiServices)
    }

    @Locals
    @Provides
    fun provideOrderHistoryLocalDataSource(databasesManager: DatabasesManager):OrdersHistoryLocal{
        return OrdersHistoryLocalImpl(databasesManager)
    }

    @Provides
    internal fun provideOrderHistoryRepository(orderHistorysRemote: OrderHistorysRemote, ordersHistoryLocal: OrdersHistoryLocal, ordersHistoryMapper: OrdersHistoryMapper): OrderHistorysRepository {
        return OrderHistorysRepositoryImpl(orderHistorysRemote, ordersHistoryLocal,ordersHistoryMapper)
    }
}