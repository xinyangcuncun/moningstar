package com.example.administrator.morningstar.view.tool;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.morningstar.view.activity.DemoIntentService;
import com.example.administrator.morningstar.view.activity.DemoPushService;
import com.igexin.sdk.PushManager;
import com.lqr.imagepicker.ImagePicker;
import com.lqr.imagepicker.loader.ImageLoader;
import com.lqr.imagepicker.view.CropImageView;

/**
 * Created by anson on 2017/4/5.
 */

public class MsApplication extends Application {
    @Override
    public void onCreate() {
        //严苛模式
        KqcStrictMode.strictModeCheck();
        initImagePicker();
        super.onCreate();
    }


    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                Glide.with(getApplicationContext()).load(Uri.parse("file://" + path).toString()).centerCrop().into(imageView);
            }

            @Override
            public void clearMemoryCache() {

            }
        });   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

}
