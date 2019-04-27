/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

/**
 * Food inherits from GamePiece class; contains int foodValue that has a value
 * for the score increment that a particular food may have.
 *
 * @author crnis
 */
public class Food extends GamePiece {

    int foodValue;

    public Food(int x, int y, int foodType) {
        xLocation = x;
        yLocation = y;
        xincr = 25;
        yincr = 0;
        width = 165;
        height = 165;
        type = foodType; // 0 = mice; 
        foodValue = 10;
    }
}
