package com.example.administrator.morningstar.view.tool;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;

/**
 * Created by Anson on 2017/2/5.
 */

public  class ViewTools {

    //设置整个页面的背景颜色
   public static void setActivityBackGroudCoulor(Activity context , int color) {
        context.getWindow().setBackgroundDrawable(new ColorDrawable(color));
    }

}
