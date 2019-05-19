/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.io.Serializable;

/**
 * Player class contains all the attributes and methods related to the bird
 * (user).
 *
 * @author crnis
 */
public class Player extends GamePiece implements Serializable {

    final static int MAX_BIRD_HEALTH = 250;
    final static int INITIAL_Y_INCREASE = 12;
    final static int INITIAL_X_LOCATION = 30;
    final static int INITIAL_Y_LOCATION = 300;
    final static int BIRD_HEIGHT = 100;
    final static int BIRD_WIDTH = 184;
    final static int X_OFFSET = 30;
    final static int Y_OFFSET = 5;

    private static int health;
    private static int score;
    private int xOffset;
    private int yOffset;
    private Sprite bird;

    public Player() {
        setYIncr(INITIAL_Y_INCREASE);
        setX(INITIAL_X_LOCATION);
        setY(INITIAL_Y_LOCATION);
        setWidth(BIRD_WIDTH);
        setHeight(BIRD_HEIGHT);
        health = MAX_BIRD_HEALTH;
        xOffset = X_OFFSET;
        yOffset = Y_OFFSET;
        score = 0;
    }

    /**
     * isAlive() checks if the player is still alive
     *
     * @return true if player is still alive and false otherwise
     */
    public boolean isAlive() {
        if (health <= 0) {
            return false;
        }
        return true;
    }

    /**
     * checks if the Player collides with a GamePiece
     *
     * @return true if player collides with GamePiece and false otherwise
     */
    public boolean checkCollision(GamePiece piece) {
        int xLocation = getX();
        int yLocation = getY();
        int width = getWidth();
        int height = getHeight();
        int xOff = getXOffset();
        int yOff = getYOffset();
        int otherX = piece.getX();
        int otherY = piece.getY();
        int otherW = piece.getWidth();
        int otherH = piece.getHeight();
        int otherYOff = piece.getYOffset();
        int otherXOff = piece.getXOffset();

        if (xLocation + width - xOff >= otherX + otherXOff && xLocation + width - xOff <= otherX + otherW - otherXOff) {
            if (yLocation + yOff >= otherY + otherYOff && yLocation + yOff <= otherY + otherW - otherYOff) {
            	return true;
            } else if (yLocation + height - yOff >= otherY + otherYOff && yLocation + height - yOff <= otherY + otherH - otherYOff) {
            	return true;
            }
        } else if (xLocation + xOff >= otherX + otherXOff && xLocation + xOff <= otherX + otherH - otherXOff) {
        	if (yLocation + yOff >= otherY + otherYOff && yLocation + yOff <= otherY + otherW - otherYOff) {
                return true;
            }
            if (yLocation + height - yOff >= otherY + otherYOff && yLocation + height - yOff <= otherY + otherH - otherYOff) {
                return true;
            }
        }

        return false;

    }

    public void move(Direction dir) {
        if (dir.equals(Direction.UP)) {

            setY(getY() - getYIncr());
        }
        if (dir.equals(Direction.DOWN)) {
            setY(getY() + getYIncr());
        }
        health--;

    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Player.score = score;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        Player.health = health;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public void resetPlayer() {
        setHealth(MAX_BIRD_HEALTH);
    }
}
