package com.example.linemirror;

import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private static final String TAG = "Fragment2";

    MyCanvas2 myCanvas2;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.canvas_2,container,false);
        myCanvas2=view.findViewById(R.id.canvas2);

        return view;
    }
    public void updateEditText(Path path) {
        myCanvas2.draw(path);
    }



}
