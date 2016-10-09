package com.example.sse.interfragmentcommratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ShiftFragment.ShiftFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void shiftImage(int d) {
        DrawableFragment receivingFragment = (DrawableFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        receivingFragment.changePicture(d);
    }

}
