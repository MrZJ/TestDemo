package com.jazz.testdemo

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.jazz.testdemo.adapter.StockListAdapter
import com.jazz.testdemo.contract.StockListContract
import com.jazz.testdemo.databinding.ActivityMainBinding
import com.jazz.testdemo.entity.StockDetails
import com.jazz.testdemo.presenter.StockListPresenter

class MainActivity : AppCompatActivity(), StockListContract.View {

    private lateinit var mBinding: ActivityMainBinding
    private val mPresenter: StockListPresenter by lazy {
        StockListPresenter(this)
    }
    private val mAdapter: StockListAdapter by lazy {
        StockListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        mBinding.searchEt.doOnTextChanged { text, _, _, _ ->
            mPresenter.getStockList(text.toString())
        }
        mBinding.listRecyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.listRecyclerView.adapter = mAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    override fun showLoadListSuccess(list: List<StockDetails>) {
        if (list.isEmpty()) {
            showTips(true, R.string.input_something)
        } else {
            showTips(false)
        }
        mAdapter.refreshData(list)
    }


    override fun clearListData() {
        mAdapter.clearData()
        mBinding.tipsTv.apply {
            visibility = View.VISIBLE
            text = resources.getString(R.string.input_something)
        }
    }

    override fun showLoadListFail() {
        mAdapter.clearData()
        showTips(true, R.string.request_fail)
    }

    private fun showTips(showTips: Boolean, @StringRes strId: Int = -1) {
        if (showTips) {
            mBinding.tipsTv.visibility = View.VISIBLE
            if (strId != -1)
                mBinding.tipsTv.text = resources.getText(strId)
        } else {
            mBinding.tipsTv.visibility = View.GONE
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}