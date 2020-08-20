package com.example.linemirror;

import android.graphics.Paint;
import android.graphics.Path;


    public class stroke {
        Path path;
        Paint paint;


        stroke(int color) {
            path = new Path();

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(10);
            paint.setColor(color);
            paint.setStyle(Paint.Style.STROKE);
        }
    }
