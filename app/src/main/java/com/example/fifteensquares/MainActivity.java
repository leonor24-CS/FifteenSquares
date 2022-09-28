package com.example.fifteensquares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SquareView mainView = findViewById(R.id.squareView);
        mainView.setOnTouchListener(mainView);

    }
}