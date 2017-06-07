package com.example.administrator.morningstar.view.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by anson on 2017/6/7.
 */

public class GreenDaoManager extends DaoMaster.DevOpenHelper {
    private static GreenDaoManager mInstance;
    private final DaoSession daoSession;

    public GreenDaoManager(Context context) {
        super(context, "morning.db", null);
        DaoMaster daoMaster = new DaoMaster(this.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    //单例
    public static GreenDaoManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new GreenDaoManager(context);
        }
        return mInstance;
    }

    // session
    public DaoSession getSession() {
        return daoSession;
    }

}
