package com.example.administrator.morningstar.view.adapter;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.morningstar.R;

/**
 * Created by anson on 2017/8/14.
 */

public class CenterBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private float mStartX;
    private float mStartY;
    private int mOrigentSize;

    public CenterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        if (dependency.getId() == R.id.tool_bar) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        if (mStartX == 0) {
            mStartX = child.getX();
        }
        if (mStartY == 0) {
            mStartY = dependency.getY();
        }
        if (mOrigentSize == 0) {
            mOrigentSize = child.getHeight();
        }
        child.setY(dependency.getY());

        float persent = dependency.getY() / mStartY;
        return true;
    }
}
