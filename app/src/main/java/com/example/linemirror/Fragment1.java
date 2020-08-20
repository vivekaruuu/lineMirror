package com.example.linemirror;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment1 extends Fragment {

    private static final String TAG = "Fragment1";
    private FragmentAListener listener;
    public interface FragmentAListener {
        void onInputASent(Path path);
    }
    MyCanvas myCanvas;
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.canvas_1,container,false);
         myCanvas=view.findViewById(R.id.canvas);
         button=view.findViewById(R.id.button);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 myCanvas.mPath.reset();
                 myCanvas.invalidate();
                 listener.onInputASent(myCanvas.mPath);
             }
         });

        touchListener(myCanvas);

        return view;
    }
    
    private void touchListener(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        myCanvas.mPath.moveTo(x,y);
                        return true;
                    }
                    case MotionEvent.ACTION_MOVE:{
                        myCanvas.mPath.lineTo(x,y);
                        listener.onInputASent(myCanvas.mPath);
                        break;
                    }


                }
                myCanvas.invalidate();
                return true;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }


}
