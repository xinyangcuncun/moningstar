package com.example.administrator.morningstar.view.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.view.View;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.presenter.SigleVideoPlayerPresenter;
import com.example.administrator.morningstar.view.tool.OnTransitionListener;
import com.example.administrator.morningstar.view.wight.Wifi;

/**
 * Created by anson on 2017/8/8.
 */

public class SigleVideoPlayer extends BaseMvpActivity<ISigleVideoPlayer,SigleVideoPlayerPresenter> implements ISigleVideoPlayer{

    private static String TRANSITION = "TRANSITION";
    private static String IMG_TRANSITION = "IMG_TRANSITION";
    private Wifi button;
    private boolean isTransition;
    private Transition transition;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_sigle_video;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "单个播放器";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected SigleVideoPlayerPresenter createPresenter() {
        return new SigleVideoPlayerPresenter(mContext);
    }

    public static void startMe(BaseMvpActivity mContext, View view) {
        Intent intent = new Intent(mContext, SigleVideoPlayer.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair<>(view, IMG_TRANSITION);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    mContext, pair);
            ActivityCompat.startActivity(mContext, intent, activityOptions.toBundle());
        } else {
            mContext.startActivity(intent);
            mContext.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTransition();
    }

    private void initTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(button, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            // 处理你自己的逻辑
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new OnTransitionListener(){
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                      // 动画完成之后  处理你自己的逻辑
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }

    private void initView() {
        button = (Wifi) findViewById(R.id.bt_sigle_video);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        button.OnDestroy();
        button = null;
    }
}
