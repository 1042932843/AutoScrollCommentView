package com.view.widget.autopopcommentview

import android.animation.Animator
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.util.Pools
import com.view.R
import com.view.widget.autopopcommentview.data.TestData
import kotlinx.android.synthetic.main.layout_pop_comment_view.view.*


class AutoPopCommentView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {
        View.inflate(context, R.layout.layout_pop_comment_view, this)
        initAnimator()
    }

    private var duration_DISAPPEARING = 0L
    private var duration_APPEARING = 0L

    private var viewPool=Pools.SimplePool<ItemView>(4)
    private var datas = ArrayList<TestData>()

    @SuppressLint("ObjectAnimatorBinding")
    fun initAnimator() {
        val transition = LayoutTransition()
        duration_DISAPPEARING = transition.getDuration(LayoutTransition.DISAPPEARING)
        duration_APPEARING = transition.getDuration(LayoutTransition.APPEARING)

        //添加动画
        val valueAnimator = ObjectAnimator.ofFloat(null, "alpha", 0f, 1f)
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) { //当前展示超过四条，执行删除动画
                if (mlayout.childCount == 4) {
                    handler.sendEmptyMessage(1)
                }
            }

            override fun onAnimationEnd(animation: Animator) {
                if (mlayout.childCount == 5) //动画执行完毕，删除view
                    handler.sendEmptyMessage(2)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        transition.setAnimator(LayoutTransition.APPEARING, valueAnimator)
        //删除动画
        val objectAnimator = ObjectAnimator.ofFloat(null, "alpha", 0f, 0f).setDuration(duration_DISAPPEARING)

        transition.setAnimator(LayoutTransition.DISAPPEARING, objectAnimator)
        mlayout.layoutTransition = transition
    }

    fun setViewListData(datas: ArrayList<TestData>) {
        this.datas = datas
    }

    @SuppressLint("SetTextI18n")
    fun obtainView(data: TestData): View {
        var v = viewPool.acquire()
        if(v==null){
            v= ItemView(context)
            v.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }

        v.findViewById<TextView>(R.id.username)?.text = data.username + ":"
        v.findViewById<TextView>(R.id.comment)?.text = data.comment
        return v
    }

    private var indexView = 0
    private var indexData = 0
    @SuppressLint("HandlerLeak")
    private val handler = object : Handler() {
        @SuppressLint("ResourceAsColor")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(datas.size<=0){
                return
            }
            when (msg.what) {
                0 -> {
                    val view: View = obtainView(datas[indexData])
                    mlayout.addView(view)
                    sendEmptyMessageDelayed(0, 1000)
                    indexView++
                    indexData++
                    if (indexView == 4) {
                        indexView = 0
                    }
                    if (indexData == (datas.size - 1)) {
                        indexData = 0
                    }
                }
                1 ->  //给展示的第一个view增加渐变透明动画
                    mlayout.getChildAt(0).animate().alpha(0f).setDuration(duration_APPEARING).start()
                2 ->  //删除顶部view
                    mlayout.removeViewAt(0)
            }
        }
    }

    fun onResume() {
        handler.sendEmptyMessage(0)
    }

    fun onPause() {
        handler.removeMessages(0)
    }
}
