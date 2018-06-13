package com.rosia.outletdetail

import com.example.demomodule.data.UsersRepositoryImpl
import com.example.demomodule.data.local.DatabaseManager
import com.example.demomodule.data.local.orderHistory.OrderHistoryLocal
import com.example.demomodule.data.local.orderHistory.OrderHistoryLocalImpl
import com.example.demomodule.data.local.outletDetail.OutletDetailLocal
import com.example.demomodule.data.local.outletDetail.OutletDetailLocalImpl
import com.example.demomodule.data.local.user.UserLocal
import com.example.demomodule.data.local.user.UserLocalImpl
import com.example.demomodule.data.mapper.OrderHistoryMapper
import com.example.demomodule.data.mapper.OutletMapper
import com.example.demomodule.data.remote.RetrofitApiServices
import com.example.demomodule.data.repository.UsersRepository
import com.example.demomodule.pref.SharedPreferenceManager
import com.rosia.data.OutletDetailsRepositoryImpl
import com.rosia.data.source.remote.OrderHistorysRemote
import com.rosia.data.source.remote.OrderHistorysRemoteImpl
import com.rosia.data.source.remote.outletDetail.OutletDetailRemote
import com.rosia.data.source.remote.outletDetail.OutletDetailRemoteImpl
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
    internal fun provideOutletDetailPresenter(outletView: OutletDetailsPageContract.View, repository: OutletDetailsRepositoryImpl, userRepository: UsersRepositoryImpl): OutletDetailsPageContract.Presenter {
        return OutletDetailsPagePresenter(outletView, repository,userRepository)
    }

    @Remote
    @Provides
    internal fun provideRemoteDataSource(apiServices: RetrofitApiServices): OutletDetailRemote {
        return OutletDetailRemoteImpl(apiServices)
    }

    @Local
    @Provides
    internal fun provideLocalDataSource(databaseManager: DatabaseManager): OutletDetailLocal {
        return OutletDetailLocalImpl(databaseManager)
    }

    @Remote
    @Provides
    internal fun provideOrderhistoryRemote(apiServices: RetrofitApiServices): OrderHistorysRemote {
        return OrderHistorysRemoteImpl(apiServices)
    }

    @Local
    @Provides
    internal fun provideOrderHistoryLocalDataSource(databaseManager: DatabaseManager): OrderHistoryLocal {
        return OrderHistoryLocalImpl(databaseManager)
    }

    @Provides
    internal fun provideOutletDetailRepository(outletDetailRemote: OutletDetailRemote, outletDetailLocal: OutletDetailLocal, orderHistorysRemote: OrderHistorysRemote, orderHistoryLocal: OrderHistoryLocal, outletMapper: OutletMapper, orderHistoryMapper: OrderHistoryMapper): OutletDetailsRepository {
        return OutletDetailsRepositoryImpl(outletDetailRemote, outletDetailLocal,orderHistorysRemote,orderHistoryLocal,outletMapper,orderHistoryMapper)
    }

    @Local
    @Provides
    internal fun provideUserLocal(preferenceManager: SharedPreferenceManager): UserLocal {
        return UserLocalImpl(preferenceManager)
    }

    @Provides
    internal fun provideUserRepository(userLocal: UserLocal):UsersRepository{
        return UsersRepositoryImpl(userLocal)
    }

}