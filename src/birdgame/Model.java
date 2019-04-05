/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

/**
 *
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
    
    /*
    constructor
    */
    public Model(int width, int height, int imageWidth, int imageHeight) {

    }
    /*
    updateLocationAndDirection() will increment/decrement the object's location
    and change the object's direction
    */
    public void updateLocationAndDirection() {

    }
    
    /*
    the handleTicks() method will call update the screen every tick
    */
    public void handleTicks() {

    }

    /*
    updatePlayer()
    */
    public void updatePlayer() {

    }
    
    /*
    updateObstacles()
    */
    public void updateObstacles() {

    }
    
    /*
    spawnObstacle() will randomly generate an obstacle on the screen (i.e. building,
    enemy, etc.)
    */
    public void spawnObstacle() {

    }
    
    /*
    
    */
    public void start() {

    }
    /*
    getUserInput()
    */
    public void getUserInput() {

    }
    
    public void getProgress() {

    }
    
    /*
    eat() will increment the player's food score
    */
    public void eat() {

    }
    /*
    die() will handle the event of the player dying
    */
    public void die() {

    }

    /*
    nest()
    */
    public void nest() {

    }

    /*
    collide() will handle if any collisions between GamePieces
    */
    public void collide() {

    }
}
