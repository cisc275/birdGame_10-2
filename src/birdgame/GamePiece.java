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
    private Sprite type;
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
    
    public Sprite getSprite() {
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
    
    public void setSprite(Sprite s) {
    	type = s;
    }
    public boolean isEnemy() {
    	if (type.equals(Sprite.PLANE) || type.equals(Sprite.EAGLE) || type.equals(Sprite.REDFOX) || type.equals(Sprite.RACCOON)){
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
		if (getSprite().equals(Sprite.REDFOX)) {
			//TODO	
		}
		if (getSprite().equals(Sprite.EAGLE)) {
			//TODO
		}
		if(getSprite().equals(Sprite.PLANE)) {
			//TODO
		}
		if (getSprite().equals(Sprite.BUNNY)) {
			//TODO
		}
		if (getSprite().equals(Sprite.MOUSE)) {
			//TODO
		}
		if (getSprite().equals(Sprite.FISH)) {
			//TODO
		}
		if (getSprite().equals(Sprite.RACCOON)) {
			//TODO
		}
		if (getSprite().equals(Sprite.SNAKE)) {
			//TODO
		}
		
	}
    
    public int getXOffset() {
    	return xOffset;
    }
    public int getYOffset() {
    	return yOffset;
    }
}
