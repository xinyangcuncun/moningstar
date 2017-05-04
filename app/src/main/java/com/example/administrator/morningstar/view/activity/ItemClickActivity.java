package com.example.administrator.morningstar.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.bean.ClickEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anson on 2017/4/20.
 */

public class ItemClickActivity extends BaseActivity{

    private RecyclerView recyclerView;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_item_click;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "点击效果";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.list);
        initAdapter();
        /*adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ItemClickActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ItemClickActivity.this, "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ItemClickActivity.this, "onItemChildClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ItemClickActivity.this, "onItemChildLongClick" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/
    }

    private void initAdapter() {
        List<ClickEntity> data = new ArrayList<>();
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_CHILD_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW));
        data.add(new ClickEntity(ClickEntity.NEST_CLICK_ITEM_CHILD_VIEW));
        /*adapter = new ItemClickAdapter(data);
        adapter.openLoadAnimation();
        mRecyclerView.setAdapter(adapter);*/
    }
}
