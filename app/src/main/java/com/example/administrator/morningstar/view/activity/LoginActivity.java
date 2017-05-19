package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.presenter.LoginPresenter;

/**
 * Created by anson on 2017/5/11.
 */

public class LoginActivity extends BaseMvpActivity<ILoginActivity,LoginPresenter> implements ILoginActivity{

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
        return "登陆or注册";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(mContext);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        btLogin.setOnClickListener(v -> {
            mPresenter.Loging();
        });
        btRegist.setOnClickListener(v -> {
            RegistActivity.startMe(mContext);
        });
    }

    private void initView() {
        tieName = (TextInputLayout) findViewById(R.id.til_name);
        tiePassword = (TextInputLayout) findViewById(R.id.til_password);
        tieToken = (TextInputLayout) findViewById(R.id.til_token);
        btLogin = (Button) findViewById(R.id.bt_login);
        btRegist = (Button) findViewById(R.id.bt_regist);
        tieToken.setVisibility(View.INVISIBLE);

    }

    public static void startMe(BaseActivity mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
    public static void startMe(BaseMvpActivity mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
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
}
