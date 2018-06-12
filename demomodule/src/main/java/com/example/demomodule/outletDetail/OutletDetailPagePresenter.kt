package com.rosia.outletdetail

import com.rosia.data.source.repository.OutletDetailRepository
import com.rosia.domain.outletDetail.*
import com.rosia.exceptions.ErrorMessageFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3


class OutletDetailPagePresenter(private var outletDetailView: OutletDetailPageContract.View, private var repository: OutletDetailRepository) : OutletDetailPageContract.Presenter {

    private var disposable = CompositeDisposable()
    override fun start() {
    }

    override fun stop() {
        disposable.dispose()
    }

    override fun onGetOutletData(id: Int) {
        outletDetailView.showLoading("Loading")

        Observable.zip(
                repository.getOutletDetail(id),
                repository.getCallHistory(id),
                repository.getOrderHistory(id),
                Function3 { outletDetail: OutletDetail,
                            callHistoryList: List<CallHistory>,
                            orderItemList: List<OrderItem> ->
                    OutletEntity(outletDetail, CallHistoryData(callHistoryList))
                }
        ).subscribe(
                {
                    println("on next ${it.callHistoryData} ${it.outletDetail}")

                },
                {
                    outletDetailView.showError(ErrorMessageFactory.createMessage(it))
                }, {
            onGetOutletDetail(id)
            onGetCallHistory(id)
        }
        )
    }

    override fun onGetOutletDetail(id: Int) {
        outletDetailView.showLoading("Loading")
        disposable.add(repository.getOutletDetailLocal(id).subscribe(
                {
                    outletDetailView.getOutletDetailSuccess(it)
                }, {
            outletDetailView.showError(ErrorMessageFactory.createMessage(it))
        }))
    }

    override fun onGetCallHistory(id: Int) {
        disposable.add(repository.getCallHistoryLocal(id).subscribe(
                {
                    outletDetailView.getCallHistorySuccess(it)
                }, {
            outletDetailView.showError(ErrorMessageFactory.createMessage(it))
        }))
    }

    override fun showError(errorMessage: String) {
        outletDetailView.showError(errorMessage)
    }
}