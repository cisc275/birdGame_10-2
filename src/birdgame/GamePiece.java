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
    private Type type;
    private int xOffset;
    private int yOffset;
    private int picNum;

    /**
     * the move() method will call upon the methods in the Model class to update 
     * the object's location and direction.
     */
    
    public String toString(){
    	return " " + type;
    }
    
    public void move() {
    	xLocation = xLocation - xincr;
    	yLocation = yLocation - yincr;
    	
    }
    
    public void setPicNum(int newNum){
        picNum = newNum;
    }
    public int getPicNum(){
        return picNum;
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
    
    public Type getType() {
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
    
    public void setType(Type s) {
    	type = s;
    }
    public boolean isEnemy() {
    	if (type.equals(Type.PLANE) || type.equals(Type.EAGLE) || type.equals(Type.REDFOX) || type.equals(Type.RACCOON)){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public boolean isFood() {
    	return !(isEnemy());
    }
    
    public void setOffsets() {
		if (getType().equals(Type.REDFOX)) {
			xOffset = 50;
			yOffset = 50;
		}
		if (getType().equals(Type.EAGLE)) {
			xOffset = 30;
			yOffset = 30;
		}
		if(getType().equals(Type.PLANE)) {
			xOffset = 30;
			yOffset = 30;
		}
		if (getType().equals(Type.BUNNY)) {
			xOffset = 10;
			yOffset = 10;
		}
		if (getType().equals(Type.MOUSE)) {
			xOffset = 10;
			yOffset = 10;
		}
		if (getType().equals(Type.FISH)) {
			xOffset = 30;
			yOffset = 30;
		}
		if (getType().equals(Type.RACCOON)) {
			xOffset = 30;
			yOffset = 30;
		}
		if (getType().equals(Type.SNAKE)) {
			xOffset = 30;
			yOffset = 30;
		}
		
	}
    
    public int getXOffset() {
    	return xOffset;
    }
    public int getYOffset() {
    	return yOffset;
    }
}
