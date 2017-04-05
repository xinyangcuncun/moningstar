/*
package com.example.administrator.morningstar.view.tool;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


*/
/**
 * 对话框集合
 *//*

final public class DialogBundle {
    */
/**
     * 不允许实例化
     *//*

    DialogBundle() {
    }

    */
/**
     * 确认对话框
     *//*

    public static class AlertDialog extends Dialog {
        */
/**
         * 点击事件
         *//*

        public interface Listener {
            void click();
        }

        */
/**
         * 构造器
         *//*

        public static class Builder {
            Context context;
            CharSequence title;
            CharSequence message;
            CharSequence positiveText;
            CharSequence negativeText;
            Boolean outsideCancelable;

            Listener positiveListener;
            Listener negativeListener;

            int titleGravity = -1;
            @ColorInt
            int titleColor;
            int messageGravity = -1;
            @ColorInt
            int messageColor;
            @ColorInt
            int positiveColor;
            @ColorInt
            int negativeColor;

            public Builder(Context context) {
                this.context = context;
            }

            */
/**
             * 设置提示消息
             *//*

            public Builder setMessage(String message) {
                this.message = message;
                return this;
            }

            */
/**
             * 设置提示消息
             *
             * @param message 资源id
             *//*

            public Builder setMessage(int message) {
                this.message = this.context.getResources().getText(message);
                return this;
            }

            */
/**
             * 设置消息颜色
             * @param messageColor
             * @return
             *//*

            public Builder setMessageColor(@ColorInt int messageColor) {
                this.messageColor = messageColor;
                return this;
            }

            */
/**
             * 设置消息位置
             * @param messageGravity
             * @return
             *//*

            public Builder setMessageGravity(int messageGravity) {
                this.messageGravity = messageGravity;
                return this;
            }


            */
/**
             * 设置提示标题
             *//*

            public Builder setTitle(String title) {
                this.title = title;
                return this;
            }

            */
/**
             * 设置提示标题
             *
             * @param title rsid 资源id
             *//*

            public Builder setTitle(int title) {
                this.title = this.context.getResources().getText(title);
                return this;
            }

            */
/**
             * 设置标题颜色
             * @param titleColor
             * @return
             *//*

            public Builder setTitleColor(@ColorInt int titleColor) {
                this.titleColor = titleColor;
                return this;
            }

            */
/**
             * 设置标题位置
             * @param titleGravity
             * @return
             *//*

            public Builder setTitleGravity(int titleGravity) {
                this.titleGravity = titleGravity;
                return this;
            }

            */
/**
             * 确定按钮
             *//*

            public Builder setPositiveButton(String positiveButtonText, Listener listener) {
                this.positiveText = positiveButtonText;
                this.positiveListener = listener;
                return this;
            }

            public Builder setPositiveButton(int positiveButtonText, Listener listener) {
                this.positiveText = this.context.getResources().getText(positiveButtonText);
                this.positiveListener = listener;
                return this;
            }

            */
/**
             * 确定按钮颜色
             * @param positiveColor
             *//*

            public Builder setPositiveColor(@ColorInt int positiveColor) {
                this.positiveColor = positiveColor;
                return this;
            }

            */
/**
             * 取消按钮
             *//*

            public Builder setNegativeButton(String negativeButtonText, Listener listener) {
                this.negativeText = negativeButtonText;
                this.negativeListener = listener;
                return this;
            }

            public Builder setNegativeButton(int negativeButtonText, Listener listener) {
                this.negativeText = this.context.getResources().getText(negativeButtonText);
                this.negativeListener = listener;
                return this;
            }

            */
/**
             * 取消按钮颜色
             * @param negativeColor
             * @return
             *//*

            public Builder setNegativeColor(@ColorInt int negativeColor) {
                this.negativeColor = negativeColor;
                return this;
            }

            */
/**
             * 设置点击dialog外部是否可以取消
             *
             * @param outsideCancelable
             * @return
             *//*

            public Builder setCancelable(boolean outsideCancelable) {
                this.outsideCancelable = outsideCancelable;
                return this;
            }

            */
