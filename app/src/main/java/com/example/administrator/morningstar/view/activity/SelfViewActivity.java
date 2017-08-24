package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;

/**
 * Created by anson on 2017/8/21.
 */

public class SelfViewActivity extends BaseActivity{

    private TextView textView;
    private TextView textView2;
    private TextView textView3;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_self_view;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "自定义控件专题";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    public static void startMe(BaseActivity mContext) {
        mContext.startActivity(new Intent(mContext,SelfViewActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.tv_view_1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelfView1.startMe(mContext);
            }
        });

        textView2 = (TextView) findViewById(R.id.tv_view_2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelfView2.startMe(mContext);
            }
        });

        textView3 = (TextView) findViewById(R.id.tv_svg_1);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SVG1.startMe(mContext);
            }
        });
    }
}
