package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;

/**
 * Created by anson on 2017/8/14.
 */

public class BehaviorActivity2 extends BaseActivity{
    @Override
    protected int getViewLayout() {
        return R.layout.activity_elema;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return null;
    }

    @Override
    protected boolean isShowToolbar() {
        return false;
    }

    public static void startMe(BaseMvpActivity mContext, TextView tvOpenVideo) {
        mContext.startActivity(new Intent(mContext,BehaviorActivity2.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
