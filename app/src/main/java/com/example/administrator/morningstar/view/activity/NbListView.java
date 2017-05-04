package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.adapter.HomeAdapter;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.bean.HomeItem;

import java.util.ArrayList;

/**
 * Created by anson on 2017/4/20.
 */

public class NbListView extends BaseActivity{

    private RecyclerView recyclerView;
    private static final String[] TITLE = {"Animation", "MultipleItem", "Header/Footer", "PullToRefresh", "Section", "EmptyView", "DragAndSwipe", "ItemClick", "ExpandableItem", "DataBinding"};
    private static final Class<?>[] ACTIVITY = {AnimationUseActivity.class, MultipleItemUseActivity.class, HeaderAndFooterUseActivity.class, PullToRefreshUseActivity.class, SectionUseActivity.class, EmptyViewUseActivity.class, ItemDragAndSwipeUseActivity.class, ItemClickActivity.class, ExpandableUseActivity.class, DataBindingUseActivity.class};
    private ArrayList<HomeItem> mDataList;

    @Override
    protected int getViewLayout() {
        return R.layout.nb_listview_activity;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "史上最牛逼的封装";
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
    }

    private void initData() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.home_item_view, mDataList);
        homeAdapter.openLoadAnimation();
        /*View top = getLayoutInflater().inflate(R.layout.top_view, (ViewGroup) mRecyclerView.getParent(), false);
        homeAdapter.addHeaderView(top);*/
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, ACTIVITY[position]);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(homeAdapter);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[i]);
            mDataList.add(item);
        }
    }
}