/**
             * 构造
             *//*

            public AlertDialog build() {
                return new AlertDialog(context, this);
            }
        }

        //VIEW ROOT
        LinearLayout ll_root;
        //content view
        LinearLayout ll_content;
        //提示标题
        TextView tv_title;
        //提示消息
        TextView tv_message;
        //确定和取消栏目
        LinearLayout ll_cancel_and_sure;
        //取消
        TextView tv_cancel;
        //按钮中间虚线
        View v_middle_line;
        //确定
        TextView tv_sure;

        AlertDialog(Context context, final Builder builder) {
            super(context, R.style.alert_dialog);
            //UI
            {
                ll_root = new LinearLayout(context);
                {
                    //样式
                    {
                        ll_root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        ll_root.setOrientation(LinearLayout.VERTICAL);
                        ll_root.setGravity(Gravity.CENTER);
                        RoundRectShape roundRectShape = new RoundRectShape(new float[]{30, 30, 30, 30, 30, 30, 30, 30}, null, null);
                        ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
                        drawable.getPaint().setColor(Color.parseColor("#FFFFFF"));
                        drawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                        ll_root.setBackgroundDrawable(drawable);
                    }
                    //content容器
                    ll_content = new LinearLayout(context);
                    {
                        ll_root.addView(ll_content);
                        //样式
                        {
                            ll_content.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            ll_content.setMinimumHeight(dp2px(context, 80));
                            ll_content.setOrientation(LinearLayout.VERTICAL);
                            ll_content.setPadding(dp2px(context, 24), dp2px(context, 32), dp2px(context, 24), dp2px(context, 32));
                        }
                        //内容配置
                        {
                            //标题
                            tv_title = new TextView(context);
                            //配置
                            {
                                ll_content.addView(tv_title);
                                //样式
                                {
                                    tv_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                    tv_title.setPadding(dp2px(context, 18), 0, dp2px(context, 18), 0);
                                    tv_title.setTextSize(15);
                                }
                            }
                            //内容
                            tv_message = new TextView(context);
                            //配置
                            {
                                ll_content.addView(tv_message);
                                //样式
                                {
                                    tv_message.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                    tv_message.setGravity(Gravity.CENTER);
                                    tv_message.setPadding(dp2px(context, 18), dp2px(context, 12), dp2px(context, 18), 0);
                                    tv_message.setTextSize(15);
                                }

                            }
                        }
                        //内容虚线
                        View v_message_line = new View(context);
                        {
                            ll_root.addView(v_message_line);
                            //样式
                            {
                                v_message_line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                                v_message_line.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EFEFEF")));
                            }

                        }
                        //设置 取消 和 确定
                        ll_cancel_and_sure = new LinearLayout(context);
                        //设置
                        {
                            ll_root.addView(ll_cancel_and_sure);
                            //样式
                            {
                                ll_cancel_and_sure.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(context, 48)));
                                ll_cancel_and_sure.setGravity(Gravity.CENTER_VERTICAL);
                                ll_cancel_and_sure.setOrientation(LinearLayout.HORIZONTAL);
                            }
                            //子View
                            {
                                tv_cancel = new TextView(context);
                                //配置
                                {
                                    ll_cancel_and_sure.addView(tv_cancel);
                                    //样式
                                    {
                                        tv_cancel.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                                        tv_cancel.setGravity(Gravity.CENTER);
                                        tv_cancel.setSingleLine();
                                        tv_cancel.setTextSize(15);
                                        tv_cancel.setText("取消");
                                    }
                                }
                                //中间虚线
                                v_middle_line = new View(context);
                                {
                                    ll_cancel_and_sure.addView(v_middle_line);
                                    //样式
                                    {
                                        v_middle_line.setLayoutParams(new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));
                                        v_middle_line.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EFEFEF")));
                                    }
                                }
                                //确定按钮
                                tv_sure = new TextView(context);
                                //配置
                                {
                                    ll_cancel_and_sure.addView(tv_sure);
                                    //样式
                                    {
                                        tv_sure.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                                        tv_sure.setGravity(Gravity.CENTER);
                                        tv_sure.setSingleLine();
                                        tv_sure.setTextSize(15);
                                        tv_sure.setText("确定");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //配置
            {
                //设置视图
                setContentView(ll_root);
                //取消
                setCancelable(true);
                // 设置居中 和 背景色
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                // 设置背景层透明度
                lp.dimAmount = 0.4f;
                lp.gravity = Gravity.CENTER;
                //设置dialog的宽度
                WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                if (windowManager != null) {
                    Display display = windowManager.getDefaultDisplay();
                    lp.width = (int) (display.getWidth() * 0.75);
                }
                getWindow().setAttributes(lp);
            }
            //根据builder 显示不同的样式
            {
                //配置按钮栏目
                if (builder.negativeListener == null && builder.positiveListener == null) {
                    //没有任何按钮
                    ll_cancel_and_sure.setVisibility(View.GONE);
                } else {
                    //存在其中一个按钮
                    ll_cancel_and_sure.setVisibility(View.VISIBLE);
                    if (builder.negativeListener != null && builder.positiveListener != null) {
                        //都存在
                        v_middle_line.setVisibility(View.VISIBLE);
                        tv_cancel.setVisibility(View.VISIBLE);
                        tv_sure.setVisibility(View.VISIBLE);
                        //设置背景
                        tv_cancel.setBackgroundResource(R.drawable.selector_item_white_left_bottom_bg);
                        tv_sure.setBackgroundResource(R.drawable.selector_item_white_right_bottom_bg);
                    } else {
                        //隐藏虚线
                        v_middle_line.setVisibility(View.GONE);
                        if (builder.negativeListener != null && builder.positiveListener == null) {
                            //只有取消
                            tv_cancel.setVisibility(View.VISIBLE);
                            tv_sure.setVisibility(View.GONE);
                            //设置背景
                            tv_cancel.setBackgroundResource(R.drawable.selector_item_white_bottom_bg);
                        } else {
                            //只有确定
                            tv_cancel.setVisibility(View.GONE);
                            tv_sure.setVisibility(View.VISIBLE);
                            //设置背景
                            tv_sure.setBackgroundResource(R.drawable.selector_item_white_bottom_bg);
                        }
                    }
                }
                //设置左侧按钮颜色
                if (builder.negativeColor == 0) {
                    tv_cancel.setTextColor(Color.parseColor("#FF7206"));
                } else {
                    tv_cancel.setTextColor(builder.negativeColor);
                }
                //设置右侧按钮颜色
                if (builder.positiveColor == 0) {
                    tv_sure.setTextColor(Color.parseColor("#FF7206"));
                } else {
                    tv_sure.setTextColor(builder.positiveColor);
                }
                //设置title
                if (builder.title != null) {
                    tv_title.setText(builder.title);
                } else {
                    tv_title.setVisibility(View.GONE);
                }
                //设置title颜色
                if (builder.titleColor == 0) {
                    tv_title.setTextColor(Color.parseColor("#333333"));
                } else {
                    tv_title.setTextColor(builder.titleColor);
                }
                //设置title gravity
                if (builder.titleGravity == -1) {
                    tv_title.setGravity(Gravity.CENTER);
                } else {
                    tv_title.setGravity(builder.titleGravity);
                }
                //设置消息
                if (builder.message != null) {
                    tv_message.setText(builder.message);
                } else {
                    tv_message.setVisibility(View.GONE);
                }
                //设置消息颜色
                if (builder.messageColor == 0) {
                    tv_message.setTextColor(Color.parseColor("#999999"));
                } else {
                    tv_message.setTextColor(builder.messageColor);
                }
                //设置消息 gravity
                if (builder.messageGravity == -1) {
                    tv_message.setGravity(Gravity.CENTER);
                } else {
                    tv_message.setGravity(builder.messageGravity);
                }
                //取消按钮标识
                if (builder.negativeText != null) {
                    tv_cancel.setText(builder.negativeText);
                }
                //取消监听器
                if (builder.negativeListener != null) {
                    tv_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.this.dismiss();
                            builder.negativeListener.click();
                        }
                    });
                }
                //确定按钮
                if (builder.positiveText != null) {
                    tv_sure.setText(builder.positiveText);
                }
                //确定callback
                if (builder.positiveListener != null) {
                    tv_sure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.this.dismiss();
                            builder.positiveListener.click();
                        }
                    });
                }
            }
            //设置点击屏幕外部是否可以取消
            {
                if (builder.outsideCancelable != null) {
                    setCanceledOnTouchOutside(builder.outsideCancelable);
                }
            }
        }
    }

    */
