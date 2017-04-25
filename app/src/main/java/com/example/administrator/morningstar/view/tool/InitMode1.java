package com.example.administrator.morningstar.view.tool;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

/**
 * Created by anson on 2017/4/5.
 */

class InitMode1 {
    private final Context mContext;

    public InitMode1(Context context) {
        mContext = context;
    }

    public synchronized void tryInitializeCore() {
        //初始化-阶段1
        tryInitializeCoreStage1();
    }

    private void tryInitializeCoreStage1() {
        //设置全局异常处理
        {
            //重启
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    Log.e(thread.getName(), ex.getMessage(), ex);
                    ApplicationIo.getInstance(mContext).kill(true);
                }
            });
        }
    }
}
