package com.example.fifteensquares;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;


public class SquareView extends SurfaceView implements View.OnTouchListener {
    Square[] squares = new Square[16];

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        squares[0] = new Square(0,30,30);
        squares[0].draw(canvas);
        invalidate();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        squares[0].setCoord(x,y);

        invalidate();

        return false;
    }
}