/**
     * 提示dialog
     *//*

    public static class PromptDialog extends Toast {
        static public class Builder {
            Context context;

            CharSequence alert;
            int icon;
            int delay;

            public Builder(Context context) {
                this.context = context;
            }

            public Builder setAlert(String alert) {
                this.alert = alert;
                return this;
            }

            public Builder setAlert(int alert) {
                this.alert = this.context.getResources().getText(alert);
                return this;
            }

            public Builder setIcon(int icon) {
                this.icon = icon;
                return this;
            }

            */
/**
             * 延迟时间 ms
             *
             * @param delay
             * @return
             *//*

            public Builder setDelay(int delay) {
                this.delay = delay;
                return this;
            }

            public PromptDialog build() {
                return new PromptDialog(context, this);
            }
        }

        //VIEW ROOT
        LinearLayout ll_root;
        //ICON
        ImageView iv_icon;
        //ALERT
        TextView tv_alert;

        //handler
        Handler handler;
        //builder
        int delay;
        //默认延时
        final int defaultDelay = 1000;

        PromptDialog(Context context, Builder builder) {
            super(context);
            //UI
            {
                ll_root = new LinearLayout(context);
                //样式
                {
                    ll_root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    ll_root.setOrientation(LinearLayout.VERTICAL);
                    ll_root.setMinimumWidth(dp2px(context, 230));
                    ll_root.setPadding(dp2px(context, 30), dp2px(context, 50), dp2px(context, 30), dp2px(context, 50));
                    ll_root.setGravity(Gravity.CENTER);
                    RoundRectShape roundRectShape = new RoundRectShape(new float[]{30, 30, 30, 30, 30, 30, 30, 30}, null, null);
                    ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
                    drawable.getPaint().setColor(Color.parseColor("#FFFFFF"));
                    drawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                    ll_root.setBackgroundDrawable(drawable);
                }
                //子VIEW
                {
                    //ICON
                    {
                        iv_icon = new ImageView(context);
                        ll_root.addView(iv_icon);
                        //样式
                        {
                            LinearLayout.LayoutParams lp_iv_icon = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            lp_iv_icon.setMargins(0, 0, 0, dp2px(context, 10));
                            iv_icon.setLayoutParams(lp_iv_icon);
                            iv_icon.setImageResource(R.drawable.app_success_icon);
                        }
                    }
                    //ALERT
                    {
                        tv_alert = new TextView(context);
                        ll_root.addView(tv_alert);
                        //样式
                        {
                            LinearLayout.LayoutParams lp_tv_alert = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            lp_tv_alert.setMargins(0, dp2px(context, 16), 0, 0);
                            tv_alert.setLayoutParams(lp_tv_alert);
                            tv_alert.setTextColor(Color.parseColor("#FF7206"));
                            tv_alert.setVisibility(View.GONE);
                            tv_alert.setBackgroundColor(Color.parseColor("#00000000"));
                            tv_alert.setGravity(Gravity.CENTER);
                            tv_alert.setTextSize(15);
                        }
                    }
                }
            }
            //配置
            {
                setView(ll_root);
                setGravity(Gravity.CENTER, 0, 0);
                setDuration(Toast.LENGTH_SHORT);
                if (builder.icon > 0) {
                    //设置图片
                    iv_icon.setImageResource(builder.icon);
                }
                if (builder.alert != null && builder.alert.length() > 0) {
                    tv_alert.setText(builder.alert);
                    tv_alert.setVisibility(View.VISIBLE);
                }
                this.delay = builder.delay <= 0 ? defaultDelay : builder.delay;
                this.handler = new Handler(Looper.getMainLooper());
            }
        }

        @Override
        public void show() {
            super.show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    PromptDialog.this.cancel();
                }
            }, this.delay);
        }
    }

    */
