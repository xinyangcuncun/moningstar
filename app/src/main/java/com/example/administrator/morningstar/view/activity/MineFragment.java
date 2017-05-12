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
import com.example.administrator.morningstar.view.tool.JJBarWithErrorIconController;
import com.example.administrator.morningstar.view.tool.JJSearchView;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.wingsofts.threedlayout.ThreeDLayout;

/**
 * Created by anson on 2017/4/5.
 */

public class MineFragment extends BaseFragment{
    private GyroscopeObserver gyroscopeObserver;
    private ThreeDLayout threeDLayout;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_main_guangzhou;
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
        threeDLayout = (ThreeDLayout) getRootView().findViewById(R.id.three_layout);
        threeDLayout.setTouchMode(ThreeDLayout.MODE_BOTH_X_Y);
        threeDLayout.setMaxRotateDegree(200);

        gyroscopeObserver = new GyroscopeObserver();
        PanoramaImageView panoramaImageView = (PanoramaImageView) getRootView().findViewById(R.id.panorama_image_view);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        gyroscopeObserver.register(mContext);
    }

    @Override
    public void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
    }
}
