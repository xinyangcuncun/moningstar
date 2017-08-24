package com.example.administrator.morningstar.view.adapter;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by anson on 2017/8/21.
 */

@SuppressWarnings("unused")
public class View1Behavior extends CoordinatorLayout.Behavior<TextView>{

    //自定义Behavior 一定要复写两个参数的构造方法
    public View1Behavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //如果依赖的是 toolbar 才去处理
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    private float startX ;
    private float startY ;
    private float stubX = 10 ; //矫正像素

    //这个方法主要是根据被依赖控件的位置，来改变依赖控件的时时滑动位置
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        if (startX == 0) {
            startX = child.getX();
        }

        if (startY == 0) {
            startY = dependency.getY();
        }
        child.setY(dependency.getY());

        float persent = dependency.getY() / startY;
        dependency.setAlpha( 1 - persent);
        float x = startX - ((dependency.getWidth() - child.getWidth()) * (1 -persent)) + child.getWidth()/2 - stubX;
        child.setX(x);
        return true;
    }
}
