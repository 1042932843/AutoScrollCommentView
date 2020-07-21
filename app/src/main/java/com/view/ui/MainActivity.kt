package com.view.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.view.R
import com.view.widget.adapter.SimpleAdapter
import com.view.widget.autopopcommentview.data.TestData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initType1()
        initType2()
    }


    fun initType1(){
        val list= mutableListOf<String>()
        for (i in 0 until  10){
            list.add("数据$i")
        }
        mRecyclerView.layoutManager= LinearLayoutManager(this)
        mRecyclerView.adapter=SimpleAdapter(list,this)
    }

    fun initType2(){
        val datas=ArrayList<TestData>()
        for (i in 0..50){
            val data=TestData(i,"", "Dusky$i", "哈哈$i")
            datas.add(data)
        }

        autoPopCommentView.setViewListData(datas)
    }

    override fun onResume() {
        super.onResume()
        autoPopCommentView.onResume()
    }

    override fun onPause() {
        super.onPause()
        autoPopCommentView.onPause()
    }
}
