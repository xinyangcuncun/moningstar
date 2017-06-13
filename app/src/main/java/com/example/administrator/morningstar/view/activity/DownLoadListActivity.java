package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.adapter.DownLoadAdapter;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.constant.AppConst;
import com.example.administrator.morningstar.view.db.DaoSession;
import com.example.administrator.morningstar.view.db.GreenDaoManager;
import com.example.administrator.morningstar.view.db.MyBean;
import com.example.administrator.morningstar.view.manager.DownLoadManager;

import org.simple.eventbus.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anson on 2017/6/10.
 */

public class DownLoadListActivity extends BaseActivity{

    private RecyclerView recyclerView;
    private ArrayList<MyBean> data = new ArrayList<>();
    private DownLoadAdapter adapter;

    @Override
    protected int getViewLayout() {
        return R.layout.down_load_list_activity;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "下载列表页面";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    public static void startMe(BaseActivity mContext) {
        mContext.startActivity(new Intent(mContext,DownLoadListActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView) findViewById(R.id.rv_down_load_list);
        adapter = new DownLoadAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_item_start:  //开始下载
                        MyBean myBean1 = data.get(position);
                        DownLoadManager.getInstance(mContext).startDownLoad(myBean1,mContext);
                        break;
                    case R.id.btn_item_pause:  //暂停
                        MyBean myBean = data.get(position);
                        DownLoadManager.getInstance(mContext).pauseDownLoad(myBean.getDownid());
                        myBean.setState(AppConst.CANCLE);
                        GreenDaoManager.getInstance(mContext).getSession().getMyBeanDao().update(myBean);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.btn_item_delete:  //删除
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                DownLoadManager.getInstance(mContext).cleanDownLoad(data.get(position));
                                GreenDaoManager.getInstance(mContext).getSession().getMyBeanDao().delete(data.get(position));
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        data.remove(position);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }).start();
                        break;
                }
            }
        });

        initData();
    }

    private void initData() {
        //查数据库
        DaoSession session = GreenDaoManager.getInstance(mContext).getSession();
        List<MyBean> list = session.getMyBeanDao().queryBuilder().list();
        data.clear();
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Subscriber(tag = AppConst.DOWN_UPDATE)
    public void refreshdata(int i) {
        initData();
    }
}
