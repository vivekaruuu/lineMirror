package com.example.linemirror;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MyCanvas extends View {
    private static final String TAG = "MyCanvas";
        Paint mPaint;
        static List<stroke> allStrokes = new ArrayList<stroke>();


    public MyCanvas(Context context) {
            super(context);

            init(null);
        }

        public MyCanvas(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init(attrs);
        }

        public MyCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(attrs);
        }

        public MyCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            init(attrs);
        }

        public void init(@Nullable AttributeSet set) {
            mPaint =new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(5);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);

            allStrokes.add(new stroke(Color.BLACK));
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
             {
                canvas.drawLine(0, getHeight(), getWidth(), getHeight(), mPaint);
                 for (stroke s : allStrokes) {
                     canvas.drawPath(s.path, s.paint);
                 }
             }
        }


}


