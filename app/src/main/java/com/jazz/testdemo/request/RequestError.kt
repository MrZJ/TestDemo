package com.jazz.testdemo.request

import okio.IOException

class RequestError(private val code: Int, message: String) : IOException(message) {
    fun getCode() = code
}