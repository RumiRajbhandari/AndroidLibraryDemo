package com.rosia.outletdetail

import com.example.demomodule.data.UserRepositoryImpl
import com.example.demomodule.data.local.DatabaseManager
import com.example.demomodule.data.local.orderHistory.OrderHistoryLocal
import com.example.demomodule.data.local.orderHistory.OrderHistoryLocalImpl
import com.example.demomodule.data.local.outletDetail.OutletDetailLocal
import com.example.demomodule.data.local.outletDetail.OutletDetailLocalImpl
import com.example.demomodule.data.local.user.UserLocal
import com.example.demomodule.data.local.user.UserLocalImpl
import com.example.demomodule.data.mapper.OrderHistoryMapper
import com.example.demomodule.data.mapper.OutletMapper
import com.example.demomodule.data.remote.RetrofitApiService
import com.example.demomodule.data.repository.UserRepository
import com.example.demomodule.pref.SharedPreferenceManager
import com.rosia.data.OutletDetailRepositoryImpl
import com.rosia.data.source.remote.OrderHistoryRemote
import com.rosia.data.source.remote.OrderHistoryRemoteImpl
import com.rosia.data.source.remote.outletDetail.OutletDetailRemote
import com.rosia.data.source.remote.outletDetail.OutletDetailRemoteImpl
import com.rosia.data.source.repository.OutletDetailRepository
import com.rosia.di.qualifiers.Local
import com.rosia.di.qualifiers.Remote
import com.rosia.di.scopes.ActivityScoped
import dagger.Module
import dagger.Provides


@Module
class OutletDetailActivityModule {


    @ActivityScoped
    @Provides
    internal fun provideOutletDetailActivity(outletDetailActivity: OutletDetailActivity): OutletDetailPageContract.View {
        return outletDetailActivity
    }

    @ActivityScoped
    @Provides
    internal fun provideOutletDetailPresenter(outletView: OutletDetailPageContract.View, repository: OutletDetailRepositoryImpl, userRepository: UserRepositoryImpl): OutletDetailPageContract.Presenter {
        return OutletDetailPagePresenter(outletView, repository,userRepository)
    }

    @Remote
    @Provides
    internal fun provideRemoteDataSource(apiService: RetrofitApiService): OutletDetailRemote {
        return OutletDetailRemoteImpl(apiService)
    }

    @Local
    @Provides
    internal fun provideLocalDataSource(databaseManager: DatabaseManager): OutletDetailLocal {
        return OutletDetailLocalImpl(databaseManager)
    }

    @Remote
    @Provides
    internal fun provideOrderhistoryRemote(apiService: RetrofitApiService): OrderHistoryRemote {
        return OrderHistoryRemoteImpl(apiService)
    }

    @Local
    @Provides
    internal fun provideOrderHistoryLocalDataSource(databaseManager: DatabaseManager): OrderHistoryLocal {
        return OrderHistoryLocalImpl(databaseManager)
    }

    @Provides
    internal fun provideOutletDetailRepository(outletDetailRemote: OutletDetailRemote, outletDetailLocal: OutletDetailLocal, orderHistoryRemote: OrderHistoryRemote,orderHistoryLocal: OrderHistoryLocal, outletMapper: OutletMapper, orderHistoryMapper: OrderHistoryMapper): OutletDetailRepository {
        return OutletDetailRepositoryImpl(outletDetailRemote, outletDetailLocal,orderHistoryRemote,orderHistoryLocal,outletMapper,orderHistoryMapper)
    }

    @Local
    @Provides
    internal fun provideUserLocal(preferenceManager: SharedPreferenceManager): UserLocal {
        return UserLocalImpl(preferenceManager)
    }

    @Provides
    internal fun provideUserRepository(userLocal: UserLocal):UserRepository{
        return UserRepositoryImpl(userLocal)
    }

}