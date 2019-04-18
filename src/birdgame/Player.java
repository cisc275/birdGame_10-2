/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

/**
 * Player class contains all the attributes and methods related to the bird (user).
 * @author crnis
 */
public class Player extends GamePiece {
	int health;
    int score;

	Player(){
	yincr = 3;
	xLocation = 30;
	yLocation = 300;
	width = 165;
	height = 165;
	}


	/**
     * isAlive() checks if the player is still alive
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
     * @return true if player collides with GamePiece and false otherwise
     */

    public boolean checkCollision(GamePiece piece) {
    	int x = xLocation;
    	int y = yLocation;
    	int w = width;
    	int h = height;
    	int otherX = piece.xLocation;
    	int otherY = piece.yLocation;
    	int otherW = piece.width;
    	int otherH = piece.height;
    	
    	//probably bugged, should test more
        if (x + w >= otherX && x + w <= otherX + otherW) {
        	if (y >= otherY && y <= otherY + otherW) {
        		return true;
        	}
        	else if (y + h >= otherY && y + h <= otherY + otherH) {
        		return true;	
        	}
        }
        else if (x >= otherX && x <= otherX + otherH) {
        	if (y >= otherY && y <= otherY + otherW) {
        		return true;
        	}
        	if (y + h >= otherY && y + h <= otherY + otherH) {
        		return true;
        	}
        }
       
       return false;
        
    }
    public void move(Direction dir) {
    	if (dir.equals(Direction.UP)) {
    		yLocation = yLocation - yincr;
    	}
    	if (dir.equals(Direction.DOWN)) {
    		yLocation = yLocation + yincr;
    	}
    }

}
