package com.jazz.testdemo.contract

import com.jazz.testdemo.entity.StockDetails

interface StockListContract {
    interface View : BaseView<Presenter> {
        fun showLoadListSuccess(list: List<StockDetails>)
        fun showLoadListFail()
        fun clearListData()
    }

    abstract class Presenter : BasePresenter() {
        abstract fun getStockList(searchText: String?)
    }
}