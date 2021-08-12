package com.jazz.testdemo.presenter

import com.jazz.testdemo.contract.StockListContract
import com.jazz.testdemo.repository.StockRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class StockListPresenter(private val mView: StockListContract.View) :
    StockListContract.Presenter() {
    private val mRepository: StockRepository by lazy {
        StockRepository()
    }
    private var stockListDisposable: Disposable? = null

    override fun getStockList(searchText: String?) {
        //remove last unused request
        stockListDisposable?.dispose()
        //check data
        if (searchText.isNullOrEmpty()) {
            mView.clearListData()
            return
        }
        stockListDisposable = mRepository.getStockList(searchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mView.showLoadListSuccess(it)
            }, {
                it.printStackTrace()
                mView.showLoadListFail()
            })
        mDisposable.add(stockListDisposable)
    }
}