package com.example.administrator.morningstar.view.activity;

import android.content.Intent;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;

/**
 * Created by anson on 2017/8/21.
 */

public class SelfView1 extends BaseActivity{
    @Override
    protected int getViewLayout() {
        return R.layout.self_view_1;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return null;
    }

    @Override
    protected boolean isShowToolbar() {
        return false;
    }

    public static void startMe(BaseActivity mContext) {
        mContext.startActivity(new Intent(mContext,SelfView1.class));
    }
}
