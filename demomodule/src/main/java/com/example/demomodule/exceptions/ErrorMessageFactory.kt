package com.rosia.exceptions

import org.json.JSONException

/**
 * Created by subrat on 3/25/18.
 */
object ErrorMessageFactory{
    fun createMessage(e: Throwable) : String{
        var message = "Sorry, something went wrong"
        if (e is JSONException) {
            message = "Error while parsing result"
        } else if (e is NetworkNotAvailableException) {
            message = "No Internet Connection"
        }
        return message

    }
}