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

    public Model(int frameWidth, int frameHeight, int imageWidth, int imageHeight) {
        fWidth = frameWidth;
        fHeight = frameHeight;
        imgWidth = imageWidth;
        imgHeight = imageHeight;
    }

    /**
     * updateLocationAndDirection() will increment/decrement the object's location
     * and change the object's direction based off of user input from Controller
     */

    public void updateLocationAndDirection() {

    }

    /**
     * the handleTicks() method will update the screen every tick, including 
     * updating the player's direction and location, spawning obstacles periodically,
     * updating progress on minimap, and checking win/lose conditions.
     */

    public void handleTicks() {

    }

    /**
     * spawnObstacle() will randomly generate an obstacle on the screen, including
     * buildings, enemies, food, etc.
     */

    public void spawnObstacle() {

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

    }


    /**
     * collide() will handle if any collisions between the player and GamePieces
     * based on what the GamePiece is (food, enemy, obstacle) 
     */

    public void collide() {

    }
}
