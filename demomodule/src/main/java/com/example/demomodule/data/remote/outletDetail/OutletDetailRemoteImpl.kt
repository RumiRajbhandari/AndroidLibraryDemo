package com.rosia.data.source.remote.outletDetail

import com.example.demomodule.data.remote.BaseResponse
import com.example.demomodule.data.remote.RetrofitApiService
import com.rosia.domain.outletDetail.CallHistoryResponseModel
import com.rosia.domain.outletDetail.OutletResponseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rumi on 5/17/18.
 */
class OutletDetailRemoteImpl @Inject constructor(private var apiService: RetrofitApiService):OutletDetailRemote {
    override fun getOutletDetail(id: Int): Observable<BaseResponse<OutletResponseModel>> {
        return apiService.getOutletDetail(id)
    }

    override fun getCallHistory(outletId: Int): Observable<BaseResponse<CallHistoryResponseModel>> {
        return apiService.getCallHistoryData(outletId,3)
    }

}