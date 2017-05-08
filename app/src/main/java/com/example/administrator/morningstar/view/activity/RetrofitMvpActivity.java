package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.presenter.BasePresenter;
import com.example.administrator.morningstar.view.presenter.RetrofitPresenter;
import com.example.administrator.morningstar.view.presenter.VIgetView;

/**
 * Created by anson on 2017/5/5.
 */

public class RetrofitMvpActivity extends BaseMvpActivity<VIgetView,RetrofitPresenter> implements VIgetView {

    private TextView textView;

    public static void startMe(BaseActivity mContext) {
        Intent intent = new Intent(mContext,RetrofitMvpActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_main_home;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "Retrofit + Mvp";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected RetrofitPresenter createPresenter() {
        return new RetrofitPresenter(mContext);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.tv_test_name);
        textView.setText("Retrofit+Mvp");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //业务下沉
                mPresenter.getData();
            }
        });
    }

    @Override
    public TextView getMvpTextView() {
        return textView;
    }
}
