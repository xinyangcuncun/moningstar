package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.presenter.GSYPlayerPresenter;

/**
 * Created by anson on 2017/8/8.
 */

public class GSYPlayerActivity extends BaseMvpActivity<IGSYPlayerActivity,GSYPlayerPresenter> implements IGSYPlayerActivity{

    private TextView textView;
    private TextView tvMusic;
    private TextView tvBehavior1;
    private TextView tvBehavior2;
    private TextView tvBehavior3;
    private TextView tvBehavior4;

    public static void startMe(BaseActivity mContext) {
        mContext.startActivity(new Intent(mContext,GSYPlayerActivity.class));
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_gsy_player;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "超牛逼的播放器";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected GSYPlayerPresenter createPresenter() {
        return new GSYPlayerPresenter(mContext);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mPresenter.initPresenterView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.tv_open_video);
        textView.setOnClickListener(mPresenter);
        tvMusic = (TextView) findViewById(R.id.tv_music);
        tvMusic.setOnClickListener(mPresenter);
        tvBehavior1 = (TextView) findViewById(R.id.tv_behavior_1);
        tvBehavior1.setOnClickListener(mPresenter);
        tvBehavior2 = (TextView) findViewById(R.id.tv_behavior_2);
        tvBehavior2.setOnClickListener(mPresenter);
        tvBehavior3 = (TextView) findViewById(R.id.tv_behavior_3);
        tvBehavior3.setOnClickListener(mPresenter);
        tvBehavior4 = (TextView) findViewById(R.id.tv_behavior_4);
        tvBehavior4.setOnClickListener(mPresenter);
    }

    @Override
    public TextView getTvOpenVideo() {
        return textView;
    }
}
