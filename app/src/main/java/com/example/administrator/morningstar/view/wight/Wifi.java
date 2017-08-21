package com.example.administrator.morningstar.view.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by anson on 2017/8/12.
 */

public class Wifi extends View {

    private Paint paint;
    private int wifiLenght;
    private float baseLength;

    /**
     * 通常来说,自定义控件要声明三个构造方法,第一个和第二个都指向第三个构造:这是通用写法,也是最方便的.
     *
     * @param context
     */
    //主要用于手动new出来,区别下面的两个构造,主要少了AttributeSet这个参数
    //AttributeSet这个参数是定义了一系列的参数,比如TextView的 text属性,当我们在外面new的时候,如果缺少了第一种的构造方法,要传入多个属性,这样是非常不方便的
    public Wifi(Context context) {
        this(context, null);
    }

    //主要用于在xml文件中, attrs定义了一系列的属性,在xml声明时,系统会自动传入相应的参数
    public Wifi(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //主要用于在xml文件中,defStyleAttr这个参数是 用于在xml中声明的 style属性
    public Wifi(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化操作只要在第三个构造中调用即可,因为第一个和第二个构造都默认走第三个构造:是不是很方便?
        initView();
    }

    //初始化操作
    private void initView() {
        //onDraw 方法会多次调用,在这里先把画笔new出来,减少性能开销
        paint = new Paint();
        //用于抗锯齿,也就是让画的轮廓更加清晰
        paint.setAntiAlias(true);
        //加粗效果
        paint.setStrokeWidth(6);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //重复绘制,为的是多次调用ondraw方法
                invalidate();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    //根据需求要画四个信号
    private float wifiSize = 4;
    //默认要画几次
    private float wifiDrawSize = 4;

    //handler
    private Handler handler = new Handler();


    //这个方法可以得到控件的 实际 宽度和 高度
    @Override

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //取宽度和高度的最小值来进行计算
        wifiLenght = Math.min(w, h);
    }

    //自定义View控件通常要复写OnDraw方法,用于画图像,重点就是这个Canvas参数,俗称画板
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        wifiDrawSize++;
        if (wifiDrawSize > wifiSize) {
            wifiDrawSize = 1;
        }
        //让画板平移,必须在前面调用save ,和 restore 函数
        canvas.save();
        canvas.translate(0, wifiLenght / wifiSize);
        RectF rectf;
        //因为每个信号的 相对距离是相等的,所以根据圆的图形计算,我们定义最基础圆的半径
        baseLength = wifiLenght / 2 / wifiSize;
        //根据需求,我们主要是画扇形和圆弧,扇形和圆弧的画法是一样的,只是圆弧会多调用一个api
        //drawArc 里面要传入一个RectF这个参数,实际上就是一个矩形,圆弧和扇形都是根据矩形来画的,都是矩形的内接结构
        for (int i = 0; i < wifiSize; i++) {
            if (i >= wifiSize - wifiDrawSize) {
                float nowWifiLeagth = baseLength * i; // 当前矩形及内接圆的半径
                //定义当前矩形的大小(这里涉及到一个自定义控件的知识点)
                //参数依次为左上右下,左侧就是距离左侧的左边距,右侧也是左边距 + 控件的宽度, 上是距离上边的边距,下是上边距加 + 控件高度
                rectf = new RectF(nowWifiLeagth, nowWifiLeagth, wifiLenght - nowWifiLeagth, wifiLenght - nowWifiLeagth);
                if (i < wifiSize -1 ) {
                    switch (i) {
                        case 1:
                            paint.setColor(Color.RED);
                            break;
                        case 2:
                            paint.setColor(Color.MAGENTA);
                            break;
                        case 3:
                            paint.setColor(Color.YELLOW);
                            break;
                    }
                    //画一个空心的
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawArc(rectf, -135, 90, false, paint);
                } else {
                    paint.setColor(Color.WHITE);
                    //画一个实心的
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawArc(rectf, -135, 90, true, paint);
                }
            }
        }
        canvas.restore();

    }

    public void OnDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
