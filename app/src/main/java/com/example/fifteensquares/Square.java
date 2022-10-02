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
    private boolean isSelected = false;

    /* Constants */
    public float SQ_LENGTH; //length of a given square on the board
    private float TXT_SIZE;
    private final Paint SQ_COLOR = new Paint();
    private final Paint SQ_SELECTED_COLOR = new Paint();
    private Rect squareBounds;
    private boolean dummy;
    public Square(int initNum, /*float initXPos, float initYPos,*/ float screenWidth, float screenHeight) {
        //Square parameters
        this.number = initNum;
        this.xPos = 0; //set later
        this.yPos = 0;
        this.SQ_LENGTH = screenWidth/4;
        this.TXT_SIZE = screenWidth/8;
        this.squareBounds = null; //set later
        this.dummy = true;

        //Colors
        this.SQ_COLOR.setColor(Color.YELLOW);
        this.SQ_SELECTED_COLOR.setColor(Color.BLUE);
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

    public void draw(Canvas canvas, float x, float y) {
        //source:
        //https://stackoverflow.com/questions/15609426/draw-text-inside-a-filled-rectangle-using-canvas-android


            float textWidth = this.text.measureText(Integer.toString(this.number));
            float textSize = this.text.getTextSize();
            this.text.setTextAlign(Paint.Align.CENTER);
            //NEW CODE
            this.squareBounds = new Rect((int) x, (int) y, (int) (x + SQ_LENGTH), (int) (y + SQ_LENGTH));


            //Rect rect = new Rect((int) (xPos), (int) (yPos), (int) (xPos + SQ_LENGTH), (int) (yPos + SQ_LENGTH));
            // Rect rect = new Rect((int) (SQ_LENGTH - textWidth), (int) (SQ_LENGTH - textSize), (int) (SQ_LENGTH + textWidth), (int) SQ_LENGTH);
        if (dummy){
            if (isSelected) {
                canvas.drawRect(squareBounds, SQ_SELECTED_COLOR);
            } else {
                canvas.drawRect(squareBounds, SQ_COLOR);
            }
            canvas.drawText(Integer.toString(this.number), squareBounds.centerX(), squareBounds.centerY() + TXT_SIZE / 3, this.text);
        }

        //canvas.drawRect(xPos, yPos, xPos + SQ_LENGTH, yPos + SQ_LENGTH, SQ_COLOR);
    }
    public void setCoord(float x, float y){
        this.xPos = x;
        this.yPos = y;
    }
    public void setNumber(int num)
    {
        this.number = num;
    }

    public int getNumber(){
        return this.number;
    }

    public void setSelected()
    {
        this.isSelected = !this.isSelected;
    }

    public float getxPos(){
        return this.xPos;
    }

    public float getyPos(){
        return this.yPos;
    }
    public boolean checkBounds(float x, float y){
        return squareBounds.contains((int) x, (int) y);
    }
    public void setDummy(){
        this.dummy = false;
    }
}
