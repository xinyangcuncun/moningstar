package com.example.administrator.morningstar.view.wight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.administrator.morningstar.R;

/**
 * Created by anson on 2017/8/13.
 */

public class LoadingView extends TextView {

    private BitmapShader bitmapShader;
    private int shadeX;
    private int shadeY;
    private Matrix matrix;
    private int intrinsicHeight;
    private int h;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //设置字体颜色
        this.setTextColor(Color.RED);
        //设置为美术字体:美工会提供相应的ttf文件 并放入assets目录下面
        Typeface fromAsset = Typeface.createFromAsset(getResources().getAssets(), "Satisfy-Regular.ttf");
        setTypeface(fromAsset);
        //初始化一个矩阵,用于让着色器不断的向下和向右移动
        matrix = new Matrix();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取当前控件的高度
        this.h = h;
        //把图片画到bitmap里面
        Drawable wave = getResources().getDrawable(R.drawable.wave);
        int intrinsicWidth = wave.getIntrinsicWidth(); // 图片的原始宽度
        intrinsicHeight = wave.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        //画布和bitmap进行绑定
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(getCurrentTextColor());
        //把Drawable绘制出来必须要设置边界
        wave.setBounds(0,0,intrinsicWidth, intrinsicHeight);
        wave.draw(canvas);
        //准备一个着色器,把图片绘制到自定义控件上
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        getPaint().setShader(bitmapShader);
        //把着色器进行平移 为了使着色器和loading进行覆盖
        shadeX = 0;
        shadeY = -intrinsicHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shadeX += 3;
        shadeY += 0.3;
        if (shadeY > -intrinsicHeight / 2 + h) {
            shadeY = -intrinsicHeight / 2;
        }
        matrix.setTranslate(shadeX,shadeY);
        bitmapShader.setLocalMatrix(matrix);
        invalidate();
    }
}
