package com.example.administrator.morningstar.view.adapter;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.tool.UIUtils;



/**
 * Created by anson on 2017/8/23.
 */
@SuppressWarnings("unused")
public class View2BehaviorTextView extends CoordinatorLayout.Behavior<LinearLayout>{

    private final ArgbEvaluator argbEvaluator;
    private TransitionSet mSet;

    public View2BehaviorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        argbEvaluator = new ArgbEvaluator();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        if (dependency.getId() == R.id.iv_head) {
            return true;
        }
        return false;
    }

    private float initOffSet = 480;
    private float endOffSet = 5;
    private int finalHeight = 200;
    private int initMagin = 20;
    private int endMagin = 5;
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {
        float persent = Math.abs(dependency.getTranslationY()/(dependency.getHeight() - finalHeight));
        //随着图片移动而移动
        float translationY = endOffSet +(initOffSet - endOffSet)*(1-persent);
        child.setTranslationY(translationY);
        int evaluate = (int)argbEvaluator.evaluate(persent, Color.GRAY, Color.DKGRAY);
//        child.setBackgroundColor(evaluate);
        int magin = (int) (endMagin + ((initMagin - endMagin) *(1 - persent)));
        if (persent == 0) {
            reduce(child);
        } else if (persent == 1) {
            expand(child);
        } else {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            params.setMargins(magin,0,magin,magin);
            child.setLayoutParams(params);
        }
        return true;
    }

    private void expand(LinearLayout mSearchLayout) {
        //设置伸展状态时的布局
        CoordinatorLayout.LayoutParams LayoutParams = (CoordinatorLayout.LayoutParams) mSearchLayout.getLayoutParams();
        LayoutParams.width = LayoutParams.MATCH_PARENT;
        LayoutParams.setMargins(UIUtils.dip2Px(10), UIUtils.dip2Px(5), UIUtils.dip2Px(10), UIUtils.dip2Px(10));
        mSearchLayout.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(mSearchLayout);
    }

    private void reduce(LinearLayout mSearchLayout) {
        //设置收缩状态时的布局
        CoordinatorLayout.LayoutParams LayoutParams = (CoordinatorLayout.LayoutParams) mSearchLayout.getLayoutParams();
        LayoutParams.width = UIUtils.dip2Px(80);
        LayoutParams.setMargins(UIUtils.dip2Px(10), UIUtils.dip2Px(10), UIUtils.dip2Px(10), UIUtils.dip2Px(10));
        mSearchLayout.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(mSearchLayout);
    }

   private void beginDelayedTransition(LinearLayout view) {
        mSet = new AutoTransition();
        mSet.setDuration(300);
        TransitionManager.beginDelayedTransition(view, mSet);
    }
}
