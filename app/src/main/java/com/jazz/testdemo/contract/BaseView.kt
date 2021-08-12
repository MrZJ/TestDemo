package com.jazz.testdemo.contract

interface BaseView<P : BasePresenter> {
    fun showLoading()
    fun hideLoading()
}