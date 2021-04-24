package com.dangthuy.trolybabau.common.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;

public class DetailLineChart extends LineChart {

    protected Paint mYAxisSafeZonePaint;
    final float INTERVAL = 2f;
    String[] arrayColors = {"#99EC2436", "#99FEBF54", "#992FB758", "#99277BB7", "#99E8E8E8"};

    public DetailLineChart(Context context) {
        super(context);
    }

    public DetailLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DetailLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        mYAxisSafeZonePaint = new Paint();
        mYAxisSafeZonePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float[] pts = new float[4];
        pts[1] = 12f;
        float tempPts;
        for (int i = 0; i < 6; i++) {
            pts[3] = pts[1] - INTERVAL;
            tempPts = pts[3];
            mLeftAxisTransformer.pointValuesToPixel(pts);
            mYAxisSafeZonePaint.setColor(Color.parseColor(i % 2 == 0 ? arrayColors[0] : arrayColors[1]));
//            canvas.drawRect(mViewPortHandler.contentLeft(), pts[1], mViewPortHandler.contentRight(), pts[3], mYAxisSafeZonePaint);
            canvas.drawRect(pts[1], mViewPortHandler.contentTop(), pts[3], mViewPortHandler.contentBottom(), mYAxisSafeZonePaint);
            pts[1] = tempPts;
        }

        super.onDraw(canvas);
    }

    public void setSafeZoneColor(int color) {
        mYAxisSafeZonePaint.setColor(color);
    }
}