/**
     * 显示Toast类型的Dialog
     *//*

    public static class ToastDialog extends Toast {
        static public class Builder {
            Context context;

            CharSequence alert;
            int delay;

            public Builder(Context context) {
                this.context = context;
            }

            public Builder setAlert(String alert) {
                this.alert = alert;
                return this;
            }

            public Builder setAlert(int alert) {
                this.alert = this.context.getResources().getText(alert);
                return this;
            }

            public Builder setDelay(int delay) {
                this.delay = delay;
                return this;
            }

            public ToastDialog build() {
                return new ToastDialog(context, this);
            }
        }

        //VIEW ROOT
        LinearLayout ll_root;
        //ALERT
        TextView tv_alert;

        //handler
        Handler handler;
        //builder
        int delay;
        //默认延时
        final int defaultDelay = 1000;

        ToastDialog(Context context, Builder builder) {
            super(context);
            //UI
            {
                ll_root = new LinearLayout(context);
                //样式
                {
                    ll_root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    ll_root.setOrientation(LinearLayout.VERTICAL);
                    ll_root.setMinimumWidth(dp2px(context, 270));
                    ll_root.setPadding(dp2px(context, 30), dp2px(context, 3), dp2px(context, 30), dp2px(context, 3));
                    ll_root.setGravity(Gravity.CENTER);
                    RoundRectShape roundRectShape = new RoundRectShape(new float[]{10, 10, 10, 10, 10, 10, 10, 10}, null, null);
                    ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
                    drawable.getPaint().setColor(Color.parseColor("#7f333333"));
                    drawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                    ll_root.setBackgroundDrawable(drawable);
                }
                //子VIEW
                {
                    //ALERT
                    {
                        tv_alert = new TextView(context);
                        ll_root.addView(tv_alert);
                        //样式
                        {
                            tv_alert.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            tv_alert.setTextColor(Color.parseColor("#FFFFFF"));
                            tv_alert.setTextSize(15);
                            tv_alert.setBackgroundColor(Color.parseColor("#00000000"));
                            tv_alert.setGravity(Gravity.CENTER);
                        }
                    }
                }
            }
            //配置
            {
                setView(ll_root);
                setGravity(Gravity.CENTER, 0, 0);
                setDuration(Toast.LENGTH_SHORT);
                if (builder.alert != null && builder.alert.length() > 0) {
                    tv_alert.setText(builder.alert);
                    tv_alert.setVisibility(View.VISIBLE);
                }
                this.delay = builder.delay <= 0 ? defaultDelay : builder.delay;
                this.handler = new Handler(Looper.getMainLooper());
            }
        }

        @Override
        public void show() {
            super.show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ToastDialog.this.cancel();
                }
            }, this.delay);
        }
    }

    public static class ListDialog extends Dialog {

        */
