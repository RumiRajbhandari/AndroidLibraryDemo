package com.rosia.exceptions

import java.io.IOException

/**
 * Created by subrat on 3/25/18.
 */

class FailedResponseException(val status: Int, override val message : String) : IOException(message)
class NetworkNotAvailableException : IOException()