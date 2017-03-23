package com.example.administrator.morningstar.view.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.tool.ViewTools;


/**
 * Created by Anson on 2017/2/5.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private LinearLayout mView;
    private BaseActivity mContext;
    private int mToolBarLayoutId;
    private View mToolBarView;
    private DrawerLayout mDrawerLayout;
    private TextView mTitleView;
    private TextView haha;
    private TextView haha_test;
    private TextView haha_master;


    private int toolbarId;

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    private int titleId;

    public void setMtitleName(String mtitleName) {
        this.mtitleName = mtitleName;
    }

    private String mtitleName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewTools.setActivityBackGroudCoulor(this, Color.parseColor("#FF4081"));
        setContentView(R.layout.activity_base);
        mContext = this;
        mView = (LinearLayout) findViewById(R.id.root_view);
        setToolBar();

    }

    private void setToolBar() {
        if (isShowToolbar()) {
            Toolbar toolbar = new Toolbar(mContext);
            setSupportActionBar(toolbar);
            int toolBarLayoutId = getToolBarLayoutId();
            mToolBarView = View.inflate(mContext, toolBarLayoutId, toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mDrawerLayout = new DrawerLayout(this);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
            toggle.syncState();
            mDrawerLayout.setDrawerListener(toggle);
            initTitle(mToolBarView, getTitleViewId(),getTitleName());
            mView.addView(toolbar);
        }
    }

    private void initTitle(View toolBarView, int titleViewId,String title) {
        mTitleView = (TextView) toolBarView.findViewById(titleViewId);
        mTitleView.setText(title);
    }

    public  int getToolBarLayoutId(){
        return toolbarId;
    }

    public void setToolbarId(int toolbarId) {
        this.toolbarId = toolbarId;
    }

    protected abstract boolean isShowToolbar();

    public String getTitleName(){
        return mtitleName;
    }
    public  int getTitleViewId(){
        return titleId;
    }


}
