package com.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.view.R


/**
 * 项目名称:AutoVerticalRollRecyclerView
 * Author：mj
 * Time: 2018/10/9 15:14
 * 描述：Recyclerview的ViewHoloder
 * Version:
 */
class SimpleAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    var mTvData: TextView? = null

    init {
        mTvData = itemView.findViewById(R.id.tvData)
    }

}
