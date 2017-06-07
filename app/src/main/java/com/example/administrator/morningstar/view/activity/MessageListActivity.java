package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.db.DaoMaster;
import com.example.administrator.morningstar.view.db.DaoSession;
import com.example.administrator.morningstar.view.db.GreenDaoManager;
import com.example.administrator.morningstar.view.db.MyBean;
import com.example.administrator.morningstar.view.db.MyBeanDao;
import com.example.administrator.morningstar.view.presenter.MessageListPresenter;

import java.math.BigDecimal;

/**
 * Created by anson on 2017/5/25.
 */

public class MessageListActivity extends BaseMvpActivity<IMessageListActivity,MessageListPresenter> implements  IMessageListActivity{

    private RecyclerView recyclerView;
    private TextView tv;

    @Override
    protected int getViewLayout() {
        return R.layout.message_list_activity;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "最近聊天";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected MessageListPresenter createPresenter() {
        return new MessageListPresenter(mContext);
    }

    public static void startMe(BaseActivity mContext) {
        mContext.startActivity(new Intent(mContext,MessageListActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_message_list);
        tv = (TextView) findViewById(R.id.hhhhhhh);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //初始化数据库
                /*DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
                DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
                DaoSession daoSession = daoMaster.newSession();*/
                DaoSession daoSession = GreenDaoManager.getInstance(mContext.getApplicationContext()).getSession();

                MyBeanDao myBeanDao = daoSession.getMyBeanDao();//  获取一个表

                MyBean myBean = new MyBean(null,"haha","heee" );
                myBeanDao.insert(myBean);                       //增加一条数据


            }
        });
    }

    @Override
    public RecyclerView getRv() {
        return recyclerView;
    }


    public static String getNewAmount(long amount) {
        String newAmount;
        if (amount >= 10000) {
            //超过一万则显示如1.3万
            double hit = new BigDecimal(amount / 1000).setScale(1, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(10)).doubleValue();
            newAmount = String.valueOf(hit).concat("万");
        } else {
            newAmount = String.valueOf(amount);
        }
        return newAmount;
    }
}
