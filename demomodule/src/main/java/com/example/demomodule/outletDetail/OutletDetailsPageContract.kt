package com.rosia.outletdetail

import com.example.demomodule.entity.User
import com.rosia.base.BasesPresenter
import com.rosia.base.BasesView
import com.rosia.domain.outletDetail.CallsHistory
import com.rosia.domain.outletDetail.OutletsDetail

interface OutletDetailsPageContract {
    interface View: BasesView<Presenters> {
        fun getOutletDetailSuccess(outletsDetail: OutletsDetail)
        fun getCallHistorySuccess(callsHistoryList: List<CallsHistory>)
        fun showError(errorMessage: String)
        fun showLoading(loading: String="Loading")
    }

    interface Presenters: BasesPresenter {
        fun onGetOutletDetail(id: Int)
        fun onGetCallHistory(id: Int)
        fun onGetOutletData(id: Int)
        fun showError(errorMessage: String)
        fun saveUserToken(user:User)

    }
}