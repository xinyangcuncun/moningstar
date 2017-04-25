package com.example.administrator.morningstar.view.tool;

import android.content.Context;

import com.example.administrator.morningstar.view.activity.SplashActivity;

/**
 * Created by anson on 2017/4/5.
 */

public class ApplicationIo {

    private static ApplicationIo instance;
    private final InitMode1 mode1;
    private final Context mContext;

    public ApplicationIo(Context context) {
        mContext = context;
        mode1 = new InitMode1(context);

    }
    public static ApplicationIo getInstance(Context context) {
        if (instance == null) {
            synchronized (ApplicationIo.class) {
                if (instance == null) {
                    instance = new ApplicationIo(context);
                }
            }
        }
        return instance;
    }

    public void initMode1() {
        mode1.tryInitializeCore();
    }

    public void kill(boolean restart) {
        AppKit.kill(mContext, restart, SplashActivity.class);
    }
}
