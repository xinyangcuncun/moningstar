package com.example.administrator.morningstar.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

import com.example.administrator.morningstar.R;

/**
 * Created by anson on 2017/8/14.
 */

public class HeadBehavior extends CoordinatorLayout.Behavior<RecyclerView> {


    private View dependency;
    private final Scroller scroller;

    public HeadBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        if (dependency.getId() == R.id.iv_eleme) {
            this.dependency = dependency;
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        child.setTranslationY(dependency.getHeight() +dependency.getTranslationY());
        float abs = Math.abs(dependency.getTranslationY() / (dependency.getHeight() - finalHeight));
        dependency.setScaleX(1 + abs);
        dependency.setScaleY(1 + abs);
        dependency.setAlpha(1- abs);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    private int finalHeight = 100;
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dx, int dy, int[] consumed) {
        if (dy <= 0) {

        } else {
            float v = dependency.getTranslationY() - dy;
            int i = -(dependency.getHeight() - finalHeight);
            if (v > i) {
                dependency.setTranslationY(v);
                consumed[1] = dy;
            }
        }
    }

    private boolean isScrolling = false;
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyUnconsumed < 0) {
            float v = dependency.getTranslationY() - dyUnconsumed;
            if (v < 0) {
                dependency.setTranslationY(v);
            }
        }
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, float velocityX, float velocityY) {
        if (!isScrolling) {
            return openOrClose(velocityY);
        }
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    private boolean openOrClose(float velocityY) {
        float translationY = dependency.getTranslationY();
        float i = -(dependency.getHeight() - finalHeight);
        float y = 0;
        boolean isClose = false;
        if (Math.abs(velocityY) <= 800) {
            if (Math.abs(translationY) < Math.abs(translationY - i)) {
                isClose = false;
            } else {
                isClose = true;
            }
        } else {
            if (velocityY > 0) {
                isClose = true;
            } else {
                isClose = false;
            }
        }

        float nowState = isClose ? i : y;
        scroller.startScroll(0,(int) translationY,0,(int) (nowState - translationY));
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (scroller.computeScrollOffset()) {
                    dependency.setTranslationY(scroller.getCurrY());
                    handler.post(this);
                } else {
                    isScrolling = false;
                }
            }
        });
        isScrolling = true;
        return false;
    }

    private Handler handler = new Handler();

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        openOrClose(0);
    }
}
