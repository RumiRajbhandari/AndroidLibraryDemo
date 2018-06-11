package com.rosia.outletdetail

import com.rosia.base.BasePresenter
import com.rosia.base.BaseView
import com.rosia.domain.outletDetail.CallHistoryData
import com.rosia.domain.outletDetail.OutletDetail

interface OutletDetailPageContract {
    interface View: BaseView<Presenter> {
        fun getOutletDetailSuccess(outletDetail: OutletDetail)
        fun getCallHistorySuccess(callHistoryData: CallHistoryData)
        fun showError(errorMessage: String)
        fun showLoading(loading: String="Loading")
    }

    interface Presenter: BasePresenter {
        fun onGetOutletDetail(id: Int)
        fun onGetCallHistory(id: Int)
        fun showError(errorMessage: String)

    }
}