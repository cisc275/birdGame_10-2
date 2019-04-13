/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

/**
 * GamePiece contains various attributes of a GamePiece, including x-location,
 * y-location, x and y increment, width, height, and type; in addition, the 
 * GamePiece class contains a move() method that defines how each GamePiece moves
 * on the screen
 * @author crnis
 */
public class GamePiece {

    int xLocation;
    int yLocation;
    int xincr;
    int yincr;
    int width;
    int height;
    String type;

    /**
     * the move() method will call upon the methods in the Model class to update 
     * the object's location and direction.
     */

    public void move() {
    	xLocation = xLocation + xincr;
    	yLocation = yLocation + yincr;
    }
    public int getX(){
        return xLocation;
    }
    
    public int getY(){
        return yLocation;
    }
    
}
