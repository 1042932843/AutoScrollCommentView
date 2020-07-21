package com.view.widget.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
