package com.example.administrator.morningstar.view.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.activity.DemoIntentService;
import com.example.administrator.morningstar.view.activity.DemoPushService;
import com.example.administrator.morningstar.view.tool.ViewTools;
import com.igexin.sdk.PushManager;

import org.simple.eventbus.EventBus;


/**
 * Created by Anson on 2017/2/5.
 */

public abstract class BaseActivity extends AppCompatActivity {


    public BaseActivity mContext;
    private Toolbar mToolBarView;
    private LinearLayout mView;
    private TextView mGoBack;
    private TextView mTitle;
    private FrameLayout mBaseView;
    private FrameLayout mBaseViews;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        ViewTools.setActivityBackGroudCoulor(this, Color.parseColor("#ffffff"));
        super.setContentView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        mContext = this;
        mView = (LinearLayout) findViewById(R.id.root_view);
        mToolBarView = (Toolbar) findViewById(R.id.tb_app_bar);
        mGoBack = (TextView) findViewById(R.id.tv_toolbar_back);
        mTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        mBaseView = (FrameLayout) findViewById(R.id.base_framelayout);
        int viewLayoutId = getViewLayout();
        if (viewLayoutId != 0) {
            View.inflate(mContext, viewLayoutId, mBaseView);
        }
        setToolBarDetail();

    }

    protected abstract int getViewLayout();

    private void setToolBarDetail() {
        if (isShowToolbar()) {
            mToolBarView.setVisibility(View.VISIBLE);
            mGoBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            mTitle.setText(getToolBarTitle());
        } else {
            mToolBarView.setVisibility(View.GONE);
        }
    }

    protected abstract CharSequence getToolBarTitle();

    protected abstract boolean isShowToolbar();

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }
}
