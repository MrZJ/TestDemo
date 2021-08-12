package com.jazz.testdemo.api

import com.jazz.testdemo.entity.StockDetails
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Url

interface StockApi {
    //https://myibd.investors.com/searchapi/predictivesearch/A/10
    @GET
    @Headers("Content-Type:application/json")
    fun getStockDetailList(@Url url: String): Observable<List<StockDetails>>

    @GET("searchapi/predictivesearch/{searchText}/{searchRange}")
    @Headers("Content-Type:application/json")
    fun getStockDetailList2(
        @Path("searchText") searchText: String,
        @Path("searchRange") searchRange: Int
    ): Observable<List<StockDetails>>
}