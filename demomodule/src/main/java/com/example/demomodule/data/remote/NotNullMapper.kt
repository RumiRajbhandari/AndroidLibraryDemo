package com.example.demomodule.data.remote

import com.rosia.exceptions.FailedResponseException
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function

/**
 * Created by krishna on 2/27/18.
 * Project Name piecm-android
 */

class NotNullMapper<T> : Function<BaseResponse<T>, Observable<T>> {
    @Throws(Exception::class)
    override fun apply(@NonNull baseResponse: BaseResponse<T>): Observable<T> {
//        if (baseResponse.statusCode != 200)
//            return Observable.error(FailedResponseException(baseResponse.statusCode,
//                    baseResponse.statusMessage.toString()))
        val item = baseResponse.response
        return if (item == null)
            Observable.error(NullPointerException("BaseResponse --> Response is  null"))
        else
            Observable.just(item)
    }
}
