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
 * Created by anson on 2017/8/22.
 */
@SuppressWarnings("unused")
public class View2Behavior extends CoordinatorLayout.Behavior<RecyclerView> {
    private View dependency;
    private final Scroller scroller;
    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //scroller是一帧一帧执行的
            if (scroller.computeScrollOffset()) {
                dependency.setTranslationY(scroller.getCurrY()); //去移动到下一个位置
                handler.post(this);
            } else {
                isScrolling = false;
            }

        }
    };

    public View2Behavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        if (dependency.getId() == R.id.iv_head) {
            this.dependency = dependency;
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY()); // 跟随 被依赖布局一起移动
        //百分比
        float persent = Math.abs(dependency.getTranslationY() / (dependency.getHeight() - finalHeight));
        //头部图片的放大缩小
        dependency.setScaleY(persent + 1);
        dependency.setScaleX(persent + 1);
        dependency.setAlpha((float) (1 - persent + 0.3));
        return true;
    }

    //在嵌套滚到将要开始的时候：用于判断滚动方向
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    public float finalHeight = 200;

    //滚动之前  dy y方向上移动的距离 ： 手指的移动距离（单位时间）
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        //手指向下移动
        if (dy <= 0) {

        } else {
            //手指向上移动
            float newDy = dependency.getTranslationY() - dy;
            float minDy = -(dependency.getHeight() - finalHeight);
            if (newDy > minDy) {
                dependency.setTranslationY(newDy);
                consumed[1] = dy; // 系统去处理
            }

        }
    }

    //滚动中
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        float v = dependency.getTranslationY() - dyUnconsumed;
        if (dyUnconsumed < 0) {
            if (v < 0) {
                dependency.setTranslationY(v);
            }
        }
    }

    private boolean isScrolling = false;

    //快速滑动 velocityY 代表滑动速度
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, float velocityX, float velocityY) {
        if (!isScrolling) {
            return openOrExpand(velocityY);
        }
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }


    private boolean openOrExpand(float velocityY) {
        float translationY = dependency.getTranslationY();
        float upFinalTranslationY = -(dependency.getHeight() - finalHeight);
        float downFinalTranslationY = 0;
        boolean isClose = false;
        if (Math.abs(velocityY) <= 800) {
            //判断位置
            if (Math.abs(translationY) < Math.abs(translationY - upFinalTranslationY)) {
                isClose = false;
            } else {
                isClose = true;
            }
        } else {
            if (velocityY > 0) {
                //快速向上滑动
                isClose = true;
            } else {
                //快速向下滑动
                isClose = false;
            }
        }
        //确定目标点
        float targetPosition = isClose ? upFinalTranslationY : downFinalTranslationY;
        scroller.startScroll(0, (int) translationY, 0, (int) (targetPosition - translationY));
        handler.post(runnable);
        isScrolling = true;
        return true;   //防止 recycleview 被滚动
    }

    //停止嵌套滚动  通常会执行两次：父布局一个 自身布局一次
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        openOrExpand(0);
    }

    //当子视图滚动被确认的时候
    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        scroller.abortAnimation(); // 停止执行
    }
}
