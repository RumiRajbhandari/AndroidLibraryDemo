package com.rosia.outletdetail

import com.example.demomodule.data.repository.UsersRepository
import com.example.demomodule.entity.User
import com.rosia.data.source.repository.OutletDetailsRepository
import com.rosia.domain.outletDetail.*
import com.rosia.exceptions.ErrorMessageFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3


class OutletDetailsPagePresenters(private var outletDetailsView: OutletDetailsPageContract.View,
                                  private var repository: OutletDetailsRepository,
                                  private var usersRepository: UsersRepository) : OutletDetailsPageContract.Presenters {

    private var disposable = CompositeDisposable()
    override fun start() {
    }

    override fun stop() {
        disposable.dispose()
    }

    override fun saveUserToken(user: User) {
        usersRepository.saveUser(user)
    }

    override fun onGetOutletData(id: Int) {
        outletDetailsView.showLoading("Loading")

        Observable.zip(
                repository.getOutletDetail(id),
                repository.getCallHistory(id),
                repository.getOrderHistory(id),
                Function3 { outletResponseModel: OutletResponseModel,
                            callHistoryResponseModel: CallHistoryResponseModel,
                            orderHistoryResponseModel: OrderHistoryResponseModel ->
                    OutletEntity(outletResponseModel.outletDetail, CallHistoryData(callHistoryResponseModel.calls))
                }
        ).subscribe(
                {
                    println("on next ${it.callHistoryData} ${it.outletDetail}")

                },
                {
                    outletDetailsView.showError(ErrorMessageFactory.createMessage(it))
                }, {
            onGetOutletDetail(id)
            onGetCallHistory(id)
        }
        )
    }

    override fun onGetOutletDetail(id: Int) {
        outletDetailsView.showLoading("Loading")
        disposable.add(repository.getOutletDetailLocal(id).subscribe(
                {
                    outletDetailsView.getOutletDetailSuccess(it)
                }, {
            outletDetailsView.showError(ErrorMessageFactory.createMessage(it))
        }))
    }

    override fun onGetCallHistory(id: Int) {
        disposable.add(repository.getCallHistoryLocal(id).subscribe(
                {
                    outletDetailsView.getCallHistorySuccess(it)
                }, {
            outletDetailsView.showError(ErrorMessageFactory.createMessage(it))
        }))
    }

    override fun showError(errorMessage: String) {
        outletDetailsView.showError(errorMessage)
    }
}