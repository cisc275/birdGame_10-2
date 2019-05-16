/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

/**
 * Player class contains all the attributes and methods related to the bird
 * (user).
 *
 * @author crnis
 */
public class Player extends GamePiece {

    private static int health;
    private static int score;
    private int xOffset;
    private int yOffset;
    private Sprite bird;
    public static boolean alive=true;

    public Player() {
        setYIncr(8);
        setX(30);
        setY(300);
        setWidth(184);
        setHeight(100);
        health = 250;
        xOffset = 30;
        yOffset = 30;
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
        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();
        int xOff = getXOffset();
        int yOff = getYOffset();
        int otherX = piece.getX();
        int otherY = piece.getY();
        int otherW = piece.getWidth();
        int otherH = piece.getHeight();
        int otherYOff = piece.getYOffset();
        int otherXOff = piece.getXOffset();

        //probably bugged, should test more
        if (x + w - xOff >= otherX + otherXOff && x + w - xOff <= otherX + otherW - otherXOff) {
            if (y + yOff >= otherY + otherYOff && y + yOff <= otherY + otherW - otherYOff) {
                return true;
            } else if (y + h - yOff >= otherY + otherYOff && y + h - yOff <= otherY + otherH - otherYOff) {
                return true;
            }
        } else if (x + xOff >= otherX + otherXOff && x + xOff <= otherX + otherH - otherXOff) {
            if (y + yOff >= otherY + otherYOff && y + yOff <= otherY + otherW - otherYOff) {
                return true;
            }
            if (y + h - yOff >= otherY + otherYOff && y + h - yOff <= otherY + otherH - otherYOff) {
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

    public void reset() {
        setHealth(250);
    }
}
