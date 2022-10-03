package com.example.fifteensquares;
/** SquareBoard
 *
 *  defines the board in an array list named squares that dynamically changes based on the current
 *  state of the game
 *
 *  handles all the bounds checking and win conditions
 *
 */

import android.graphics.Canvas;
import android.location.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class SquareBoard {
    /* Instance Variables */
    private float screenHeight;
    private float screenWidth;
    /* Arrays */
    private ArrayList<Square> squares = new ArrayList(); //the current game state
    private ArrayList<Square> winningSquare = new ArrayList(); //the winning game state


    /* SquareBoard Constructor

       initializes all the variables and array lists

       parameter screenHeight the height of the screen
       parameter screenWidth the width of the screen

     */
    public SquareBoard(float screenHeight, float screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

        //initialize the first 15 squares on the board
        for (int i = 0; i < 15; i++) {
            squares.add(new Square(i + 1, screenWidth, screenHeight));
        }

        //add a "dummy" square to act as our blank space
        squares.add(new Square(0, screenWidth, screenHeight)); //dummy square will have the value of zero
        squares.get(squares.size() - 1).setDummy(); //set the square to the dummy square

        //currently the board is in order (its in the win condition state)
        //so copy the current state into another arraylist to represent the win condition state
        for (int j = 0; j < squares.size(); j++) {
            winningSquare.add(new Square(squares.get(j))); //use the copy constructor in the Square class
        }

        //once done copying the win state, shuffle the current game state
        Collections.shuffle(squares);
        checkCorrectSpot();
        if(checkWinCond())
        {
            setWinCond();
        }


    }

    /* drawBoard

       draws the 4x4 board using the current state of the game
     */
    public void drawBoard(Canvas canvas) {

        /* calculating the initial xPos and yPos of the board:
         *
         * to calculate xPos, mod index i by 4 to find the squares position relative to screenwidth
         * j is used to move on to the next row in the board
         */

        int j = 0; // "row" variable

        for (int i = 0; i <= 15; i++) {
            //draw the given square object at the right position on the board
            squares.get(i).draw(canvas, (float) ((i % 4)) / 4 * screenWidth, (float) j / 4 * screenWidth);
            //set the current coordinates of the given square
            squares.get(i).setCoord((float) ((i % 4)) / 4 * screenWidth, (float) j / 4 * screenWidth);
            //once you get to the end of the row, iterate to the next row
            if ((i % 4 == 3) && (i != 0)) {
                j++;
            }
        }
    }

    /* shuffle

       shuffles and resets the game state

     */
    public void shuffle() {
        for (Square square : squares) {
            square.setWin(false); //if the game is won, reset the colors of the squares
            square.setCorrect(false); //reset the value of isCorrect
        }
        Collections.shuffle(this.squares); //shuffle the board
        checkCorrectSpot();
    }

    /* findDummy

        find the dummy square on the board

     */
    public Square findDummy() {
        for (Square square : squares) {
            if (!square.getDummy()) {
                return square;
            }
        }
        return null;
    }

    /* findSelectSquare

       find the square that has been touched

     */
    public Square findSelectSquare(float x, float y) {
        for (Square square : squares) {
            if (square.checkBounds(x, y)) {
                return square;
            }
        }
        return null;
    }

    /* checkSelection

       check whether the selected square is in a cardinal direction from the dummy square

       parameter dummy the dummy square
       parameter toCheck the selected square to check

     */
    public boolean checkSelection(Square dummy, Square toCheck) {
        boolean isValid = false;
        int dummyPos = squares.indexOf(dummy);
        int checkPos = squares.indexOf(toCheck);

        //top side of the board
        if ((dummyPos >= 0) && (dummyPos < 3)) //top right corner includes the right side of the board
        {
            if ((checkPos == dummyPos + 1) || (checkPos == dummyPos - 1) || (checkPos == dummyPos + 4)) {
                isValid = true;
                return isValid;
            }
        }
        //left side of the board
        else if (dummyPos % 4 == 0) {
            if ((checkPos == dummyPos + 1) || (checkPos == dummyPos - 4) || (checkPos == dummyPos + 4)) {
                isValid = true;
                return isValid;
            }
        }
        //right side of the board
        else if (dummyPos % 4 == 3) {
            if ((checkPos == dummyPos - 1) || (checkPos == dummyPos - 4) || (checkPos == dummyPos + 4)) {
                isValid = true;
                return isValid;
            }
        }
        //bottom of the board
        else if ((dummyPos >= 12) && (dummyPos <= 15)) {
            if ((checkPos == dummyPos + 1) || (checkPos == dummyPos - 1) || (checkPos == dummyPos - 4)) {
                isValid = true;
                return isValid;
            }
        } else //middle of the board
        {
            if ((checkPos == dummyPos + 1) || (checkPos == dummyPos - 1) || (checkPos == dummyPos + 4) || (checkPos == dummyPos - 4)) {
                isValid = true;
                return isValid;
            }
        }

        //if the square is not a valid move then it is false

        return isValid;
    }

    /* swapSquares

       swap the dummy square with a selected square

       parameter x the x position of the selected square
       parameter y the y position of the selected square

     */
    public void swapSquares(float x, float y) {
        Square selectedSquare = findSelectSquare(x, y); //find the selected square on the board
        Square dummySquare = findDummy(); //find the dummy square on the board

        //check if the selected square is a valid move (the selected square is in the cardinal direction of the dummy)

        if (checkSelection(dummySquare, selectedSquare)) {
            //swap the two squares
            Collections.swap(squares, squares.indexOf(dummySquare), squares.indexOf(selectedSquare));
            checkCorrectSpot(); //check if the square is now in the correct spot
            if (checkWinCond()) //check whether the board is now in the win condition
            {
                setWinCond();
            }
        }
    }

    /* checkCorrectSpot

       check whether the square is in the correct position on the board

     */
    public void checkCorrectSpot() {
        for (int i = 0; i < winningSquare.size(); i++) {
            if (squares.get(i).getNumber() == winningSquare.get(i).getNumber()) //compare the current board state
            // to the winning state
            {
                squares.get(i).setCorrect(true);
            } else {
                squares.get(i).setCorrect(false);
            }
        }
    }

    /* checkWinCond

       checks whether the win state has been achieved on the board

     */
    public boolean checkWinCond() {
        for (int i = 0; i < squares.size(); i++) {
            if (!squares.get(i).getCorrect()) //checks whether all the squares are in the correct position
            {
                return false;
            }
        }
        return true;
    }

    public void setWinCond() {
        for (Square square: squares)
        {
            square.setWin(true);
        }
    }

}
