package com.example.administrator.morningstar.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.presenter.BasePresenter;
import com.example.administrator.morningstar.view.presenter.RetrofitPresenter;
import com.example.administrator.morningstar.view.presenter.VIgetView;
import com.example.administrator.morningstar.view.tool.ThreadPoolFactory;
import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.bean.ImageItem;
import com.lqr.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by anson on 2017/5/5.
 */

public class RetrofitMvpActivity extends BaseMvpActivity<VIgetView,RetrofitPresenter> implements VIgetView, QRCodeView.Delegate {

    private TextView textView;
    private ZXingView zXingView;

    public static void startMe(BaseActivity mContext) {
        Intent intent = new Intent(mContext,RetrofitMvpActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_main_zxing;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "Retrofit + Mvp";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected RetrofitPresenter createPresenter() {
        return new RetrofitPresenter(mContext);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        /*textView = (TextView) findViewById(R.id.tv_test_name);
        textView.setText("Retrofit+Mvp");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //业务下沉
                mPresenter.getData();
            }
        });*/
        zXingView = (ZXingView) findViewById(R.id.zxingview);
        textView = (TextView) findViewById(R.id.ib);
        zXingView.setDelegate(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageGridActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    public TextView getMvpTextView() {
        return textView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        zXingView.startCamera();
        zXingView.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        zXingView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zXingView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        handleResult(result);
    }


    private void handleResult(String result) {
        Log.d("tag-result", result);
        vibrate();
        zXingView.startSpot();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(mContext,"扫描出错",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {//返回多张照片
            if (data != null) {
                final ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() > 0) {
                    //取第一张照片
                    ThreadPoolFactory.getNormalPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            String result = QRCodeDecoder.syncDecodeQRCode(images.get(0).path);
                            if (TextUtils.isEmpty(result)) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext,"扫描出错",Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                handleResult(result);
                            }
                        }
                    });
                }
            }
        }
    }
}
