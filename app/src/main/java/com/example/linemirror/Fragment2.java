package com.example.linemirror;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private static final String TAG = "Fragment2";
    private Fragment2.FragmentBListener listener;
    public interface FragmentBListener {
        void onInputBSent(Path path);
    }

    MyCanvas2 myCanvas2;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.canvas_2,container,false);
        myCanvas2=view.findViewById(R.id.canvas2);
        touchListener(myCanvas2);
        return view;
    }
    public void updateEditText(Path path,int color) {
        myCanvas2.draw(path);myCanvas2.mPaint.setColor(color);
    }

    private void touchListener(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        myCanvas2.mPath.moveTo(x,y);
                        return true;
                    }
                    case MotionEvent.ACTION_MOVE:{
                        myCanvas2.mPath.lineTo(x,y);
                        listener.onInputBSent(myCanvas2.mPath);
                        break;
                    }


                }
                myCanvas2.invalidate();
                return true;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Fragment1.FragmentAListener) {
            listener = (Fragment2.FragmentBListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }



}
