package com.example.demomodule.data.remote

import com.rosia.domain.outletDetail.CallHistoryResponseModel
import com.rosia.domain.outletDetail.OrderHistoryResponseModel
import com.rosia.domain.outletDetail.OutletResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * this layer is responsible for network handling using retrofit2
 */
interface RetrofitApiService {


    @GET("outlet/detail")
    fun getOutletDetail(@Query("outlet_id") outletId:Int):Observable<BaseResponse<OutletResponseModel>>

    @GET("calls/detail?projection=outlet-call-history")
    fun getCallHistoryData( @Query("outlet_id") outletId: Int, @Query("timePeriod") timeperiod: Int): Observable<BaseResponse<CallHistoryResponseModel>>

    @GET("sales/detail?type=received")
    fun getOrderHistoryData(@Query("outlet_id") routeId: Int, @Query("timePeriod") timeperiod: Int): Observable<BaseResponse<OrderHistoryResponseModel>>

}