package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.presenter.RegistPresenter;
import com.superrtc.call.DataChannel;

/**
 * Created by anson on 2017/5/11.
 */

public class RegistActivity extends BaseMvpActivity<IRegistActivity,RegistPresenter> implements IRegistActivity{
    private TextInputLayout tieName;
    private TextInputLayout tiePassword;
    private Button btLogin;
    private Button btRegist;
    private TextInputLayout tieToken;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_login_activity;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "请注册";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected RegistPresenter createPresenter() {
        return new RegistPresenter(mContext);
    }

    public static void startMe(BaseMvpActivity mContext) {
        Intent intent = new Intent(mContext, RegistActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        btRegist.setOnClickListener(v -> {
            mPresenter.register();
        });
        btLogin.setOnClickListener(v -> {
            mPresenter.sendCode();
        });
    }

    private void initView() {
        tieName = (TextInputLayout) findViewById(R.id.til_name);
        tiePassword = (TextInputLayout) findViewById(R.id.til_password);
        tieToken = (TextInputLayout) findViewById(R.id.til_token);
        btLogin = (Button) findViewById(R.id.bt_login);
        btRegist = (Button) findViewById(R.id.bt_regist);
        btLogin.setText("发送验证码");
    }

    @Override
    public Button getBtnLogin() {
        return btLogin;
    }

    @Override
    public Button getBtnRegist() {
        return btRegist;
    }

    @Override
    public TextInputLayout getTieName() {
        return tieName;
    }

    @Override
    public TextInputLayout getTiePassword() {
        return tiePassword;
    }

    @Override
    public TextInputLayout getTieToken() {
        return tieToken;
    }
}
