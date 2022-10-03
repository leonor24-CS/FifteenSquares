package com.example.fifteensquares;
/** Square
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
    private Rect squareBounds;
    private boolean dummy;

    //conditions
    private boolean isSelected = false;
    private boolean isCorrect = false;
    private boolean isWin = false;

    //paints
    private Paint text = new Paint();
    private final Paint SQ_COLOR = new Paint();
    private final Paint SQ_SELECTED_COLOR = new Paint();
    private final Paint SQ_WIN_COLOR = new Paint();

    /* Constants */
    public float SQ_LENGTH; //length of a given square on the board
    private float TXT_SIZE;

    /* Square Constructor
       initializes instance variables

       parameters initialize the number, screen width and height
     */
    public Square(int initNum, float screenWidth, float screenHeight) {
        //Square parameters
        this.number = initNum;
        this.xPos = 0; //set these later because the looping is tricky
        this.yPos = 0;
        this.SQ_LENGTH = screenWidth/4;
        this.TXT_SIZE = screenWidth/8;
        this.squareBounds = null; //set this later
        this.dummy = true;

        //Colors
        this.SQ_COLOR.setColor(Color.YELLOW); //default color is yellow
        this.SQ_SELECTED_COLOR.setColor(Color.BLUE); //correct position is blue
        this.SQ_WIN_COLOR.setColor(Color.RED); //whole board will be red
        this.text.setColor(Color.BLACK); //text color is black

        //Text Sizes
        this.text.setTextSize(TXT_SIZE); //set text size
    }

    /* Square Constructor
       copies the Square object
     */
    public Square(Square toCopy)
    {
        this.number = toCopy.number;
        this.xPos = toCopy.xPos;
        this.yPos = toCopy.yPos;
        this.SQ_LENGTH = toCopy.SQ_LENGTH;
        this.TXT_SIZE = toCopy.TXT_SIZE;
        this.squareBounds = toCopy.squareBounds;

        this.SQ_COLOR.setColor(toCopy.SQ_COLOR.getColor());
        this.SQ_SELECTED_COLOR.setColor(toCopy.SQ_SELECTED_COLOR.getColor());
        this.text.setColor(toCopy.text.getColor());

        this.text.setTextSize(toCopy.TXT_SIZE);
    }

    /* draw
       initialize a given square as a Rect object, then draw it on a given position in the board
       some code was inspired by the thread linked on stackoverflow below

       parameter canvas the canvas to draw on the surface view
       parameter x the x position of a given square on the canvas
       parameter y the y position of a given square on the canvas
     */
    public void draw(Canvas canvas, float x, float y) {
        //source:
        //https://stackoverflow.com/questions/15609426/draw-text-inside-a-filled-rectangle-using-canvas-android

            //align the text to the center of the square
            this.text.setTextAlign(Paint.Align.CENTER);

            //initialize the square as a Rect object
            this.squareBounds = new Rect((int) x, (int) y, (int) (x + SQ_LENGTH), (int) (y + SQ_LENGTH));

        if (dummy){ //check to see if the given Square object is the dummy square
            if (isCorrect) { //check to see if the given Square object is in the correct position on the board and recolor it
                canvas.drawRect(squareBounds, SQ_SELECTED_COLOR);
                if(isWin) //check to see if the board is in the win state and recolor it
                {
                    canvas.drawRect(squareBounds, SQ_WIN_COLOR);
                }

            } else { //default color
                canvas.drawRect(squareBounds, SQ_COLOR);
            }
            canvas.drawText(Integer.toString(this.number), squareBounds.centerX(), squareBounds.centerY() + TXT_SIZE / 3, this.text);
        }
    }

    /* checkBounds
        check whether a click was within the bounds of a given square

        parameter x the x position of the touch on the square view
        parameter y the y position of the touch on the square view
     */
    public boolean checkBounds(float x, float y){
        return squareBounds.contains((int) x, (int) y);
    }




    /* SETTERS AND GETTERS */

    /* Setters */
    public void setCoord(float x, float y){
        this.xPos = x;
        this.yPos = y;
    }
    public void setNumber(int num) {this.number = num;}

    public void setSelected() {this.isSelected = !this.isSelected;}

    public void setDummy(){this.dummy = false;}

    public void setCorrect(boolean newCorrect) {this.isCorrect = newCorrect;}

    public void setWin(boolean win) {this.isWin = win;}

    /* Getters */
    public int getNumber(){return this.number;}

    public boolean getDummy(){return this.dummy;}

    public boolean getCorrect(){return this.isCorrect;}

    public boolean getSelected() {return this.isSelected;}

    public float getxPos(){return this.xPos;}

    public float getyPos(){return this.yPos;}






}
