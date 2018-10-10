package com.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.view.R

/**
 * 项目名称:AutoVerticalRollRecyclerView
 * Author：mj
 * Time: 2018/10/9 15:13
 * 描述：RecyclerView的Adapter
 * Version:
 */
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