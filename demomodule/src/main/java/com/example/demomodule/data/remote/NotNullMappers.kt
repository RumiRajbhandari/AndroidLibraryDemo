package com.example.demomodule.data.remote

import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function

/**
 * Created by krishna on 2/27/18.
 * Project Name piecm-android
 */

class NotNullMappers<T> : Function<BaseResponses<T>, Observable<T>> {
    @Throws(Exception::class)
    override fun apply(@NonNull baseResponses: BaseResponses<T>): Observable<T> {
//        if (baseResponses.statusCode != 200)
//            return Observable.error(FailedResponseException(baseResponses.statusCode,
//                    baseResponses.statusMessage.toString()))
        val item = baseResponses.response
        return if (item == null)
            Observable.error(NullPointerException("BaseResponses --> Response is  null"))
        else
            Observable.just(item)
    }
}
