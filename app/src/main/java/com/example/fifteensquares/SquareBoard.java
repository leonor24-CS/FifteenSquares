package com.example.fifteensquares;

import android.graphics.Canvas;
import android.location.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SquareBoard {
    /* Instance Variables */

    private float screenHeight;
    private float screenWidth;
    private ArrayList<Square> squares = new ArrayList();

    public SquareBoard(float screenHeight, float screenWidth){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

        /* calculating the initial xPos and yPos of the board:
         *
         * initially, the board is organized sequentially
         * to calculate xPos, mod index i by 4 to find the squares position relative to screenwidth
         * j is used to move on to the next row in the board
         */
        int j = 0; //initialize "row" variable
        for(int i = 0; i < 15; i++) {
            squares.add(new Square(i+1, /*(float) ((i % 4)) / 4 * screenWidth, (float) j/4 * screenWidth ,*/ screenWidth, screenHeight));
            /*if((i % 4 == 3) && (i != 0)) { //check to see if a given row is finished being created
                j++; //move onto the next row
            }*/

        }
        squares.add(new Square(0, screenWidth, screenHeight)); //add a null object to represent a blank square
        squares.get(squares.size()-1).setDummy();

        Collections.shuffle(squares);


/*
        for (int j = 1; j < 16 ; j++) {
            squares.get(j-1).setCoord((j%4 - 1)/4 * screenWidth, (j%4 - 1)/4 * screenHeight );
        }

 */
    }

    public void drawBoard(Canvas canvas){
        int j = 0;

        for (int i = 0; i <= 15; i++)
        {


            if (squares.get(i) == null){
                //check to see if null object is at the end of a row
                if((i % 4 == 3) && (i != 0)) {
                    j++;
                }
                continue;
            }
            squares.get(i).draw(canvas,(float) ((i%4)) / 4 * screenWidth, (float) j/4 * screenWidth);
            squares.get(i).setCoord((float) ((i%4)) / 4 * screenWidth, (float) j/4 * screenWidth);
            if((i % 4 == 3) && (i != 0)) {
                j++;
            }
        }

    }

    //used to debug
    public void shuffle(){
        Collections.shuffle(this.squares);
        for (Square square: squares) {
            if (square == null) {
                System.out.print("null, ");
                continue;
            }
            System.out.print(square.getNumber() + ", ");
        }

    }
    public void checkBounds(float x, float y){
        for(Square square: squares)
        {
            if (square == null){
                continue;
            }
            if (square.checkBounds(x, y))
            {
                square.setSelected();
            }
        }
    }
    public float calcDistance(float x1, float x2, float y1, float y2)
    {

        float diff1 = (x2-x1) * (x2-x1);
        float diff2 = (y2-y1) * (y2-y1);

        return (float) Math.sqrt(diff1 + diff2);
    }
    /*
    public void checkBounds(float x, float y)
    {
        float dist1;
        float dist2;
        for (Square square: squares) {
            if (square == null){
                continue;
            }

            dist1 = calcDistance(square.getxPos(), x, square.getyPos(), y);
            dist2 = calcDistance(square.getxPos(), square.getxPos() + square.SQ_LENGTH,
                    square.getyPos(), square.getyPos() + square.SQ_LENGTH);
            if (dist1 < dist2/Math.sqrt(2)){
                square.setSelected();
                break;
            }
        }
    }

     */
    /*
    public void checkBounds(float x, float y)
    {
        float[] dist1 = new float[1];
        float[] dist2 = new float[1];
        for(Square square: squares) {
            if (square == null){
                continue;
            }
            Location.distanceBetween(square.getyPos(), square.getxPos(), y, x, dist1);
            Location.distanceBetween(square.getyPos(), square.getxPos(),
                    square.getyPos() + square.SQ_LENGTH, square.getxPos() + square.SQ_LENGTH, dist2);
            if(dist1[0] < dist2[0]){
                square.setSelected();
            }
        }
    }

     */
}
