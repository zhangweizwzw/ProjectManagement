package com.bj.yatu.projectmanagement.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by wxixis on 2017/4/6.
 * 自定义ProgressBar显示百分比
 */

public class MyProgress extends ProgressBar{
    String text;
    Paint mPaint;


    public MyProgress(Context context) {
        super(context);
        initText();
    }

    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initText();
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initText();
    }

//    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
    /**
     * 初始化画笔
     */
    private void initText() {
        this.mPaint=new Paint();
        this.mPaint.setColor(Color.BLACK);
//        this.mPaint.setTextSize(15);
    }

    @Override
    public synchronized void setProgress(int progress) {
        setText(progress);
        super.setProgress(progress);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
        this.mPaint.setTextSize(30);
        int x = (getWidth() / 2) - rect.centerX();
        int y = (getHeight() / 2) - rect.centerY();
        canvas.drawText(this.text, x, y, this.mPaint);
    }

    private void setText() {
        setText(this.getProgress());
    }

    /**
     * 设置文字内容
     * @param progress
     */
    private void setText(int progress){
        int i = (progress * 100)/this.getMax();
        this.text = String.valueOf(i) + "%";
    }
}
