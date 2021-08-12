package com.jazz.testdemo.contract

import io.reactivex.rxjava3.disposables.CompositeDisposable


abstract class BasePresenter {

    protected val mDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }


    fun onDestroy() {
        mDisposable.clear()
    }
}