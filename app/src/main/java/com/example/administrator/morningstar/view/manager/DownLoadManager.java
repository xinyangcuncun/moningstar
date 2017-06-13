package com.example.administrator.morningstar.view.manager;

import android.content.Context;
import android.util.Log;

import com.example.administrator.morningstar.view.constant.AppConst;
import com.example.administrator.morningstar.view.db.DaoSession;
import com.example.administrator.morningstar.view.db.GreenDaoManager;
import com.example.administrator.morningstar.view.db.MyBean;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;

import org.simple.eventbus.EventBus;

import java.io.File;

/**
 * Created by anson on 2017/6/10.
 */

public class DownLoadManager {

    private static Context mContext;
    private static DownLoadManager instance;


    private DownLoadManager(Context context) {

    }

    public static DownLoadManager getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (null == instance) {
            instance = new DownLoadManager(mContext);
        }
        return instance;
    }

    /**
     * 单个任务下载 串行
     */

    public int  startDownLoadFileListSequentially(String url , String path, MyBean myBean ,Context context) {
        FileDownloader impl = FileDownloader.getImpl();
        impl.setMaxNetworkThreadCount(1);
        impl.enableAvoidDropFrame();
        int enqueue =
                impl.create(url)
                        .setPath(path)
                        .setAutoRetryTimes(0)
                        .setCallbackProgressMinInterval(2000)
                        .setListener(new DownLoadManagerListener(myBean,context))
                        .start();
        return enqueue;
    }

    public void pauseDownLoad(int id) {
        FileDownloader.getImpl().pause(id);
    }
    public void startDownLoad(MyBean bean,Context context) {
        startDownLoadFileListSequentially(bean.getUrl(),bean.getFilepath(),bean,context);
    }

    public void cleanDownLoad(MyBean bean) {
        FileDownloader.getImpl().clear(bean.getDownid(), bean.getFilepath());
    }



}
