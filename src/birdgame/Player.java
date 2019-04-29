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
	private static int health;
        private static int score;

	Player(){
	setYIncr(20);
	setX(30);
	setY(300);
	setWidth(184);
	setHeight(100);
	health = 100;
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
    	int x = getX();
    	int y = getY();
    	int w = getWidth();
    	int h = getHeight();
    	int otherX = piece.getX();
    	int otherY = piece.getY();
    	int otherW = piece.getWidth();
    	int otherH = piece.getHeight();
    	
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
	
	public static int getHealth() {
		return health;
	}


	public static void setHealth(int health) {
		Player.health = health;
	}

}
