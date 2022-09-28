package com.example.fifteensquares;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;


public class SquareView extends SurfaceView implements View.OnTouchListener {
    private Square square;
    private SquareBoard board;
    private float screenHeight;
    private float screenWidth;

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //get screen dimensions
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;


        setWillNotDraw(false);

        this.square = new Square(2, 50, 50);
    }

    protected void onDraw(Canvas canvas) {
        square.draw(canvas);
        invalidate();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        square.setCoord(x,y);
        invalidate();

        return false;
    }
}
