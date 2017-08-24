package com.example.administrator.morningstar.view.activity;

import android.content.Intent;

import com.example.administrator.morningstar.view.base.BaseActivity;

/**
 * Created by anson on 2017/8/24.
 */

public class SVG1 extends BaseActivity{
    @Override
    protected int getViewLayout() {
        return 0;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "SVG";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    public static void startMe(BaseActivity mContext) {
        mContext.startActivity(new Intent(mContext,SVG1.class));
    }
}
