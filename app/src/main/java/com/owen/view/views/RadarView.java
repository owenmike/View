package com.owen.view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 雷达图
 * Created by Owen on 2016/1/12.
 */
public class RadarView extends View {

    private Paint mPaint;
    private float mAngel = (float) (2 * Math.PI / 6);

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2F);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        canvas.translate(getWidth() / 2, getHeight() / 2);

        drawPolygon(canvas);
        drawLine(canvas);
    }

    /**
     * 绘制多边形
     */
    private void drawPolygon(Canvas canvas) {
        Path mPath = new Path();
        float r = 50F;

        for (int k = 0; k < 5; k++) {
            float radius = r * (1 + k);

            mPath.moveTo(radius, 0);
            for (int i = 1; i < 6; i++) {
                float x = (float) (Math.cos(mAngel * i) * radius);
                float y =(float) (Math.sin(mAngel * i) * radius);
                mPath.lineTo(x, y);
            }
            mPath.close();

            canvas.drawPath(mPath, mPaint);
        }
    }

    private void drawLine(Canvas canvas) {
        Path path = new Path();

        for (int i = 0; i < 6; i++) {
            path.reset();

            path.moveTo(0, 0);
            float x = (float) (Math.cos(mAngel * i) * 250);
            float y =(float) (Math.sin(mAngel * i) * 250);
            path.lineTo(x, y);

            canvas.drawPath(path, mPaint);
        }
    }

}