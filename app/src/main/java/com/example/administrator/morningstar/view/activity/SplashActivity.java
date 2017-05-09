package com.example.administrator.morningstar.view.activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
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
    //外部启动目标intent
    static final String GOTO_INTENT = "GOTO_INTENT";

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
    static public Intent startAppOutside(Context context, @Nullable Intent gotoIntent) {
        Intent intent = new Intent(context, SplashActivity.class);
        //添加模拟HOME进入的标志
        intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            if (gotoIntent != null) {
            intent.putExtra(GOTO_INTENT, gotoIntent);
        }
        //从外部进入，则还需要添加NEW_TASK, 避免App被杀死，无法进入的问题
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return intent;
    }
}
