package com.example.administrator.morningstar.view.manager;

import android.content.Context;
import android.util.Log;

import com.example.administrator.morningstar.view.constant.AppConst;
import com.example.administrator.morningstar.view.db.DaoSession;
import com.example.administrator.morningstar.view.db.GreenDaoManager;
import com.example.administrator.morningstar.view.db.MyBean;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;

import org.simple.eventbus.EventBus;

/**
 * Created by anson on 2017/6/13.
 */

public class DownLoadManagerListener extends FileDownloadListener {


    private final MyBean myBean;
    private final DaoSession session;

    public DownLoadManagerListener(MyBean myBean, Context context) {
        this.myBean = myBean;
        session = GreenDaoManager.getInstance(context).getSession();
    }

    @Override
    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        myBean.setState(AppConst.PENDING);
        session.getMyBeanDao().update(myBean);
        EventBus.getDefault().post(0,AppConst.DOWN_UPDATE);
        Log.d("tag--DownLoadManager--pending", "id :" + myBean.getDownid() + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());
    }

    @Override
    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
        myBean.setState(AppConst.BEGGIN);
        session.getMyBeanDao().update(myBean);
        EventBus.getDefault().post(0,AppConst.DOWN_UPDATE);
        Log.d("tag--DownLoadManager--connected", "id :" + myBean.getDownid() + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());
    }
    @Override
    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        myBean.setState(AppConst.DOWNING);
        myBean.setProgress(soFarBytes * 100/totalBytes);
        session.getMyBeanDao().update(myBean);
        EventBus.getDefault().post(0,AppConst.DOWN_UPDATE);
        Log.d("tag--DownLoadManager--progress", "id :" + myBean.getDownid() + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());
    }

    @Override
    protected void completed(BaseDownloadTask task) {
        myBean.setState(AppConst.ENDING);
        session.getMyBeanDao().update(myBean);
        EventBus.getDefault().post(0,AppConst.DOWN_UPDATE);
        Log.d("tag--DownLoadManager--completed", "id :" + myBean.getDownid() + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());
    }

    @Override
    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        myBean.setState(AppConst.CANCLE);
        session.getMyBeanDao().update(myBean);
        EventBus.getDefault().post(0,AppConst.DOWN_UPDATE);
        Log.d("tag--DownLoadManager--paused", "id :" + myBean.getDownid() + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());
    }

    @Override
    protected void error(BaseDownloadTask task, Throwable e) {
        myBean.setState(AppConst.ERROR);
        session.getMyBeanDao().update(myBean);
        EventBus.getDefault().post(0,AppConst.DOWN_UPDATE);
        Log.d("tag--DownLoadManager--error", "id :" + myBean.getDownid() + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());
    }

    @Override
    protected void warn(BaseDownloadTask task) {
        Log.d("tag--DownLoadManager--warn", "id :" + myBean.getDownid() + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());
    }

    @Override
    protected void blockComplete(BaseDownloadTask task) {
        Log.d("tag--DownLoadManager--blockComplete", "id :" + myBean.getDownid() + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());

    }

    @Override
    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
        Log.d("tag--DownLoadManager--retry", "id :" + myBean.getDownid() + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());

    }

}
