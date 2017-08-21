package com.example.administrator.morningstar.view.presenter;

import android.view.View;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.activity.BaseMvpActivity;
import com.example.administrator.morningstar.view.activity.BehaviorActivity1;
import com.example.administrator.morningstar.view.activity.BehaviorActivity2;
import com.example.administrator.morningstar.view.activity.BehaviorActivity3;
import com.example.administrator.morningstar.view.activity.BehaviorActivity4;
import com.example.administrator.morningstar.view.activity.IGSYPlayerActivity;
import com.example.administrator.morningstar.view.activity.MusicActivity;

import static com.example.administrator.morningstar.view.activity.SigleVideoPlayer.startMe;

/**
 * Created by anson on 2017/8/8.
 */

public class GSYPlayerPresenter extends BasePresenter<IGSYPlayerActivity> implements View.OnClickListener {

    private TextView tvOpenVideo;

    public GSYPlayerPresenter(BaseMvpActivity context) {
        super(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_open_video:
                startMe(mContext,tvOpenVideo);
                break;
            case R.id.tv_music:
                MusicActivity.startMe(mContext,tvOpenVideo);
                break;
            case R.id.tv_behavior_1:
                BehaviorActivity1.startMe(mContext,tvOpenVideo);
                break;
            case R.id.tv_behavior_2:
                BehaviorActivity2.startMe(mContext,tvOpenVideo);
                break;
            case R.id.tv_behavior_3:
                BehaviorActivity3.startMe(mContext,tvOpenVideo);
                break;
            case R.id.tv_behavior_4:
                BehaviorActivity4.startMe(mContext);
                break;
        }
    }

    public void initPresenterView() {
        tvOpenVideo = getView().getTvOpenVideo();
    }
}
