package com.jazz.testdemo.repository

import com.jazz.testdemo.api.StockApi
import com.jazz.testdemo.constat.Constant
import com.jazz.testdemo.entity.StockDetails
import com.jazz.testdemo.gsonconvert.GsonConverterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory


class StockRepository {

    private var mApi: StockApi = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(Constant.getOkhttpClient())
        .build()
        .create(StockApi::class.java)

    fun getStockList(
        searchText: String,
        searchRange: Int = Constant.STOCK_LIST_SEARCH_RANGE
    ): Observable<List<StockDetails>> {
//        val url = Constant.BASE_URL + "searchapi/predictivesearch/" + "${searchText}/${searchRange}"
//        return mApi.getStockDetailList(url)
        return mApi.getStockDetailList2(searchText, searchRange)
    }
}