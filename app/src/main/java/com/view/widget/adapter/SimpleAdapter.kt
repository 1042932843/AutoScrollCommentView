package com.view.widget.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.view.R


class SimpleAdapter(list: MutableList<String>, mContext: Context) : RecyclerView.Adapter<SimpleAdapterViewHolder>() {

    /**数据源*/
    private var list: MutableList<String> = list
    /**上下文*/
    private var mContext: Context = mContext

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SimpleAdapterViewHolder {
        val view=LayoutInflater.from(mContext).inflate(R.layout.item_layout,p0,false)
        return SimpleAdapterViewHolder(view)
    }

    /**
     * 为了实现无线循环，需要将数据的源的个数设置为比较大的值
     */
    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(p0: SimpleAdapterViewHolder, p1: Int) {
        p0.mTvData?.text=list!![p1%list!!.size]
    }
}