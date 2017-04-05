package com.example.administrator.morningstar.view.tool;

import android.app.Application;

/**
 * Created by anson on 2017/4/5.
 */

public class MsApplication extends Application {
    @Override
    public void onCreate() {
        //严苛模式
        KqcStrictMode.strictModeCheck();
        super.onCreate();
    }

}
