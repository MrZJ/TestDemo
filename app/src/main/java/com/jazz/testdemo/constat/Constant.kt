package com.jazz.testdemo.constat

import okhttp3.Call
import okhttp3.EventListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * common source
 */
object Constant {
    const val BASE_URL = "https://myibd.investors.com/"
    const val STOCK_LIST_SEARCH_RANGE = 10

    //optimize request link reuse
    fun getOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .hostnameVerifier { _, _ -> true }
        .eventListener(object : EventListener(){
            override fun dnsStart(call: Call, domainName: String) {
                super.dnsStart(call, domainName)
            }

        })
        .build()

}