/**
         * 监听器
         *//*

        public interface Listener {
            */
/**
             * 点击了条目
             *//*

            void click(int position);

            */
/**
             * 取消了点击
             *//*

            void cancel();
        }

        */
/**
         * 构造器
         *//*

        public static class Builder {

            Context context;
            CharSequence title;
            List<String> holderList = new ArrayList<String>();
            Listener listener;
            Boolean outsideCancelable;

            public Builder(Context context) {
                this.context = context;
            }

            */
/**
             * 设置Title
             *//*

            public Builder setTile(String title) {
                this.title = title;
                return this;
            }

            public Builder setTile(int title) {
                this.title = this.context.getResources().getText(title);
                return this;
            }

            */
/**
             * 添加一个选项
             *//*

            public Builder addItem(String what) {
                holderList.add(what);
                return this;
            }

            */
/**
             * 添加展示列表
             *
             * @param list
             * @return
             *//*

            public Builder setList(@NonNull List<String> list) {
                holderList = list;
                return this;
            }

            */
/**
             * 设置监听器
             *//*

            public Builder setListener(Listener listener) {
                this.listener = listener;
                return this;
            }

            */
/**
             * 设置点击外部是否可取消
             *//*

            public Builder setCancelable(boolean outsideCancelable) {
                this.outsideCancelable = outsideCancelable;
                return this;
            }

            */
