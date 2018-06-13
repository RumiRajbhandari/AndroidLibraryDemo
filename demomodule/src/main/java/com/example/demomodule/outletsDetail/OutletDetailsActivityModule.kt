package com.rosia.outletdetail

import com.example.demomodule.data.UsersRepositoryImpl
import com.example.demomodule.data.local.DatabasesManager
import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocal
import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocalImpl
import com.example.demomodule.data.local.outletDetail.OutletsDetailLocal
import com.example.demomodule.data.local.outletDetail.OutletsDetailLocalImpl
import com.example.demomodule.data.local.user.UsersLocal
import com.example.demomodule.data.local.user.UsersLocalImpl
import com.example.demomodule.data.mapper.OrdersHistoryMapper
import com.example.demomodule.data.mapper.OutletsMapper
import com.example.demomodule.data.remote.RetrofitApiServices
import com.example.demomodule.data.repositories.UsersRepository
import com.example.demomodule.pref.SharedPreferencesManager
import com.rosia.data.OutletDetailsRepositoryImpl
import com.rosia.data.source.remote.OrderHistorysRemote
import com.rosia.data.source.remote.OrderHistorysRemoteImpl
import com.rosia.data.source.remote.outletDetail.OutletsDetailRemote
import com.rosia.data.source.remote.outletDetail.OutletsDetailRemoteImpl
import com.rosia.data.source.repository.OutletDetailsRepository
import com.rosia.di.qualifiers.Locals
import com.rosia.di.qualifiers.Remotes
import com.rosia.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides


@Module
class OutletDetailsActivityModule {


    @ActivityScope
    @Provides
    internal fun provideOutletDetailActivity(outletsDetailsActivity: OutletsDetailsActivity): OutletDetailsPageContract.View {
        return outletsDetailsActivity
    }

    @ActivityScope
    @Provides
    internal fun provideOutletDetailPresenter(outletView: OutletDetailsPageContract.View, repository: OutletDetailsRepositoryImpl, userRepository: UsersRepositoryImpl): OutletDetailsPageContract.Presenters {
        return OutletDetailsPagePresenters(outletView, repository,userRepository)
    }

    @Remotes
    @Provides
    internal fun provideRemoteDataSource(apiServices: RetrofitApiServices): OutletsDetailRemote {
        return OutletsDetailRemoteImpl(apiServices)
    }

    @Locals
    @Provides
    internal fun provideLocalDataSource(databasesManager: DatabasesManager): OutletsDetailLocal {
        return OutletsDetailLocalImpl(databasesManager)
    }

    @Remotes
    @Provides
    internal fun provideOrderhistoryRemote(apiServices: RetrofitApiServices): OrderHistorysRemote {
        return OrderHistorysRemoteImpl(apiServices)
    }

    @Locals
    @Provides
    internal fun provideOrderHistoryLocalDataSource(databasesManager: DatabasesManager): OrdersHistoryLocal {
        return OrdersHistoryLocalImpl(databasesManager)
    }

    @Provides
    internal fun provideOutletDetailRepository(outletsDetailRemote: OutletsDetailRemote, outletsDetailLocal: OutletsDetailLocal, orderHistorysRemote: OrderHistorysRemote, ordersHistoryLocal: OrdersHistoryLocal, outletsMapper: OutletsMapper, ordersHistoryMapper: OrdersHistoryMapper): OutletDetailsRepository {
        return OutletDetailsRepositoryImpl(outletsDetailRemote, outletsDetailLocal,orderHistorysRemote,ordersHistoryLocal,outletsMapper,ordersHistoryMapper)
    }

    @Locals
    @Provides
    internal fun provideUserLocal(preferencesManager: SharedPreferencesManager): UsersLocal {
        return UsersLocalImpl(preferencesManager)
    }

    @Provides
    internal fun provideUserRepository(usersLocal: UsersLocal):UsersRepository{
        return UsersRepositoryImpl(usersLocal)
    }

}