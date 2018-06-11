package com.rosia.orderhistory

import com.example.demomodule.data.remote.RetrofitApiService
import com.rosia.data.OrderHistoryRepositoryImpl
import com.rosia.data.source.remote.OrderHistoryRemote
import com.rosia.data.source.remote.OrderHistoryRemoteImpl
import com.rosia.data.source.repository.OrderHistoryRepository
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

    @Provides
    internal fun provideOrderHistoryRepository(orderHistoryRemote: OrderHistoryRemote): OrderHistoryRepository {
        return OrderHistoryRepositoryImpl(orderHistoryRemote)
    }
}