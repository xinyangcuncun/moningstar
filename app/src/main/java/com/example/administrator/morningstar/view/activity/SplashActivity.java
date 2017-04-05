package com.example.administrator.morningstar.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.tool.ApplicationIo;

/**
 * Created by anson on 2017/4/5.
 */

public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler();

    @Override
    protected boolean isShowToolbar() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ApplicationIo.getInstance(mContext).initMode1();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //正常启动
                MainActivity.startMe(mContext);
                SplashActivity.this.finish();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onBackPressed() {
    }
}