/**
             * 构造
             *//*

            public ListDialog build() {
                return new ListDialog(context, this);
            }
        }

        //VIEW ROOT
        LinearLayout ll_root;
        //Title Layout
        LinearLayout ll_title;
        //Title
        TextView tv_title;
        //ListView
        ListView lv_main;

        ListDialog(final Context context, final Builder builder) {
            super(context, R.style.list_dialog);
            //UI
            {
                ll_root = new LinearLayout(context);
                //样式
                {
                    ll_root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    ll_root.setOrientation(LinearLayout.VERTICAL);
                    ll_root.setGravity(Gravity.CENTER);
                    RoundRectShape roundRectShape = new RoundRectShape(new float[]{20, 20, 20, 20, 20, 20, 20, 20}, null, null);
                    ShapeDrawable drawable = new ShapeDrawable(roundRectShape);
                    drawable.getPaint().setColor(Color.parseColor("#FFFFFF"));
                    drawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                    ll_root.setBackgroundDrawable(drawable);

                }
                //子View
                {
                    //Title
                    {
                        ll_title = new LinearLayout(context);
                        ll_root.addView(ll_title);
                        //样式
                        {
                            ll_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            ll_title.setOrientation(LinearLayout.VERTICAL);
                        }
                        //子View
                        {
                            //title
                            {
                                tv_title = new TextView(context);
                                ll_title.addView(tv_title);
                                //样式
                                {
                                    tv_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                    tv_title.setTextColor(Color.parseColor("#333333"));
                                    tv_title.setGravity(Gravity.CENTER);
                                    tv_title.setTextSize(15);
                                    tv_title.setPadding(dp2px(context, 24), dp2px(context, 32), dp2px(context, 24), dp2px(context, 32));
                                    tv_title.setSingleLine();
                                    tv_title.setEllipsize(TextUtils.TruncateAt.END);
                                    tv_title.setText("标题");
                                }
                            }
                            //虚线
                            {
                                View v_line = new View(context);
                                ll_title.addView(v_line);
                                //样式
                                {
                                    v_line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
                                    v_line.setBackgroundColor(Color.parseColor("#EFEFEF"));
                                }
                            }
                        }
                    }
                    //列表
                    {
                        lv_main = new ListView(context);
                        ll_root.addView(lv_main);
                        //样式
                        {
                            lv_main.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            lv_main.setScrollbarFadingEnabled(false);
                            lv_main.setFastScrollEnabled(false);
                            lv_main.setHorizontalScrollBarEnabled(false);
                            lv_main.setVerticalScrollBarEnabled(false);
                            lv_main.setScrollingCacheEnabled(false);
                            lv_main.setSelector(new ColorDrawable(Color.parseColor("#00000000")));
                            lv_main.setFooterDividersEnabled(false);
                            lv_main.setHeaderDividersEnabled(false);
                            lv_main.setDivider(new ColorDrawable(Color.parseColor("#EFEFEF")));
                            lv_main.setDividerHeight(1);
                        }
                    }
                }
            }
            //配置
            {
                setContentView(ll_root);
                setCancelable(true);
                //高度
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                //居中
                lp.gravity = Gravity.CENTER;
                // 设置背景层透明度
                lp.dimAmount = 0.4f;
                //设置dialog的宽度和高度
                WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                if (windowManager != null) {
                    Display display = windowManager.getDefaultDisplay();
                    lp.width = (int) (display.getWidth() * 0.75);
                }
                getWindow().setAttributes(lp);
            }
            //配置View
            {
                //title
                {
                    if (builder.title != null) {
                        ll_title.setVisibility(View.VISIBLE);
                        tv_title.setText(builder.title);
                    } else {
                        ll_title.setVisibility(View.GONE);
                    }
                }
                //ListView
                {
                    lv_main.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return builder.holderList.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return null;
                        }

                        @Override
                        public long getItemId(int position) {
                            return 0;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            if (convertView == null) {
                                TextView tv_item = new TextView(context);
                                convertView = tv_item;
                                tv_item.setTextSize(15);
                                tv_item.setTextColor(Color.parseColor("#FF7206"));
                                tv_item.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                tv_item.setPadding(dp2px(context, 10), dp2px(context, 12), dp2px(context, 10), dp2px(context, 12));
                                tv_item.setGravity(Gravity.CENTER);

                            }
                            TextView tv_item = (TextView) convertView;
                            tv_item.setText(builder.holderList.get(position));
                            //设置背景
                            if (builder.holderList.size() == 1) {
                                if (builder.title == null) {
                                    tv_item.setBackgroundResource(R.drawable.selector_item_white_bg);
                                } else {
                                    tv_item.setBackgroundResource(R.drawable.selector_item_white_bottom_bg);
                                }
                            } else {
                                if (position == builder.holderList.size() - 1) {
                                    tv_item.setBackgroundResource(R.drawable.selector_item_white_bottom_bg);
                                } else if (position == 0 && builder.title == null) {
                                    tv_item.setBackgroundResource(R.drawable.selector_item_white_top_bg);
                                } else {
                                    tv_item.setBackgroundResource(R.drawable.selector_item_white_rectangular_bg);
                                }
                            }
                            return tv_item;
                        }
                    });
                    //设置监听器
                    lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ListDialog.this.dismiss();
                            if (builder.listener != null) {
                                builder.listener.click(position);
                            }
                        }
                    });
                }
                //设置取消监听器
                {
                    setOnCancelListener(new OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            ListDialog.this.dismiss();
                            if (builder.listener != null) {
                                builder.listener.cancel();
                            }
                        }
                    });
                }
            }
            //设置点击外部是否可取消
            {
                if (builder.outsideCancelable != null) {
                    setCanceledOnTouchOutside(builder.outsideCancelable);
                }
            }
        }
    }

    */
/**
     * dp转 px.
     *
     * @param value the value
     * @return the int
     *//*

    private static int dp2px(Context context, float value) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }

}
*/
