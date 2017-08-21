package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.adapter.QuickAdapter;
import com.example.administrator.morningstar.view.base.BaseActivity;

/**
 * Created by anson on 2017/8/14.
 */

public class BehaviorActivity1  extends BaseActivity{

    private Button button;
    private GestureDetector gestureDetector;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private Button button1;
    private BottomSheetDialog dialog;
    private FloatingActionButton floatingActionButton;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_behavior_1;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "BottomSheetBehavior";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    public static void startMe(BaseMvpActivity mContext, TextView tvOpenVideo) {
        mContext.startActivity(new Intent(mContext,BehaviorActivity1.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        gestureDetector = new GestureDetector(this, new OnVideoGestureListener());
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.rl_botomo));
        bottomSheetBehavior.setPeekHeight(0);
        button = (Button) findViewById(R.id.bt_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        button1 = (Button) findViewById(R.id.bt_show_dialog);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                } else {
                    dialog.show();
                }
            }
        });

        initBottomDialog();
    }

    private void initBottomDialog() {
        dialog = new BottomSheetDialog(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_behacior, null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.dialog_recycler_view);
        dialog.setContentView(inflate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new QuickAdapter(30));
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class OnVideoGestureListener extends GestureDetector.SimpleOnGestureListener {

        //处理单击事件 -- 控制面板的显示or隐藏
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
            return super.onSingleTapConfirmed(e);
        }
    }
}
