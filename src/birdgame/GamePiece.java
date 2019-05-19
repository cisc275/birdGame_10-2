/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package birdgame;

import java.io.Serializable;

/**
 * GamePiece contains various attributes of a GamePiece, including x-location,
 * y-location, x and y increment, width, height, and type; in addition, the 
 * GamePiece class contains a move() method that defines how each GamePiece moves
 * on the screen
 * @author crnis
 */
public class GamePiece implements Serializable{
	
    private int xLocation;
    private int yLocation;
    private int xincr;
    private int yincr;
    private int width;
    private int height;
    private Sprite sprite;
    private int xOffset;
    private int yOffset;
    private int picNum;
    private boolean isSpecialFood;
    private int imgCount = 0;

    /**
     * the move() method will call upon the methods in the Model class to update 
     * the object's location and direction.
     */
    
    public String toString(){
    	return " " + sprite + " "+ xLocation + ", " + yLocation;
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
    	return sprite;
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
    	sprite = s;
    }
    public boolean isEnemy() {
    	if (sprite.equals(Sprite.PLANE) || sprite.equals(Sprite.EAGLE) || sprite.equals(Sprite.REDFOX) || sprite.equals(Sprite.RACCOON)){
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
			xOffset = 50;
			yOffset = 50;
		}
		if (getSprite().equals(Sprite.EAGLE)) {
			xOffset = 30;
			yOffset = 30;
		}
		if(getSprite().equals(Sprite.PLANE)) {
			xOffset = 40;
			yOffset = 20;
		}
		if (getSprite().equals(Sprite.BUNNY)) {
			xOffset = 20;
			yOffset = 20;
		}
		if (getSprite().equals(Sprite.MOUSE)) {
			xOffset = 10;
			yOffset = 5;
		}
		if (getSprite().equals(Sprite.FISH)) {
			xOffset = 10;
			yOffset = 0;
		}
		if (getSprite().equals(Sprite.RACCOON)) {
			xOffset = 30;
			yOffset = 30;
		}
		if (getSprite().equals(Sprite.SNAKE)) {
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
    public boolean isSpecialFood() {
		return isSpecialFood;
	}
    public void setSpecialFood(boolean bool) {
		isSpecialFood = bool;
	}
    public void incrImgCount() {
    	imgCount++;
    }
    public int getImgCount() {
    	return imgCount;
    }
}