package com.example.administrator.morningstar.view.activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.tool.ApplicationIo;

/**
 * Created by anson on 2017/4/5.
 */

public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler();

    @Override
    protected boolean isShowToolbar() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationIo.getInstance(mContext).initMode1();
        LottieAnimationView animationView = (LottieAnimationView)findViewById(R.id.animation_view);
        animationView.setAnimation("lottiefiles.com - Countdown.json");
        animationView.loop(false);
        animationView.playAnimation();
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                MainActivity.startMe(mContext);
                SplashActivity.this.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onBackPressed() {
    }
}
