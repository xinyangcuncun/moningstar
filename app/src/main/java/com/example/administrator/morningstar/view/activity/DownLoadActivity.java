package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.constant.AppConst;
import com.example.administrator.morningstar.view.constant.Constant;
import com.example.administrator.morningstar.view.db.DaoSession;
import com.example.administrator.morningstar.view.db.GreenDaoManager;
import com.example.administrator.morningstar.view.db.MyBean;
import com.example.administrator.morningstar.view.db.MyBeanDao;
import com.example.administrator.morningstar.view.manager.DownLoadManager;
import com.example.administrator.morningstar.view.tool.SPUtils;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.io.File;
import java.util.Random;

/**
 * Created by anson on 2017/6/10.
 */

public class DownLoadActivity extends BaseActivity{

    private Button button;
    private Button button1;
    String LIULISHUO_APK_URL = "http://cdn.llsapp.com/android/LLS-v4.0-595-20160908-143200.apk";
    String llsApkDir = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "tmpdir";
    String[] BIG_FILE_URLS = {
            // 5m
            "http://mirror.internode.on.net/pub/test/5meg.test5",
            // 6m
            "http://download.chinaunix.net/down.php?id=10608&ResourceID=5267&site=1",
            // 8m
            "http://7xjww9.com1.z0.glb.clouddn.com/Hopetoun_falls.jpg",
            // 10m
            "http://dg.101.hk/1.rar",
            // 342m
            "http://180.153.105.144/dd.myapp.com/16891/E2F3DEBB12A049ED921C6257C5E9FB11.apk",
//            "http://mirror.internode.on.net/pub/test/5meg.test4",
//            "http://mirror.internode.on.net/pub/test/5meg.test3",
//            "http://mirror.internode.on.net/pub/test/5meg.test2",
//            "http://mirror.internode.on.net/pub/test/5meg.test1",
            // 6.8m
//            "http://dlsw.baidu.com/sw-search-sp/soft/7b/33461/freeime.1406862029.exe",
            // 10m
//            "http://mirror.internode.on.net/pub/test/10meg.test",
//            "http://mirror.internode.on.net/pub/test/10meg.test1",
//            "http://mirror.internode.on.net/pub/test/10meg.test2",
//            "http://mirror.internode.on.net/pub/test/10meg.test3",
            // 10m
            "http://mirror.internode.on.net/pub/test/10meg.test4",
//            "http://mirror.internode.on.net/pub/test/10meg.test5",
            // 20m
            "http://www.pc6.com/down.asp?id=72873",
            // 22m
            "http://113.207.16.84/dd.myapp.com/16891/2E53C25B6BC55D3330AB85A1B7B57485.apk?mkey=5630b43973f537cf&f=cf87&fsname=com.htshuo.htsg_3.0.1_49.apk&asr=02f1&p=.apk",
            // 206m
            "http://down.tech.sina.com.cn/download/d_load.php?d_id=49535&down_id=1&ip=42.81.45.159"
    };

    private int key = 0;

    @Override
    protected int getViewLayout() {
        return R.layout.down_load_activity;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "下载管理";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    public static void startMe(BaseActivity mContext) {
        Intent intent = new Intent(mContext, DownLoadActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        button = (Button) findViewById(R.id.btn_all_download);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (key > 8) {
                    Toast.makeText(mContext,"chaochu le ",Toast.LENGTH_LONG).show();
                    return;
                }
                String url = BIG_FILE_URLS[key];
                DaoSession session = GreenDaoManager.getInstance(mContext).getSession();
                MyBeanDao myBeanDao = session.getMyBeanDao();
                MyBean myBean = new MyBean(null,"tmpdir" + key,null, AppConst.BEGGIN,llsApkDir + key,0);
                int i1 = DownLoadManager.getInstance(mContext).startDownLoadFileListSequentially(url, llsApkDir + key, myBean,mContext);
                myBean.setDownid(i1);
                myBean.setUrl(url);
                myBeanDao.insert(myBean);
                Log.d("tag--DownLoadActivity", "id :" + i1 + "name :" + myBean.getUsername() + "path :" + myBean.getFilepath());
                key++;
            }
        });

        button1 = (Button) findViewById(R.id.btn_go_download_list);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownLoadListActivity.startMe(mContext);
            }
        });


    }
}
