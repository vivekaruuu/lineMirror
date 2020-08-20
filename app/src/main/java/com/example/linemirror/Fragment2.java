package com.example.linemirror;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

import static com.example.linemirror.MyCanvas.allStrokes;

public class Fragment2 extends Fragment {
    private static final String TAG = "Fragment2";
    private Fragment2.FragmentBListener listener;
    static List<stroke> allStrokes = new ArrayList<stroke>();
    int mColor;
    public interface FragmentBListener {
        void onInputBSent(List<stroke> allStrokes);
    }

    MyCanvas2 myCanvas2;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.canvas_2,container,false);
        myCanvas2=view.findViewById(R.id.canvas2);
        touchListener(myCanvas2);
        return view;
    }
    public void updateEditText( List<stroke> allStrokes,int color) {
       this.allStrokes=allStrokes;
       mColor=color;
       myCanvas2.invalidate();
    }

    private void touchListener(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        allStrokes.add(new stroke(mColor));
                        if(mColor== Color.WHITE){
                            allStrokes.get(allStrokes.size() - 1).paint.setStrokeWidth(45);
                        }
                        allStrokes.get(allStrokes.size() - 1).path.moveTo(x,y);
                        return true;
                    }
                    case MotionEvent.ACTION_MOVE:{
                        allStrokes.get(allStrokes.size() - 1).path.lineTo(x,y);
                        listener.onInputBSent(allStrokes);
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
