package com.example.linemirror;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import static com.example.linemirror.Fragment2.allStrokes;

public class MyCanvas2 extends View {

    Paint mPaint;
    Path mPath;

    public MyCanvas2(Context context) {
        super(context);

        init(null);
    }

    public MyCanvas2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyCanvas2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MyCanvas2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public void init(@Nullable AttributeSet set){
        mPaint=new Paint();
        mPath=new Path();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (stroke s : allStrokes) {
            canvas.drawPath(s.path, s.paint);
        }
    }





}
