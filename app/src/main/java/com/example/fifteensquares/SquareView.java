package com.example.fifteensquares;
/**
 * SquareView
 *
 *
 */

import android.content.Context;
import android.graphics.Canvas;
import android.location.Location;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;


public class SquareView extends SurfaceView implements View.OnTouchListener, View.OnClickListener {
    private Square square;
    private SquareBoard board;
    private float screenHeight;
    private float screenWidth;

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //get screen dimensions
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;


        setWillNotDraw(false); //important line to let the canvas be drawn on

        this.board = new SquareBoard(screenHeight, screenWidth); //initialize a new board variable

    }

    protected void onDraw(Canvas canvas) {
        //square.draw(canvas);
        board.drawBoard(canvas);
        invalidate();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        board.swapSquares(x, y);
        //square.setCoord(x,y);
        invalidate();

        return false;
    }

    @Override
    public void onClick(View v) {
        board.shuffle();
    }


}
