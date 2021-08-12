package com.jazz.testdemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jazz.testdemo.R
import com.jazz.testdemo.entity.StockDetails

class StockListAdapter(private val mContext: Context) :
    RecyclerView.Adapter<StockListAdapter.VH>() {
    private var mData: List<StockDetails>? = null

    class VH(mView: View) : RecyclerView.ViewHolder(mView) {
        val companyTv: TextView = mView.findViewById(R.id.companyTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(mContext).inflate(R.layout.item_stock, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        mData?.let {
            holder.companyTv.text = it[position].CompanyName
        } ?: kotlin.run {
            holder.companyTv.text = ""
        }
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(list: List<StockDetails>) {
        if (list == mData) {
            return
        }
        mData = list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        if (mData == null) {
            return
        }
        mData = null
        notifyDataSetChanged()
    }
}