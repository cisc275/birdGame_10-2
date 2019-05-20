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
 * GamePiece class contains a move() method that defines how each GamePiece
 * moves on the screen
 *
 * @author crnis
 */
public class GamePiece implements Serializable {

    final static int FOX_X_OFFSET = 20;//20
    final static int FOX_Y_OFFSET = 0;
    final static int EAGLE_X_OFFSET = 15;
    final static int EAGLE_Y_OFFSET = 20;//20
    final static int PLANE_X_OFFSET = 10;
    final static int PLANE_Y_OFFSET = 5; //20
    final static int BUNNY_X_OFFSET = 20;
    final static int BUNNY_Y_OFFSET = 15; //20
    final static int MOUSE_X_OFFSET = 15;
    final static int MOUSE_Y_OFFSET = -10;
    final static int FISH_X_OFFSET = 10;
    final static int FISH_Y_OFFSET = 0; //
    final static int RACCOON_X_OFFSET = 5;
    final static int RACCOON_Y_OFFSET = 20;
    final static int SNAKE_X_OFFSET = 20;
    final static int SNAKE_Y_OFFSET = 10;

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
     * the toString method is an override of Object's toString, and allows for easier printing of GamePieces during user tests. It returns the sprite name and location
     * 
     * @return String (" " + sprite + " " + xLocation + ", " + yLocation) is the Sprite name and x,y location of the GamePiece.
     */
    @Override
    public String toString() {
        return " " + sprite + " " + xLocation + ", " + yLocation;
    }

    /**
     * the move() method calls upon the methods in the Model class to update the object's location and direction.
     */
    public void move() {
        xLocation = xLocation - xincr;
        yLocation = yLocation - yincr;

    }

    /**
     * setPicNum takes one parameter and sets the picNum to the given number
     * 
     * @param int newNum is the number given to set picNum equal to
     */
    public void setPicNum(int newNum) {
        picNum = newNum;
    }

    /**
     * getPicNum returns the current picNum
     * 
     * @return int picNum is the number of the current picture's animation
     */
    public int getPicNum() {
        return picNum;
    }

    /**
     * getX returns the xLocation of a gamePiece
     * 
     * @return int xLocation is the x location of the GamePiece
     */
    public int getX() {
        return xLocation;
    }

    /**
     * getY returns the yLocation of a gamePiece
     * 
     * @return int yLocation is the y location of the GamePiece
     */
    public int getY() {
        return yLocation;
    }

    /**
     * getXIncr returns the x velocity of a GamePiece
     * 
     * @return int xincr is the increase in x per tick of the GamePiece
     */
    public int getXIncr() {
        return xincr;
    }

    /**
     * getYIncr returns the y velocity of a GamePiece
     * 
     * @return int yincr is the increase in y per tick of the GamePiece
     */
    public int getYIncr() {
        return yincr;
    }

    /**
     * getWidth returns the width of the GamePiece
     * 
     * @return int width is the width in pixels of the GamePiece
     */
    public int getWidth() {
        return width;
    }

    /**
     * getHeight returns the height of the GamePiece
     * 
     * @return int height is the height in pixels of the GamePiece
     */
    public int getHeight() {
        return height;
    }

    /**
     * getSprite returns the Sprite of the GamePiece
     * 
     * @return Sprite sprite is the Enum value of the exact GamePiece it is
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * setX takes one parameter and sets the xLocation of the GamePiece
     * 
     * @param int x is the xLocation of a GamePiece
     */
    public void setX(int x) {
        xLocation = x;
    }

    /**
     * setY takes one parameter and sets the yLocation of the GamePiece
     * 
     * @param int y is the yLocation of a GamePiece
     */
    public void setY(int y) {
        yLocation = y;
    }

    /**
     * setXIncr takes one parameter and sets the xIncr of the GamePiece
     * 
     * @param int x is the x increase of a GamePiece
     */
    public void setXIncr(int x) {
        xincr = x;
    }

    /**
     * setYIncr takes one parameter and sets the yIncr of the GamePiece
     * 
     * @param int y is the y increase of a GamePiece
     */
    public void setYIncr(int y) {
        yincr = y;
    }

