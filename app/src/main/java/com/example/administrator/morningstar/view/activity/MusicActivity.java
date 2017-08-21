package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;

/**
 * Created by anson on 2017/8/13.
 */

public class MusicActivity extends BaseActivity{
    @Override
    protected int getViewLayout() {
        return R.layout.activity_music;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "自定义控件";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    public static void startMe(BaseMvpActivity mContext, TextView tvOpenVideo) {
        mContext.startActivity(new Intent(mContext,MusicActivity.class));
    }
}
