package com.example.linemirror;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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

import yuku.ambilwarna.AmbilWarnaDialog;


public class Fragment1 extends Fragment {

    private static final String TAG = "Fragment1";
    private FragmentAListener listener;
    public interface FragmentAListener {
        void onInputASent(Path path,int color);
    }
    MyCanvas myCanvas;
    Button button;
    Button colorButton;
    Button eraseButton;
    int mColor=Color.BLACK;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.canvas_1,container,false);
         myCanvas=view.findViewById(R.id.canvas);
         button=view.findViewById(R.id.button);
         colorButton=view.findViewById(R.id.color_button);
         eraseButton=view.findViewById(R.id.erase_button);
         eraseButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
             }
         });
         colorButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openColorPicker();
             }
         });
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 myCanvas.mPath.reset();
                 myCanvas.invalidate();
                 listener.onInputASent(myCanvas.mPath,mColor);
             }
         });

        touchListener(myCanvas);

        return view;
    }
    void openColorPicker(){
        AmbilWarnaDialog colorPicker=new AmbilWarnaDialog(getActivity(), Color.BLACK, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                    mColor=color;
                    myCanvas.mPaint.setColor(mColor);
                    myCanvas.invalidate();
                    listener.onInputASent(myCanvas.mPath,mColor);
                    colorButton.setBackgroundColor(mColor);
            }
        });
        colorPicker.show();
    }

    public void updateEditText(Path path) {
        myCanvas.draw(path);
    }
    
    private void touchListener(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                            myCanvas.mPath.moveTo(x, y);
                          return true;
                    }
                    case MotionEvent.ACTION_MOVE:{
                        myCanvas.mPath.lineTo(x,y);
                        listener.onInputASent(myCanvas.mPath,mColor);
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
