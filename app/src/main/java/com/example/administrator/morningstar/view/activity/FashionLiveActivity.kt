package com.example.administrator.morningstar.view.activity

import android.content.Intent
import android.os.Bundle
import com.example.administrator.morningstar.R
import com.example.administrator.morningstar.view.base.BaseActivity

/**
 * Created by anson on 2017/5/18.
 */

class FashionLiveActivity : BaseActivity() {
    override fun getToolBarTitle(): CharSequence {
        return "直播间"
    }

    override fun isShowToolbar(): Boolean {
        return true
    }

    override fun getViewLayout(): Int {
        return R.layout.activity_fashion_live
    }

    companion object {
        fun startMe(mContext: BaseActivity) {
            mContext.startActivity(Intent(mContext, FashionLiveActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    private fun initData() {

    }

    private fun initView() {

    }
}
