package com.jazz.testdemo.request

const val RESPONSE_SUCCESS = 10000

data class HttpStatus(val code: Int, val msg: String) {
    fun requestSuccess() = code == RESPONSE_SUCCESS
}