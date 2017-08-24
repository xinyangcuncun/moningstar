package com.example.administrator.morningstar.view.refresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by anson on 2017/8/24.
 */

public class AnsonRefresh extends LinearLayout{

    private LinearLayout headView;
    private AnsonRefreshManager manager;
    private int maxheght;
    private int heght;

    public AnsonRefresh(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(VERTICAL);
        init();
    }

    private void init() {
        //初始化头部视图
        initHoleHeader();
    }

    /**
     * 设置管理器
     * @param manager
     */
    public void setRefreshManager(AnsonRefreshManager manager) {
        this.manager = manager;
        initSelfHeaderView();
    }

    private void initSelfHeaderView() {
        View seflHeadView = manager.getSeflHeadView();
        heght = - manager.getHeght();
        maxheght = (int) ( heght * (0.4f));
        headView.setPadding(0,maxheght,0,0);
        headView.addView(seflHeadView);
    }

    private void initHoleHeader() {
        headView = new LinearLayout(getContext());
        headView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        addView(headView);
    }

}
