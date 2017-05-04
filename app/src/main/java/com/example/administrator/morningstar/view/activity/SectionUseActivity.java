package com.example.administrator.morningstar.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.adapter.SectionAdapter;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.bean.DataServer;
import com.example.administrator.morningstar.view.bean.MySection;

import java.util.List;

/**
 * Created by anson on 2017/4/20.
 */

public class SectionUseActivity extends BaseActivity{

    private RecyclerView recyclerView;
    private List<MySection> mData;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_section_uer;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "分类布局";
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
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mData = DataServer.getSampleData();
        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.def_section_head, mData);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
    }
}
