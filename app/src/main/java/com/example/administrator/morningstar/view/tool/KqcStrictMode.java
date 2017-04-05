package com.example.administrator.morningstar.view.tool;

import android.os.Build;
import android.os.StrictMode;

import com.example.administrator.morningstar.BuildConfig;


/**
 * 严苛模式（StrictMode），是Android提供的一种运行时检测机制，
 * 用于检测代码运行时的一些不规范的操作，最常见的场景是用于发现主线程的IO操作。
 * 应用程序可以利用StrictMode尽可能的发现一些编码的疏漏。
 * <p/>
 * 开启 StrictMode
 * 对于应用程序而言，Android 提供了一个最佳使用实践：
 * 尽可能早的在 android.app.Application 或 android.app.Activity 的生命周期使能
 * StrictMode，onCreate()方法就是一个最佳的时机，越早开启就能在更多的代码执行路径上发现违规操。
 * <p/>
 * 如果主线程有网络或磁盘读写等操作，在logcat中会有"D/StrictMode"tag的日志输出，从而定位到耗时操作的代码。
 * <p/>
 * Created by RainDrop on 2016/7/3.
 */
public class KqcStrictMode {

    public static void strictModeCheck() {
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
        }
    }
}
