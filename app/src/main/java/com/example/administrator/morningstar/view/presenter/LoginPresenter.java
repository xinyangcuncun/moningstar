package com.example.administrator.morningstar.view.presenter;

import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.activity.BaseMvpActivity;
import com.example.administrator.morningstar.view.activity.ILoginActivity;
import com.example.administrator.morningstar.view.activity.MainActivity;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.bean.LoginResponse;
import com.example.administrator.morningstar.view.constant.AppConst;
import com.example.administrator.morningstar.view.http.ApiRetrofit;
import com.example.administrator.morningstar.view.tool.ServerException;
import com.example.administrator.morningstar.view.tool.UIUtils;
import com.example.administrator.morningstar.view.tool.UserCache;


import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by anson on 2017/5/11.
 */

public class LoginPresenter extends BasePresenter<ILoginActivity> {

    private EditText editTextName;
    private EditText editTextPassWord;

    public LoginPresenter(BaseMvpActivity context) {
        super(context);

    }

    public void Loging() {
        editTextName = getView().getTieName().getEditText();
        editTextPassWord = getView().getTiePassword().getEditText();
        if (canLogin()) {
            ApiRetrofit.getInstance().login(AppConst.REGION, editTextName.getText().toString().trim(), editTextPassWord.getText().toString().trim()
            )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginResponse -> {
                        int code = loginResponse.getCode();
                        if (code == 200) {
                            UserCache.save(loginResponse.getResult().getId(), editTextName.getText().toString().trim(), loginResponse.getResult().getToken());
                            MainActivity.startMe(mContext);
                            mContext.finish();
                        } else {
                            loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
                        }
                    }, this::loginError);
        } else {
            UIUtils.showToast("请输入用户名／密码");
        }

    }

    private boolean canLogin() {
        if (editTextName.getText().toString().trim().length() > 0 && editTextPassWord.getText().toString().trim().length() > 0) {
            return true;
        }
        return false;
    }

    private void loginError(Throwable throwable) {
        UIUtils.showToast(throwable.getLocalizedMessage());
    }
}
