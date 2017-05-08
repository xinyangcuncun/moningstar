package com.example.administrator.morningstar.view.activity;

import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.administrator.morningstar.R;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

/**
 * Created by anson on 2017/4/5.
 */

public class HomeFragment extends BaseFragment {
    private static final int[] START_POINT = new int[]{300, 270};
    private static final int[] BOTTOM_POINT = new int[]{300, 400};
    private static final int[] LEFT_CONTROL_POINT = new int[]{450, 200};
    private static final int[] RIGHT_CONTROL_POINT = new int[]{150, 200};


    @Override
    protected int getViewLayout() {
        return R.layout.fragment_main_home_anim;
    }

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        LottieAnimationView animationView = (LottieAnimationView) getRootView().findViewById(R.id.animation_view);
        animationView.setAnimation("Spider Loader.json");
        animationView.loop(true);
        animationView.playAnimation();


        Path path = new Path();
        path.moveTo(START_POINT[0], START_POINT[1]);
        path.quadTo(RIGHT_CONTROL_POINT[0], RIGHT_CONTROL_POINT[1], BOTTOM_POINT[0], BOTTOM_POINT[1]);
        path.quadTo(LEFT_CONTROL_POINT[0], LEFT_CONTROL_POINT[1], START_POINT[0], START_POINT[1]);
        path.close();
        ViewAnimator animator = ViewAnimator.animate(getRootView().findViewById(R.id.button)).path(path).repeatCount(Integer.MAX_VALUE).start();
        animator.onStop(new AnimationListener.Stop() {
            @Override
            public void onStop() {

            }
        });
    }


}
