package com.rosia.outletdetail

import com.example.demomodule.entity.User
import com.rosia.base.BasePresenter
import com.rosia.base.BaseView
import com.rosia.domain.outletDetail.CallHistory
import com.rosia.domain.outletDetail.CallHistoryData
import com.rosia.domain.outletDetail.OutletDetail

interface OutletDetailPageContract {
    interface View: BaseView<Presenter> {
        fun getOutletDetailSuccess(outletDetail: OutletDetail)
        fun getCallHistorySuccess(callHistoryList: List<CallHistory>)
        fun showError(errorMessage: String)
        fun showLoading(loading: String="Loading")
    }

    interface Presenter: BasePresenter {
        fun onGetOutletDetail(id: Int)
        fun onGetCallHistory(id: Int)
        fun onGetOutletData(id: Int)
        fun showError(errorMessage: String)
        fun saveUserToken(user:User)

    }
}