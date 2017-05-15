package com.example.administrator.morningstar.view.activity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.tool.CommonUtils;
import com.example.administrator.morningstar.view.view.BezierView;
import com.nineoldandroids.view.ViewHelper;

import static android.R.attr.offset;
import static android.R.attr.startY;

/**
 * Created by LZY on 2017/5/15.
 * ＊ Description ${TODO}
 */

public class BezierViewActivity extends BaseActivity {

    private int firstArc = 60;//起始的贝塞尔曲线凹度
    private BezierView mBezierView;
    private float      mOffset;//偏移量
    private int     tempOffset = 0;//临时偏移量
    private float   startY     = 0;//起始的y坐标
    private float   rawY       = 0;//move中的y坐标
    private boolean hasMoved   = false;//判断scrollview是否移动了

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

    }

    private void initView() {
        mBezierView = (BezierView) findViewById(R.id.bv_top);
        mBezierView.setArcHeight(firstArc);
        setBvParms(firstArc);
        mBezierView.setColor(Color.parseColor("#ffffff"));

    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_bezierview;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "BezierView";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                rawY = ev.getRawY();
                mOffset = rawY - startY;
                if (mOffset < CommonUtils.Dp2Px(this, 100)) {
                    tempOffset = (int) mOffset;
                    scaleTopContainerView(mOffset);
                } else {
                    mOffset = tempOffset;
                    scaleTopContainerView(mOffset);
                }
                hasMoved = true;
                Log.d("LZY", "dispatchTouchEvent: "+offset);

                break;
            case MotionEvent.ACTION_UP:
                if (hasMoved) {
                    //如果已经移动了就执行恢复的动画
                    releaseTopContainerView(mOffset);
                    hasMoved = false;
                    //初始化偏移量
                    mOffset = 0;
                }


                break;


        }
        return super.dispatchTouchEvent(ev);

    }

    //缩小上方container动画
    public void scaleTopContainerView(float mOffset) {
        mBezierView.setArcHeight(mOffset / 5 + firstArc);
        int height = (int) (mOffset / 5 + firstArc) - 1;
        setBvParms(height);
    }

    //手释放动画
    public void releaseTopContainerView(float mOffset) {

        ValueAnimator mBvViewAnimtor = ValueAnimator.ofInt((int) (mOffset / 5), 0);
        mBvViewAnimtor.setDuration(300);
        mBvViewAnimtor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue() + firstArc;
                mBezierView.setArcHeight((int) animation.getAnimatedValue() + firstArc);
                setBvParms(height);
            }
        });
        mBvViewAnimtor.start();

    }

    //设置bv的高度
    private void setBvParms(int height) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mBezierView.getLayoutParams();
        layoutParams.height = height;
        mBezierView.setLayoutParams(layoutParams);
    }


}
