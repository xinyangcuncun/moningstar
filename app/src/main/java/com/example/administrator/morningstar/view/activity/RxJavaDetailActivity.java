package com.example.administrator.morningstar.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by anson on 2017/4/13.
 */

public class RxJavaDetailActivity extends BaseActivity {

    private String mName;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initData();
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        mName = intent.getStringExtra("name");
    }

    private void initView() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.detail_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, mName, Snackbar.LENGTH_INDEFINITE).setAction("开始计算",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (TextUtils.equals(mName, "Concat")) {

                                } else if (TextUtils.equals(mName, "Contains")) {
                                    Observable
                                            .just(1, 2, 3)
                                            .contains(3)
                                            .subscribe(aBoolean -> showInfo(aBoolean + ""));
                                } else if (TextUtils.equals(mName, "Distinct")) {
                                    Observable
                                            .just(1, 2, 3, 3, 3)
                                            .distinct()
                                            .subscribe(aBoolean -> showInfo(aBoolean + "")); // 每次都会输出
                                } else if (TextUtils.equals(mName, "Delay")) {
                                    Observable.just(1, 2, 3)
                                            .delay(1000, TimeUnit.MILLISECONDS)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(integer -> showInfo(integer + ""));
                                }
                            }
                        }).show();
            }
        });
    }

    private void showInfo(String s) {

        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();


    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_rx_java_detail;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return mName;
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    public static void startMe(Context context, String name) {
        Intent intent = new Intent(context, RxJavaDetailActivity.class);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }
}
