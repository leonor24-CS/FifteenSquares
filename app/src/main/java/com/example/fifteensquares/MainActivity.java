package com.example.fifteensquares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SquareView mainView = findViewById(R.id.squareView);

        mainView.setOnTouchListener(mainView);

        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(mainView);

        TextView gameText = findViewById(R.id.textView);

    }
}