package com.example.fifteensquares;
/* Square
 *
 * defines a given square object on the board
 *
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class Square  {

    /* Instance Variables */

    private int number; //the corresponding number of a given square
    private float xPos; //current xPos
    private float yPos; //current yPos
    private Paint text = new Paint();

    /* Constants */
    private final float SQ_LENGTH = 450.0f; //length of a given square on the board
    private final float TXT_SIZE = 200.0f;
    private final Paint SQ_COLOR = new Paint();

    public Square(int initNum, float initXPos, float initYPos) {
        //Square parameters
        this.number = initNum;
        this.xPos = initXPos;
        this.yPos = initYPos;

        //Colors
        this.SQ_COLOR.setColor(Color.YELLOW);
        this.text.setColor(Color.BLACK);

        //Text Sizes
        this.text.setTextSize(TXT_SIZE);
    }
/*
    public Square(){
        this.number = 0;
        this.xPos = 0;
        this.yPos = 0;
        this.SQ_COLOR.setColor(Color.YELLOW);
    }
*/

    public void draw(Canvas canvas) {
        //source:
        //https://stackoverflow.com/questions/15609426/draw-text-inside-a-filled-rectangle-using-canvas-android


        float textWidth = this.text.measureText(Integer.toString(this.number));
        float textSize = this.text.getTextSize();
        this.text.setTextAlign(Paint.Align.CENTER);
        Rect rect = new Rect((int) (xPos), (int) (yPos), (int) (xPos + SQ_LENGTH), (int) (yPos + SQ_LENGTH));
        //Rect rect = new Rect((int) (SQ_LENGTH - textWidth), (int) (SQ_LENGTH - textSize), (int) (SQ_LENGTH + textWidth), (int) SQ_LENGTH);
        canvas.drawRect(rect, SQ_COLOR);
        canvas.drawText(Integer.toString(this.number), rect.centerX(), rect.centerY() + TXT_SIZE/3, this.text);


        //canvas.drawRect(xPos, yPos, xPos + SQ_LENGTH, yPos + SQ_LENGTH, SQ_COLOR);
    }
    public void setCoord(float x, float y){
        this.xPos = x;
        this.yPos = y;
    }
}
