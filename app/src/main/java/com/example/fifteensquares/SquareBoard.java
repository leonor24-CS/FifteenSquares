package com.example.fifteensquares;

import java.util.ArrayList;

public class SquareBoard {
    /* Instance Variables */

    private float screenHeight;
    private float screenWidth;
    private ArrayList<Square> squares = new ArrayList<Square>;


    public SquareBoard(float screenHeight, float screenWidth){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

        for (int i = 1; i <= 15; i++)
        {
            squares.add(new Square(i, ))
        }
    }
}
