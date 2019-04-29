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
    int bird; //0 is osprey, 1 is harrier
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

    public Model(int fwidth, int fheight, int imageWidth, int imageHeight, int newBird) {
    	fWidth = fwidth;
    	fHeight = fheight;
    	imgWidth = imageWidth;
    	imgHeight = imageHeight;
    	player = new Player();
        groundLevel = (int)(0.8*fHeight);
        spawnGamePieces();
        indexOfGP = 0;
        bird = newBird;
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
        //for(GamePiece g: gamePieces){
    	Iterator it = gamePieces.iterator();
    	while(it.hasNext()) {
    		GamePiece g = (GamePiece) it.next();
            if(player.checkCollision(g)){            	
            	if(g instanceof Food) {
            		eat((Food)g);
            		//System.out.println("this is a food");
            	}
            	
                else if(g instanceof Enemy){
                    obstacleHit((Enemy)g);
                    //System.out.println("this is a enemy");
                }
            	it.remove();
            }
    	}
        player.isAlive();
        clearCurrentGP();
        seeCurrentGP();
        //System.out.println("Health: " + player.health);
        //System.out.println("Score: " + player.score);

        
    }

    /**
     * spawnGamePieces() will randomly generate an obstacle on the screen, including
     * buildings, enemies, food, etc.
     */

    //randomize the location of the GamePieces (1 every screen)
    public void spawnGamePieces() {
        int numGamePieces = 0;
        //background0:
            //land: 0-432px, 1776-2640px, 
        int tempXLoc = (int)(Math.random() * 2639 + 1776);
        System.out.println(tempXLoc);
        int landHeight = 96;
        boolean flag = true;
        if(bird == 1){ //northern harrier
            while(numGamePieces < 40){
                if(Math.random() < 0.5){ //food
                    if(Math.random() < 0.5){//bunny
                        gamePieces.add(new Food(tempXLoc, landHeight, 1));
                    }
                    else{//mouse
                        gamePieces.add(new Food(tempXLoc, (int) (Math.random()*fHeight), 0));
                    }
                }
                else{//enemy
                    if(Math.random() < 0.5){//red fox
                        gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*fHeight), 0));
                    }
                    else{//raccoon
                        gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*fHeight), 1));
                    }
                }
                numGamePieces++;
                //tempXLoc+=Math.random()
            }
        }
        else{
            while(numGamePieces < 40){
                if(Math.random() < 0.5){ //food
                    if(Math.random() < 0.5){//snakes
                        gamePieces.add(new Food(tempXLoc, (int) (Math.random()*fHeight), 2));
                    }
                    else{//fish
                        gamePieces.add(new Food(tempXLoc, (int) (Math.random()*fHeight), 3));
                    }
                }
                else{//enemy
                    if(Math.random() < 0.5){//eagles
                        gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*fHeight), 2));
                    }
                    else{//planes
                        gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*fHeight), 3));
                    }
                }
                numGamePieces++;
            }
        }
//        while(numGamePieces < 40){  
//            if(Math.random() < .5) {
//                    gamePieces.add(new Food(tempXLoc, (int) (Math.random()*fHeight), 1));
//                    gamePieces.add(new Enemy(tempXLoc + 500, (int) (Math.random()*fHeight), 0));
//
//            }else {
//                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*fHeight), 0));
//                    gamePieces.add(new Food(tempXLoc + 500, (int) (Math.random()*fHeight), 1));
//            }
//
//            numGamePieces++;
//            tempXLoc +=fWidth;
//        	
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
        player.score+=f.foodValue;
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
