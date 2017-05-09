package com.example.administrator.morningstar.view.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.morningstar.R;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * Created by anson on 2017/5/8.
 */

public class DemoIntentService extends GTIntentService {

    private long timeMillis;
    private long timeMillis1;
    private static int pushID = 100;
    private NotificationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public DemoIntentService() {

    }
    /**
     * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
     * onReceiveMessageData 处理透传消息<br>
     * onReceiveClientId 接收 cid <br>
     * onReceiveOnlineState cid 离线上线通知 <br>
     * onReceiveCommandResult 各种事件处理回执 <br>
     */
    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        Log.e(TAG, "onReceiveClientId -> " + "cmsg = " + msg.toString());
        Notification.Builder builder1 = new Notification.Builder(this);
        builder1.setSmallIcon(R.drawable.sample_footer_loading); //设置图标
        builder1.setTicker("Kede通知");
        builder1.setContentTitle("可得眼镜"); //设置标题
        builder1.setContentText("hahahah"); //消息内容  +
        builder1.setWhen(System.currentTimeMillis()); //发送时间
        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
        builder1.setAutoCancel(true);//打开程序后图标消失
        if (pushID == 90) {
            pushID = 100;
        }
//        Intent intent1 = RxJavaActivity.startMeOut(context, true);
        Intent intent = new Intent(context,PlayerService.class); // 发送一个广播
        PendingIntent pendingIntent = PendingIntent.getBroadcast(DemoIntentService.this, 0, intent, 0);
        builder1.setContentIntent(pendingIntent);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            Notification notification1 = builder1.build();
            manager.notify(pushID, notification1); // 通过通知管理器发送通知
        }
        pushID--;
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
//        RxJavaActivity.startMeOut(this, false);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.e(TAG, "onReceiveClientId -> " + "cmdMessage = " + cmdMessage.toString());
    }
}
