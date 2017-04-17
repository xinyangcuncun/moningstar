package com.example.administrator.morningstar.view.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.tool.DefaultAnimations;
import com.example.administrator.morningstar.view.tool.DefaultSort;
import com.example.administrator.morningstar.view.tool.Spruce;

/**
 * Created by anson on 2017/4/13.
 */

public class RxJavaActivity extends BaseActivity{

    private RecyclerView mRxJavaRlv;
    private String arr[] = {"Concat","Contains","Distinct","Delay","Buffer","Timer","from","just","Scan","Skip","Take"};
    private Animator spruceAnimator;

    @Override
    protected boolean isShowToolbar() {
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_rx_java;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "Rx_Java";
    }

    private void initData() {
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
                spruceAnimator = new Spruce.SpruceBuilder(mRxJavaRlv)
                        .sortWith(new DefaultSort(100))
                        .animateWith(DefaultAnimations.shrinkAnimator(mRxJavaRlv, 800),
                                ObjectAnimator.ofFloat(mRxJavaRlv, "translationX", -mRxJavaRlv.getWidth(), 0f).setDuration(800))
                        .start();
            }
        };
        mRxJavaRlv.setLayoutManager(layout);
        mRxJavaRlv.setAdapter(new RecyclerView.Adapter(){
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.rx_java_item,parent,false);
                RecyclerView.ViewHolder viewHolder = new MyViewHolder(view);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.textView.setText(arr[position]);
                myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //go to detail
                        RxJavaDetailActivity.startMe(mContext,arr[position]);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return arr.length;
            }
            class  MyViewHolder extends RecyclerView.ViewHolder {
                private final TextView textView;
                private final CardView cardView;
                public MyViewHolder(View itemView) {
                    super(itemView);
                    textView =  (TextView) itemView.findViewById(R.id.rx_java_tv);
                    cardView =  (CardView) itemView.findViewById(R.id.rx_java_cv);
                }
            }

        });

    }

    private void initView() {
        mRxJavaRlv = (RecyclerView) findViewById(R.id.rx_java_rlv);
    }

    public static void startMe(Context context) {
        Intent intent = new Intent(context,RxJavaActivity.class);
        context.startActivity(intent);
    }
}
