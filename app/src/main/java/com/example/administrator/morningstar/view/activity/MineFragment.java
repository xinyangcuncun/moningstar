package com.example.administrator.morningstar.view.activity;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.morningstar.R;

/**
 * Created by anson on 2017/4/5.
 */

public class MineFragment extends BaseFragment{
    private TextView mTextView;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_main_home;
    }
    public static MineFragment newInstance() {
        MineFragment homeFragment = new MineFragment();
        return homeFragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mTextView = (TextView) getRootView().findViewById(R.id.tv_test_name);
        mTextView.setText("数据库");
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageInfo info;
                try {
                    info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
                    // 当前应用的版本名称
                    String versionName = info.versionName;
                    // 当前版本的版本号
                    int versionCode = info.versionCode;
                    // 当前版本的包名
                    String packageNames = info.packageName;
                    Log.d("tag", packageNames);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
