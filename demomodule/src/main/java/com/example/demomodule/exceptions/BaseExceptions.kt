package com.rosia.exceptions

/**
 * Created by subrat on 3/25/18.
 */
abstract class BaseExceptions : Exception() {
    internal abstract val exceptionMessage: String
}