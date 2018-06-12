package com.rosia.orderhistory

import com.example.demomodule.data.local.DatabaseManager
import com.example.demomodule.data.local.orderHistory.OrderHistoryLocal
import com.example.demomodule.data.local.orderHistory.OrderHistoryLocalImpl
import com.example.demomodule.data.mapper.OrderHistoryMapper
import com.example.demomodule.data.mapper.OutletMapper
import com.example.demomodule.data.remote.RetrofitApiService
import com.rosia.data.OrderHistoryRepositoryImpl
import com.rosia.data.source.remote.OrderHistoryRemote
import com.rosia.data.source.remote.OrderHistoryRemoteImpl
import com.rosia.data.source.repository.OrderHistoryRepository
import com.rosia.di.qualifiers.Local
import com.rosia.di.qualifiers.Remote
import com.rosia.di.scopes.ActivityScoped
import dagger.Module
import dagger.Provides


@Module
class OrderHistoryActivityModule {

    @ActivityScoped
    @Provides
    fun provideOrderHistoryActivity(orderHistoryActivity: OrderHistoryActivity): OrderHistoryContract.View {
        return orderHistoryActivity
    }

    @ActivityScoped
    @Provides
    fun provideOrderHistoryPresenter(view: OrderHistoryContract.View, repository: OrderHistoryRepositoryImpl): OrderHistoryContract.Presenter {
        return OrderHistoryPresenter(view, repository)
    }

    @Provides
    @Remote
    fun provideUserRemoteDataSource(retrofitApiService: RetrofitApiService): OrderHistoryRemote {
        return OrderHistoryRemoteImpl(retrofitApiService)
    }

    @Local
    @Provides
    fun provideOrderHistoryLocalDataSource(databaseManager: DatabaseManager):OrderHistoryLocal{
        return OrderHistoryLocalImpl(databaseManager)
    }

    @Provides
    internal fun provideOrderHistoryRepository(orderHistoryRemote: OrderHistoryRemote, orderHistoryLocal: OrderHistoryLocal, orderHistoryMapper: OrderHistoryMapper): OrderHistoryRepository {
        return OrderHistoryRepositoryImpl(orderHistoryRemote, orderHistoryLocal,orderHistoryMapper)
    }
}