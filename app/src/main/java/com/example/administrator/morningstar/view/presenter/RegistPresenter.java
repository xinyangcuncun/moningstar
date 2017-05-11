package com.example.administrator.morningstar.view.presenter;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.activity.BaseMvpActivity;
import com.example.administrator.morningstar.view.activity.IRegistActivity;
import com.example.administrator.morningstar.view.activity.LoginActivity;
import com.example.administrator.morningstar.view.activity.MainActivity;
import com.example.administrator.morningstar.view.bean.CheckPhoneResponse;
import com.example.administrator.morningstar.view.bean.LoginResponse;
import com.example.administrator.morningstar.view.bean.RegisterResponse;
import com.example.administrator.morningstar.view.bean.SendCodeResponse;
import com.example.administrator.morningstar.view.bean.VerifyCodeResponse;
import com.example.administrator.morningstar.view.constant.AppConst;
import com.example.administrator.morningstar.view.http.ApiRetrofit;
import com.example.administrator.morningstar.view.tool.RegularUtils;
import com.example.administrator.morningstar.view.tool.ServerException;
import com.example.administrator.morningstar.view.tool.UIUtils;
import com.example.administrator.morningstar.view.tool.UserCache;

import java.util.Timer;
import java.util.TimerTask;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by anson on 2017/5/11.
 */

public class RegistPresenter extends BasePresenter<IRegistActivity>{

    private EditText editTextName;
    private EditText editTextPassWord;
    private Subscription mSubscription;
    private int time;
    private Timer mTimer;
    private EditText editTextToken;
    private Button login;

    public RegistPresenter(BaseMvpActivity context) {
        super(context);

    }

    public void register() {
        initView();

        if (canLogin()) {
            if (TextUtils.isEmpty(editTextName.getText().toString().trim())) {
                UIUtils.showToast("手机号不能为空");
                return;
            }

            if (TextUtils.isEmpty(editTextPassWord.getText().toString().trim())) {
                UIUtils.showToast("密码不能为空");
                return;
            }

            if (TextUtils.isEmpty(editTextToken.getText().toString().trim())) {
                UIUtils.showToast("验证码不能为空");
                return;
            }

            ApiRetrofit.getInstance().verifyCode(AppConst.REGION, editTextName.getText().toString().trim(), editTextToken.getText().toString().trim())
                    .flatMap(new Func1<VerifyCodeResponse, Observable<RegisterResponse>>() {
                        @Override
                        public Observable<RegisterResponse> call(VerifyCodeResponse verifyCodeResponse) {
                            int code = verifyCodeResponse.getCode();
                            if (code == 200) {
                                return ApiRetrofit.getInstance().register("renchangcun", editTextPassWord.getText().toString().trim(), verifyCodeResponse.getResult().getVerification_token());
                            } else {
                                return Observable.error(new ServerException(UIUtils.getString(R.string.vertify_code_error) + code));
                            }
                        }
                    })
                    .flatMap(new Func1<RegisterResponse, Observable<LoginResponse>>() {
                        @Override
                        public Observable<LoginResponse> call(RegisterResponse registerResponse) {
                            int code = registerResponse.getCode();
                            if (code == 200) {
                                return ApiRetrofit.getInstance().login(AppConst.REGION, editTextName.getText().toString().trim(), editTextPassWord.getText().toString().trim());
                            } else {
                                return Observable.error(new ServerException(UIUtils.getString(R.string.register_error) + code));
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginResponse -> {
                        int responseCode = loginResponse.getCode();
                        if (responseCode == 200) {
                            UserCache.save(loginResponse.getResult().getId(), editTextName.getText().toString().trim(), loginResponse.getResult().getToken());
                            MainActivity.startMe(mContext);
                        } else {
                            UIUtils.showToast(UIUtils.getString(R.string.login_error));
                            LoginActivity.startMe(mContext);
                        }
                    }, this::registerError);

        } else {
            UIUtils.showToast("请输入用户名／密码");
        }
    }

    private void initView() {
        editTextName = getView().getTieName().getEditText();
        editTextPassWord = getView().getTiePassword().getEditText();
        editTextToken= getView().getTieToken().getEditText();
        login = getView().getBtnLogin();
    }

    private void registerError(Throwable throwable) {
        UIUtils.showToast(throwable.getLocalizedMessage());
    }

    private boolean canLogin() {
        if (editTextName.getText().toString().trim().length() > 0 && editTextPassWord.getText().toString().trim().length() > 0) {
            return true;
        }
        return false;
    }

    public void sendCode() {
        initView();
        String phone = editTextName.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast("手机号不能为空");
            return;
        }

        if (!RegularUtils.isMobile(phone)) {
            UIUtils.showToast("手机号格式有误");
            return;
        }
        ApiRetrofit.getInstance().checkPhoneAvailable(AppConst.REGION, phone)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<CheckPhoneResponse, Observable<SendCodeResponse>>() {
                    @Override
                    public Observable<SendCodeResponse> call(CheckPhoneResponse checkPhoneResponse) {
                        int code = checkPhoneResponse.getCode();
                        if (code == 200) {
                            return ApiRetrofit.getInstance().sendCode(AppConst.REGION, phone);
                        } else {
                            return Observable.error(new ServerException(UIUtils.getString(R.string.phone_not_available)));
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sendCodeResponse -> {
                    int code = sendCodeResponse.getCode();
                    if (code == 200) {
                        changeSendCodeBtn();
                    } else {
                        sendCodeError(new ServerException(UIUtils.getString(R.string.send_code_error)));
                    }
                }, this::sendCodeError);
    }

    private void changeSendCodeBtn() {
        mSubscription = Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            time = 60;
            TimerTask mTask = new TimerTask() {
                @Override
                public void run() {
                    subscriber.onNext(--time);
                }
            };
            mTimer = new Timer();
            mTimer.schedule(mTask, 0, 1000);//每一秒执行一次Task
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> {
                    if (login != null) {
                        if (time >= 0) {
                            login.setEnabled(false);
                            login.setText(time + "");
                        } else {
                            login.setEnabled(true);
                            login.setText("发送验证码");
                        }
                    } else {
                        mTimer.cancel();
                    }
                });
    }

    private void sendCodeError(Throwable throwable) {
        UIUtils.showToast(throwable.getLocalizedMessage());
    }
}
