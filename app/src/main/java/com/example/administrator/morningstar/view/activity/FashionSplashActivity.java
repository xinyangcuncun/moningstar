package com.example.administrator.morningstar.view.activity;

import android.content.Intent;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;

/**
 * Created by anson on 2017/5/17.
 */

public class FashionSplashActivity  extends BaseActivity{
    @Override
    protected int getViewLayout() {
        return R.layout.fragment_main_home;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "";
    }

    @Override
    protected boolean isShowToolbar() {
        return false;
    }

    public static void startMe(BaseActivity mContext) {
        mContext.startActivity(new Intent(mContext,FashionSplashActivity.class));
    }
}
