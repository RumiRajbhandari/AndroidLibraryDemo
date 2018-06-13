package com.rosia.outletdetail

import com.example.demomodule.data.UsersRepositoryImpl
import com.example.demomodule.data.local.DatabaseManager
import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocal
import com.example.demomodule.data.local.orderHistory.OrdersHistoryLocalImpl
import com.example.demomodule.data.local.outletDetail.OutletsDetailLocal
import com.example.demomodule.data.local.outletDetail.OutletsDetailLocalImpl
import com.example.demomodule.data.local.user.UsersLocal
import com.example.demomodule.data.local.user.UsersLocalImpl
import com.example.demomodule.data.mapper.OrdersHistoryMapper
import com.example.demomodule.data.mapper.OutletsMapper
import com.example.demomodule.data.remote.RetrofitApiServices
import com.example.demomodule.data.repository.UsersRepository
import com.example.demomodule.pref.SharedPreferenceManager
import com.rosia.data.OutletDetailsRepositoryImpl
import com.rosia.data.source.remote.OrderHistorysRemote
import com.rosia.data.source.remote.OrderHistorysRemoteImpl
import com.rosia.data.source.remote.outletDetail.OutletsDetailRemote
import com.rosia.data.source.remote.outletDetail.OutletsDetailRemoteImpl
import com.rosia.data.source.repository.OutletDetailsRepository
import com.rosia.di.qualifiers.Local
import com.rosia.di.qualifiers.Remote
import com.rosia.di.scopes.ActivityScoped
import dagger.Module
import dagger.Provides


@Module
class OutletDetailsActivityModule {


    @ActivityScoped
    @Provides
    internal fun provideOutletDetailActivity(outletsDetailsActivity: OutletsDetailsActivity): OutletDetailsPageContract.View {
        return outletsDetailsActivity
    }

    @ActivityScoped
    @Provides
    internal fun provideOutletDetailPresenter(outletView: OutletDetailsPageContract.View, repository: OutletDetailsRepositoryImpl, userRepository: UsersRepositoryImpl): OutletDetailsPageContract.Presenters {
        return OutletDetailsPagePresenters(outletView, repository,userRepository)
    }

    @Remote
    @Provides
    internal fun provideRemoteDataSource(apiServices: RetrofitApiServices): OutletsDetailRemote {
        return OutletsDetailRemoteImpl(apiServices)
    }

    @Local
    @Provides
    internal fun provideLocalDataSource(databaseManager: DatabaseManager): OutletsDetailLocal {
        return OutletsDetailLocalImpl(databaseManager)
    }

    @Remote
    @Provides
    internal fun provideOrderhistoryRemote(apiServices: RetrofitApiServices): OrderHistorysRemote {
        return OrderHistorysRemoteImpl(apiServices)
    }

    @Local
    @Provides
    internal fun provideOrderHistoryLocalDataSource(databaseManager: DatabaseManager): OrdersHistoryLocal {
        return OrdersHistoryLocalImpl(databaseManager)
    }

    @Provides
    internal fun provideOutletDetailRepository(outletsDetailRemote: OutletsDetailRemote, outletsDetailLocal: OutletsDetailLocal, orderHistorysRemote: OrderHistorysRemote, ordersHistoryLocal: OrdersHistoryLocal, outletsMapper: OutletsMapper, ordersHistoryMapper: OrdersHistoryMapper): OutletDetailsRepository {
        return OutletDetailsRepositoryImpl(outletsDetailRemote, outletsDetailLocal,orderHistorysRemote,ordersHistoryLocal,outletsMapper,ordersHistoryMapper)
    }

    @Local
    @Provides
    internal fun provideUserLocal(preferenceManager: SharedPreferenceManager): UsersLocal {
        return UsersLocalImpl(preferenceManager)
    }

    @Provides
    internal fun provideUserRepository(usersLocal: UsersLocal):UsersRepository{
        return UsersRepositoryImpl(usersLocal)
    }

}