    /**
     * setWidth takes one parameter and sets the Width of the GamePiece
     * 
     * @return int w is the width in pixels of the GamePiece
     */
    public void setWidth(int w) {
        width = w;
    }

    /**
     * setHeight takes one parameter and sets the height of the GamePiece
     * 
     * @return int h is the height in pixels of the GamePiece
     */
    public void setHeight(int h) {
        height = h;
    }

    /**
     * setSprite takes one parameter and sets the Sprite of the GamePiece
     * 
     * @return Sprite s is the Sprite of the GamePiece
     */
    public void setSprite(Sprite s) {
        sprite = s;
    }

    /**
     * isEnemy returns the true or false for if a GamePiece is an Enemy or Food based on its Sprite
     * 
     * @return boolean true/false is the value for if the GamePiece is an enemy or a consumable food
     */
    public boolean isEnemy() {
        if (sprite.equals(Sprite.PLANE) || sprite.equals(Sprite.EAGLE) || sprite.equals(Sprite.REDFOX) || sprite.equals(Sprite.RACCOON)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * isFood returns the true or false for if a GamePiece is a Food based on if its an Enemy
     * 
     * @return boolean !(isEnemy()) is the value for if it's an enemy, or a food
     */
    public boolean isFood() {
        return !(isEnemy());
    }

    /**
     * setOffsets sets the x and y offsets for the GamePiece based on the Sprite it is
     */
    public void setOffsets() {
        if (getSprite().equals(Sprite.REDFOX)) {
            xOffset = FOX_X_OFFSET;
            yOffset = FOX_Y_OFFSET;
        }
        if (getSprite().equals(Sprite.EAGLE)) {
            xOffset = EAGLE_X_OFFSET;
            yOffset = EAGLE_Y_OFFSET;
        }
        if (getSprite().equals(Sprite.PLANE)) {
            xOffset = PLANE_X_OFFSET;
            yOffset = PLANE_Y_OFFSET;
        }
        if (getSprite().equals(Sprite.BUNNY)) {
            xOffset = BUNNY_X_OFFSET;
            yOffset = BUNNY_Y_OFFSET;
        }
        if (getSprite().equals(Sprite.MOUSE)) {
            xOffset = MOUSE_X_OFFSET;
            yOffset = MOUSE_Y_OFFSET;
        }
        if (getSprite().equals(Sprite.FISH)) {
            xOffset = FISH_X_OFFSET;
            yOffset = FISH_Y_OFFSET;
        }
        if (getSprite().equals(Sprite.RACCOON)) {
            xOffset = RACCOON_X_OFFSET;
            yOffset = RACCOON_Y_OFFSET;
        }
        if (getSprite().equals(Sprite.SNAKE)) {
            xOffset = SNAKE_X_OFFSET;
            yOffset = SNAKE_Y_OFFSET;
        }

    }

    /**
     * getXOffset returns the int value of the horizontal distance between the edge of the image and where the hitbox should be
     * 
     * @return int xOffset is the value of the horizontal distance between the edge of the image and where the hitbox should be
     */
    public int getXOffset() {
        return xOffset;
    }

    /**
     * getYOffset returns the int value of the vertical distance between the edge of the image and where the hitbox should be
     * 
     * @return int yOffset is the value of the vertical distance between the edge of the image and where the hitbox should be
     */
    public int getYOffset() {
        return yOffset;
    }

    /**
     * isSpecialFood returns the true/false for if the GamePiece is a specialFood
     * 
     * @return boolean isSpecialFood is the boolean true/false value for if the GamePiece is a specialfood
     */
    public boolean isSpecialFood() {
        return isSpecialFood;
    }
    
    /**
     * setSpecialFood takes one parameter and sets the boolean true/false value for if the GamePiece is a specialfood
     * 
     * @param boolean bool is the boolean true/false value for if the GamePiece is a specialfood
     */
    public void setSpecialFood(boolean bool) {
        isSpecialFood = bool;
    }

    /**
     * incrImgCount increases the count of the image by one
     */
    public void incrImgCount() {
        imgCount++;
    }

    /**
     * getImgCount returns the count of the image of the GamePiece
     * 
     * @return int imgCount is the count of the image of the GamePiece
     */
    public int getImgCount() {
        return imgCount;
    }
}
