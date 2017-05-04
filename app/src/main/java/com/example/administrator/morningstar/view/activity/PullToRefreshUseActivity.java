package com.example.administrator.morningstar.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.adapter.PullToRefreshAdapter;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.bean.DataServer;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * Created by anson on 2017/4/20.
 */

public class PullToRefreshUseActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PullToRefreshAdapter pullToRefreshAdapter;
    private int mCurrentCounter;
    private boolean isErr;
    private int TOTAL_COUNTER = 12;
    private boolean mLoadMoreEndGone;

    @Override
    protected int getViewLayout() {
        return R.layout.pull_refresh_activity;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "下拉刷新";
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
        initAdapter();
    }

    private void initAdapter() {
        pullToRefreshAdapter = new PullToRefreshAdapter();
        pullToRefreshAdapter.setOnLoadMoreListener(this, recyclerView); // load more
        pullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT); // anima
        recyclerView.setAdapter(pullToRefreshAdapter);
        mCurrentCounter = pullToRefreshAdapter.getData().size();

        recyclerView.addOnItemTouchListener(new OnItemClickListener() { // item click
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(PullToRefreshUseActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initData() {
        swipeRefreshLayout.setOnRefreshListener(this); // refresh listener
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189)); // color
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // layout manager
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
    }

    @Override
    public void onRefresh() {
        pullToRefreshAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshAdapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                isErr = false;
                mCurrentCounter = PAGE_SIZE;
                swipeRefreshLayout.setRefreshing(false);
                pullToRefreshAdapter.setEnableLoadMore(true);
            }
        }, 2000);
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setEnabled(false);
        if (pullToRefreshAdapter.getData().size() < PAGE_SIZE) {
            pullToRefreshAdapter.loadMoreEnd(true);
        } else {
            if (mCurrentCounter >= TOTAL_COUNTER) {
//                    pullToRefreshAdapter.loadMoreEnd();//default visible
                pullToRefreshAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
            } else {
                if (isErr) {
                    pullToRefreshAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                    mCurrentCounter = pullToRefreshAdapter.getData().size();
                    pullToRefreshAdapter.loadMoreComplete();
                } else {
                    isErr = true;
                    Toast.makeText(PullToRefreshUseActivity.this, "网络错误", Toast.LENGTH_LONG).show();
                    pullToRefreshAdapter.loadMoreFail();

                }
            }
            swipeRefreshLayout.setEnabled(true);
        }

    }
}
