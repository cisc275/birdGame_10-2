/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

/**
 * Model contains and deals with the basic logic of the game, including updating
 * an object's location and direction, handling collisions, spawning obstacles, 
 * and player progress.
 * @author crnis
 */
public class Model {
    int fWidth;
    int fHeight;
    int imgHeight;
    int imgWidth;
    int xLocation;
    int yLocation;
    int xincr;
    int yincr;
    int sceneNum;
    GamePiece[] gamePieces;
    int progress;
    int enemyFrequency;
    int foodFrequency;
    int specialfoodFrequency;
    int totalLevelTicks;
    int score;
    
    /**
     * Model constructor will take in four variables defined below
     * @param fwidth is an int for the width of the frame
     * @param fheight is an int for the height of the frame
     * @param imageWidth is an int for the width of the image
     * @param imageHeight is an int for the height of the image
     */

    public Model(int fwidth, int fheight, int imageWidth, int imageHeight) {

    }

    /**
     * updateLocationAndDirection() will increment/decrement the object's location
     * and change the object's direction based off of user input from Controller
     */

    public void updateLocationAndDirection() {
    	for (GamePiece gP : gamePieces) {
    		gP.move();
    	}
    	//not sure how to do the user-input stuff.
    	//Might make more sense to do the userinput before the above code, because player will move in the above loop
    }

    /**
     * the handleTicks() method will update the screen every tick, including 
     * updating the player's direction and location, spawning obstacles periodically,
     * updating progress on minimap, and checking win/lose conditions.
     */

    public void handleTicks() {
    	updateLocationAndDirection();
    	/*for (GamePiece gP : gamePieces) {
    		int xLoc = gP.xLocation;
    		int yLoc = gP.yLocation;
    		Direction dir = gP.Direction;
    		update(xLoc,yLoc,dir);
    	}*/
    	//doesn't work, but is somewhat what we want I think
    	progress += 1; //this lets progress increase throughout the level
    }

    /**
     * spawnObstacle() will randomly generate an obstacle on the screen, including
     * buildings, enemies, food, etc.
     */

    public void spawnObstacle() {
    	//we can spawn obstacles off the screen and have them move on to it
    }
   
    //minimap

    /**
     * will return an int value describing progress throughout the level to update
     * the minimap.
     * @return an int value that describes the progress of the user
     */
    public int getProgress() {
        return progress;
    }

    /**
     * eat() will increment the player's score based off of what is eaten.
     */

    public void eat() {
    	//need a way to figure out what food is being eaten
    	//increase the player's health,score by the foodvalue of the food
    }

    /**
     * die() will handle the event of the player dying by resetting everything 
     * and taking them back to the level screen.
     */

    public void die() {

    }

    /**
     * nest() will handle the event of the player nesting by displaying screen
     * showing bird nesting.
     */

    public void nest() {
    	//imo we would want to have a set x,y series of events for nest
    	//nest would do everything including moving the player, any other needed characters
    	//nest would include the wait commands and stuff
    	//it would essentially operate all on its own without user input until the nest stuff was all done
    }


    /**
     * collide() will handle if any collisions between the player and GamePieces
     * based on what the GamePiece is (food, enemy, obstacle) 
     */

    public void collide() {
    	//need a way to determine what is colliding and with what
    	//based on what the player collides with, different methods get called
    	//such as eat, die, nest
    }
}
