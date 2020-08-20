package com.example.linemirror;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Fragment1.FragmentAListener,Fragment2.FragmentBListener {

    Fragment1 fragment1;
    Fragment2 fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1=new Fragment1();
        fragment2=new Fragment2();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container1, fragment1)
                .add(R.id.fragment_container2, fragment2).commit();

    }

    @Override
    public void onInputASent(Path path,int color) {
        fragment2.updateEditText(path,color);
    }

    @Override
    public void onInputBSent(Path path) {
        fragment1.updateEditText(path);
    }

}