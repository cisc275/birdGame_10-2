/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;
import java.util.ArrayList;
import java.util.Iterator;

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
    int groundLevel;
  //  int xLocation;
  //  int yLocation;  These are implemented in individual gamepiece classes
  //  int xincr;
  //  int yincr;
    int sceneNum;
    ArrayList<GamePiece> gamePieces = new ArrayList<>();
    int progress;
    int enemyFrequency;
    int foodFrequency;
    int specialfoodFrequency;
    int totalLevelTicks;
    static Direction direction;
    Player player;
    //GamePiece currentGP;
    int indexOfGP;
    ArrayList<GamePiece> currentGPs = new ArrayList<>();
    
    /**
     * Model constructor will take in four variables defined below
     * @param fwidth is an int for the width of the frame
     * @param fheight is an int for the height of the frame
     * @param imageWidth is an int for the width of the image
     * @param imageHeight is an int for the height of the image
     */

    public Model(int fwidth, int fheight, int imageWidth, int imageHeight) {
    	fWidth = fwidth;
    	fHeight = fheight;
    	imgWidth = imageWidth;
    	imgHeight = imageHeight;
    	player = new Player();
        groundLevel = (int)(0.8*fHeight);
        spawnGamePieces();
        indexOfGP = 0;
    }

    /**
     * updateLocationAndDirection() will increment/decrement the object's location
     * and change the object's direction based off of user input from Controller
     */

    public void updateLocationAndDirection() {
    	for(GamePiece gameObjs:gamePieces) {
    		gameObjs.move();
    	}
    	
    	if (direction == Direction.UP) {
    		if (player.yLocation > 0) {      //Needs to be adjusted
    			player.move(Direction.UP);
    		}
    	}
    	if (direction == Direction.DOWN){
    		if (player.yLocation < fHeight) {
    			player.move(Direction.DOWN);
    		}
    	}
    	
    }

    /**
     * the handleTicks() method will update the screen every tick, including 
     * updating the player's direction and location, spawning obstacles periodically,
     * updating progress on minimap, and checking win/lose conditions.
     */

    public void handleTicks() {
    	updateLocationAndDirection();
        for(GamePiece g: gamePieces){
            if(player.checkCollision(g)){
                System.out.println(player.checkCollision(g));
                if(g.type.equals("food")){
                    eat((Food)g);
                }
                else if(g.type.equals("enemy")){
                    obstacleHit((Enemy)g);
                }
            }
        }
        player.isAlive();
        clearCurrentGP();
        seeCurrentGP();
        
        
    }

    /**
     * spawnGamePieces() will randomly generate an obstacle on the screen, including
     * buildings, enemies, food, etc.
     */

    //randomize the location of the GamePieces (1 every screen)
    public void spawnGamePieces() {
        int numGamePieces = 0;
        int tempXLoc = 500;
        while(numGamePieces < 20){
            if(Math.random()<0.5){
                Food f = new Food(tempXLoc,500);
                gamePieces.add(f);
            }
            else{
                Enemy e = new Enemy(tempXLoc, 500);
                gamePieces.add(e);
            }
            numGamePieces++;
            tempXLoc += fWidth;
        }
//        for(GamePiece g: gamePieces){
//            System.out.println(g.xLocation +", "+ g.yLocation);
//        }
    }
    public void clearCurrentGP(){
        currentGPs.clear();
    }
    
    public void seeCurrentGP(){
        for(GamePiece g: gamePieces){
            if(g.getX() <= fWidth && g.getX() >= 0)
                currentGPs.add(g);
        }
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

    public void eat(Food f) {
        player.score += f.foodValue;
        if(player.health > 95){
            player.health = 100;
        }
        else{
            player.health+=5;
        }
    }

    /**
     * obstacleHit() will handle the event of the player dying by resetting everything 
     * and taking them back to the level screen.
     */

    public void obstacleHit(Enemy e) {
        player.score-=e.damage;
        if(player.health < 20){
            player.health =0;
        }
        else{
            player.health-=20;
        }
    }

    /**
     * nest() will handle the event of the player nesting by displaying screen
     * showing bird nesting.
     */

    public void nest() {

    }
    

}
