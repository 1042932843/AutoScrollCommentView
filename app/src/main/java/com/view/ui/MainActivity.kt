package com.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.view.R
import com.view.adapter.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list= mutableListOf<String>()
        for (i in 0 until  10){
            list.add("数据$i")
        }
        mRecyclerView.layoutManager=LinearLayoutManager(this)
        mRecyclerView.adapter=SimpleAdapter(list,this)
    }
}
