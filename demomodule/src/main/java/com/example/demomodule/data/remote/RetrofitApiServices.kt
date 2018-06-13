package com.example.demomodule.data.remote

import com.rosia.domain.outletDetail.CallsHistoryResponseModel
import com.rosia.domain.outletDetail.OrdersHistoryResponseModel
import com.rosia.domain.outletDetail.OutletsResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * this layer is responsible for network handling using retrofit2
 */
interface RetrofitApiServices {


    @GET("outlet/detail")
    fun getOutletDetail(@Query("outlet_id") outletId:Int):Observable<BaseResponses<OutletsResponseModel>>

    @GET("calls/detail?projection=outlet-call-history")
    fun getCallHistoryData( @Query("outlet_id") outletId: Int, @Query("timePeriod") timeperiod: Int): Observable<BaseResponses<CallsHistoryResponseModel>>

    @GET("sales/detail?type=received")
    fun getOrderHistoryData(@Query("outlet_id") routeId: Int, @Query("timePeriod") timeperiod: Int): Observable<BaseResponses<OrdersHistoryResponseModel>>

}