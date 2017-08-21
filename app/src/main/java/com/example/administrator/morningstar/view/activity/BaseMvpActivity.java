package com.example.administrator.morningstar.view.activity;

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
import com.example.administrator.morningstar.view.presenter.BasePresenter;
import com.example.administrator.morningstar.view.tool.ViewTools;

/**
 * Created by anson on 2017/5/5.
 */

public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends AppCompatActivity{

    public BaseMvpActivity mContext;
    private Toolbar mToolBarView;
    private LinearLayout mView;
    private TextView mGoBack;
    private TextView mTitle;
    private FrameLayout mBaseView;
    private FrameLayout mBaseViews;
    protected T     mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewTools.setActivityBackGroudCoulor(this, Color.parseColor("#ffffff"));
        super.setContentView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        mView = (LinearLayout) findViewById(R.id.root_view);
        mToolBarView = (Toolbar) findViewById(R.id.tb_app_bar);
        mGoBack = (TextView) findViewById(R.id.tv_toolbar_back);
        mTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        mBaseView = (FrameLayout) findViewById(R.id.base_framelayout);
        int viewLayoutId = getViewLayout();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
        if (viewLayoutId != 0) {
            View.inflate(mContext, viewLayoutId, mBaseView);
        }
        setToolBarDetail();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
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

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();
}
