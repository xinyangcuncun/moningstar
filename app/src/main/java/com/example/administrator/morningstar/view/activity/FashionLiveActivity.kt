package com.example.administrator.morningstar.view.activity

import android.content.Intent
import com.example.administrator.morningstar.view.base.BaseActivity

/**
 * Created by anson on 2017/5/18.
 */

class FashionLiveActivity : BaseActivity() {
    override fun getToolBarTitle(): CharSequence {
        return ""
    }

    override fun isShowToolbar(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun startMe(mContext: BaseActivity) {
            mContext.startActivity(Intent(mContext, FashionLiveActivity::class.java))
        }
    }
}
