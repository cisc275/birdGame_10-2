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

    private int xLocation;
    private int yLocation;
    private int xincr;
    private int yincr;
    private int width;
    private int height;
    private String type;

    /**
     * the move() method will call upon the methods in the Model class to update 
     * the object's location and direction.
     */

    public void move() {
    	xLocation = xLocation - xincr;
    	yLocation = yLocation - yincr;
    	
    }
    public int getX(){
        return xLocation;
    }
    
    public int getY(){
        return yLocation;
    }
    
    public int getXIncr() {
    	return xincr;
    }
    
    public int getYIncr() {
    	return yincr;
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
    
    public String getType() {
    	return type;
    }
    
    public void setX( int x){
        xLocation = x;
    }
    
    public void setY(int y){
        yLocation = y;
    }
    
    public void setXIncr(int x) {
    	xincr = x;
    }
    
    public void setYIncr( int y) {
    	yincr = y;
    }
    
    public void setWidth(int w) {
    	width = w;
    }
    
    public void setHeight(int h) {
    	height = h;
    }
    
    public void setType(String s) {
    	type = s;
    }
    
    
    
}
