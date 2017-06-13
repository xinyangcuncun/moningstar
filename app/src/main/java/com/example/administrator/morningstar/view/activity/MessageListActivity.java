package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.morningstar.Manifest;
import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.db.DaoMaster;
import com.example.administrator.morningstar.view.db.DaoSession;
import com.example.administrator.morningstar.view.db.GreenDaoManager;
import com.example.administrator.morningstar.view.db.MyBean;
import com.example.administrator.morningstar.view.db.MyBeanDao;
import com.example.administrator.morningstar.view.presenter.MessageListPresenter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by anson on 2017/5/25.
 */

public class MessageListActivity extends BaseMvpActivity<IMessageListActivity,MessageListPresenter> implements  IMessageListActivity{

    private RecyclerView recyclerView;
    private TextView tv;

    @Override
    protected int getViewLayout() {
        return R.layout.message_list_activity;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return "最近聊天";
    }

    @Override
    protected boolean isShowToolbar() {
        return true;
    }

    @Override
    protected MessageListPresenter createPresenter() {
        return new MessageListPresenter(mContext);
    }

    public static void startMe(BaseActivity mContext) {
        mContext.startActivity(new Intent(mContext,MessageListActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_message_list);
        tv = (TextView) findViewById(R.id.hhhhhhh);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndPermission.with(MessageListActivity.this)
                        .requestCode(100)
                        .permission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS).callback(listener).start();
            }
        });
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {

            if(requestCode == 100) {
                if (AndPermission.hasPermission(mContext,android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)) {
                    Toast.makeText(mContext,"成功回调：权限申请成功",Toast.LENGTH_LONG).show();
                } else {
                    AndPermission.defaultSettingDialog(MessageListActivity.this, 400)
                            .setTitle("权限申请失败")
                            .setMessage("您拒绝了我们必要的一些权限，已经没法愉快的玩耍了，请在设置中授权！")
                            .setPositiveButton("好，去设置")
                            .show();
                }
                Toast.makeText(mContext,"成功回调：权限申请成功",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == 100) {

               /* if (AndPermission.hasAlwaysDeniedPermission(MessageListActivity.this, deniedPermissions)) {
                    // 第二种：用自定义的提示语。
                    AndPermission.defaultSettingDialog(MessageListActivity.this, 400)
                            .setTitle("权限申请失败")
                            .setMessage("您拒绝了我们必要的一些权限，已经没法愉快的玩耍了，请在设置中授权！")
                            .setPositiveButton("好，去设置")
                            .show();

                }*/

                if (AndPermission.hasPermission(mContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)) {
                    Toast.makeText(mContext,"错误回调：权限申请成功",Toast.LENGTH_LONG).show();
                } else {
                    AndPermission.defaultSettingDialog(MessageListActivity.this, 400)
                            .setTitle("权限申请失败")
                            .setMessage("您拒绝了我们必要的一些权限，已经没法愉快的玩耍了，请在设置中授权！")
                            .setPositiveButton("好，去设置")
                            .show();

                }
            }
        }
    };
    @Override
    public RecyclerView getRv() {
        return recyclerView;
    }


    public static String getNewAmount(long amount) {
        String newAmount;
        if (amount >= 10000) {
            //超过一万则显示如1.3万
            double hit = new BigDecimal(amount / 1000).setScale(1, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(10)).doubleValue();
            newAmount = String.valueOf(hit).concat("万");
        } else {
            newAmount = String.valueOf(amount);
        }
        return newAmount;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 400: { // 这个400就是你上面传入的数字。
                Toast.makeText(mContext,"系统设置：权限申请成功",Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
