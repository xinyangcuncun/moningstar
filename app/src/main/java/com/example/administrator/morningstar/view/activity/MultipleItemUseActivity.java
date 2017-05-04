package com.example.administrator.morningstar.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.adapter.MultipleItemQuickAdapter;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.bean.DataServer;
import com.example.administrator.morningstar.view.bean.MultipleItem;

import java.util.List;

/**
 * Created by anson on 2017/4/20.
 */

public class MultipleItemUseActivity extends BaseActivity{

    private RecyclerView recyclerView;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_multiple_item_use;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "复杂布局";
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
        final List<MultipleItem> data = DataServer.getMultipleItemData();
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, data);
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        recyclerView.setAdapter(multipleItemAdapter);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);

    }
}
