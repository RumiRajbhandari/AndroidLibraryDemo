package com.rosia.outletdetail

import com.rosia.data.source.repository.OutletDetailRepository
import com.rosia.domain.outletDetail.*
import com.rosia.exceptions.ErrorMessageFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction


class OutletDetailPagePresenter(private var outletDetailView: OutletDetailPageContract.View, private var repository: OutletDetailRepository) : OutletDetailPageContract.Presenter {

    private var disposable = CompositeDisposable()
    override fun start() {
    }

    override fun stop() {
        disposable.dispose()
    }


    override fun onGetCallHistory(id: Int) {
        println("get call history presenter")
        outletDetailView.showLoading("Loading")

        Observable.zip(
                repository.getOutletDetail(id),
                repository.getCallHistory(id),
                BiFunction{ outletResponseModel:OutletResponseModel,
                               callHistoryResponseModel:CallHistoryResponseModel ->
                    OutletEntity(outletResponseModel.outletDetail,CallHistoryData(callHistoryResponseModel.calls))
                }
        ).subscribe(
                {
                    println("on next ${it.callHistoryData} ${it.outletDetail}")
                    outletDetailView.getCallHistorySuccess(it.callHistoryData)
                    outletDetailView.getOutletDetailSuccess(it.outletDetail)
                },
                {
                    outletDetailView.showError(ErrorMessageFactory.createMessage(it))
                },{}
        )
    }

    override fun onGetOutletDetail(id: Int) {
        outletDetailView.showLoading("Loading")
        disposable.add(repository.getOutletDetail(id).subscribe(
                {
                    outletDetailView.getOutletDetailSuccess(it.outletDetail)
                }, {
            outletDetailView.showError(ErrorMessageFactory.createMessage(it))
        }
        )
        )
    }

    override fun showError(errorMessage: String) {
        outletDetailView.showError(errorMessage)
    }
}