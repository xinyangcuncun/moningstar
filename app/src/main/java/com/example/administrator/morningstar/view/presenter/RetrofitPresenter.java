package com.example.administrator.morningstar.view.presenter;

import android.widget.TextView;

import com.example.administrator.morningstar.view.activity.BaseMvpActivity;

/**
 * Created by anson on 2017/5/5.
 */

public class RetrofitPresenter extends BasePresenter <VIgetView> {

    private TextView mvpTextView;

    public RetrofitPresenter(BaseMvpActivity mContext) {
        super(mContext);
    }
    public void getData() {
       /* mvpTextView = getView().getMvpTextView();
        mvpTextView.setText("已经通过Mvp完成数据传递");*/
    }
}
