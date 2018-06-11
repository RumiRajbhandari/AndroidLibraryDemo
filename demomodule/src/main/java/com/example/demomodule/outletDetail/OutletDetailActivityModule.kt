package com.rosia.outletdetail

import com.example.demomodule.data.remote.RetrofitApiService
import com.rosia.data.OutletDetailRepositoryImpl
import com.rosia.data.source.remote.outletDetail.OutletDetailRemote
import com.rosia.data.source.remote.outletDetail.OutletDetailRemoteImpl
import com.rosia.data.source.repository.OutletDetailRepository
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
    internal fun provideOutletDetailPresenter(outletView: OutletDetailPageContract.View, repository: OutletDetailRepositoryImpl): OutletDetailPageContract.Presenter {
        return OutletDetailPagePresenter(outletView, repository)
    }

    @Remote
    @Provides
    internal fun provideRemoteDataSource(apiService: RetrofitApiService): OutletDetailRemote {
        return OutletDetailRemoteImpl(apiService)
    }

    @Provides
    internal fun provideOutletDetailRepository(outletDetailRemote: OutletDetailRemote): OutletDetailRepository {
        return OutletDetailRepositoryImpl(outletDetailRemote)
    }

}