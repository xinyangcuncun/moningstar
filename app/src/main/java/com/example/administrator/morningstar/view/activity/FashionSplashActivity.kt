package com.example.administrator.morningstar.view.activity

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle

import com.example.administrator.morningstar.R
import com.example.administrator.morningstar.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_fashion_splash.*

/**
 * Created by anson on 2017/5/17.
 */

class FashionSplashActivity : BaseActivity() {
    override fun getViewLayout(): Int {
        return R.layout.activity_fashion_splash
    }

    override fun getToolBarTitle(): CharSequence {
        return ""
    }

    override fun isShowToolbar(): Boolean {
        return false
    }

    companion object {
        fun startMe(mContext: BaseActivity) {
            mContext.startActivity(Intent(mContext, FashionSplashActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        welcome_videoview.setVideoURI(Uri.parse("android.resource://" + this.packageName + "/" + R.raw.kr36))
        welcome_videoview.start()
        welcome_videoview.setOnCompletionListener { welcome_videoview.start() }
        welcome_button.setOnClickListener {
            if (welcome_videoview.isPlaying) {
                welcome_videoview.stopPlayback()
            }
            finish() }
    }

}
