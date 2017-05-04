package com.example.administrator.morningstar.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.adapter.QuickAdapter;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.bean.DataServer;

/**
 * Created by anson on 2017/4/20.
 */

public class EmptyViewUseActivity extends BaseActivity{

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private View notDataView;
    private View errorView;
    private boolean mError = true;
    private boolean mNoData = true;
    private QuickAdapter mQuickAdapter;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_empty_view_use;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "空界面";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        onRefresh();
    }

    private void initData() {
        mQuickAdapter = new QuickAdapter(0);
        recyclerView.setAdapter(mQuickAdapter);
    }

    private void initView() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.btn_reset);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mError = true;
                mNoData = true;
                mQuickAdapter.setNewData(null);
                onRefresh();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) recyclerView.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    private void onRefresh() {
        mQuickAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) recyclerView.getParent());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mError) {
                    mQuickAdapter.setEmptyView(errorView);
                    mError = false;
                } else {
                    if (mNoData) {
                        mQuickAdapter.setEmptyView(notDataView);
                        mNoData = false;
                    } else {
                        mQuickAdapter.setNewData(DataServer.getSampleData(10));
                    }
                }
            }
        }, 1000);
    }
}
