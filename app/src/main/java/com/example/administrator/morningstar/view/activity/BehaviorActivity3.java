package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.adapter.QuickAdapter;
import com.example.administrator.morningstar.view.base.BaseActivity;

/**
 * Created by anson on 2017/8/14.
 */

public class BehaviorActivity3 extends BaseActivity{

    private RecyclerView recyclerView;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_eleme;
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
        mContext.startActivity(new Intent(mContext,BehaviorActivity3.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rc_eleme);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new QuickAdapter(30));
    }
}
