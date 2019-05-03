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
    static int bird; //0 is osprey, 1 is harrier
    static int level;
    private int fWidth;
    private int fHeight;
    private int imgHeight;
    private int imgWidth;
    private int groundLevel;
  //  int xLocation;
  //  int yLocation;  These are implemented in individual gamepiece classes
  //  int xincr;
  //  int yincr;
    private int sceneNum;
    private ArrayList<GamePiece> gamePieces = new ArrayList<>();
    private int progress;
    private int enemyFrequency;
    private int foodFrequency;
    private int specialfoodFrequency;
    private int totalLevelTicks;
    private static Direction direction;
    private Player player;
    //GamePiece currentGP;
    private int indexOfGP;
    private ArrayList<GamePiece> currentGPs = new ArrayList<>();
    private GamePiece furthestGP;
    
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
    	setImgWidth(imageWidth);
    	setImgHeight(imageHeight);
    	player = new Player();
//        setGroundLevel((int)(0.8*fHeight));
    	setGroundLevel(fHeight - imgHeight);
        setIndexOfGP(0);
        indexOfGP = 0;
      //  bird = newBird;
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
    		if (player.getY() > 0) {      //Needs to be adjusted
    			player.move(Direction.UP);
    		}
    	}
    	if (direction == Direction.DOWN){
    		if (player.getY() < fHeight - imgHeight) {
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
    	Iterator<GamePiece> it = gamePieces.iterator();
    	while(it.hasNext()) {
    		GamePiece g = (GamePiece) it.next();
            if(player.checkCollision(g)){            	
            	if(g.isFood()) {
            		eat((Food)g);
            	}
                else if(g.isEnemy()){
                    obstacleHit((Enemy)g);
                }
            	it.remove();
            }
    	}
        player.isAlive();
        clearCurrentGP();
        seeCurrentGP();
        
        if(furthestGP == null || furthestGP.getX() < 0){
        	endOfLevel();
        }
        
        if(player.getX() > (fWidth - imgWidth)) {
        	System.out.println("youre off the screen");
        }
    }
    
    
    public void endOfLevel() {
    	player.setXIncr(40);    	
//    	player.setXIncr((int)(fWidth * .5));
    	player.setX(player.getX() + player.getXIncr());

    }

    /**
     * spawnGamePieces() will randomly generate an obstacle on the screen, including
     * buildings, enemies, food, etc.
     */

    //randomize the location of the GamePieces (1 every screen)
    public void spawnGamePieces() {
    	SpecialFood.initializeQandAs();
        int numGamePieces = 0;
        //background0:
            //land: 0-432px, 1776-2640px, 
        //int tempXLoc = (int)(Math.random() * 2639 + 1776);
        int tempXLoc = 500;
        int landHeight = 96;
        boolean flag = true;
        if(bird == 1){ //northern harrier
            while(numGamePieces < 5){
            	int bottomHalfY = ((int) (Math.random()*(fHeight/2)) + (fHeight/2));
            	int topHalfY = ((int) (Math.random()*(fHeight/2)));
            	
                if(Math.random() < .5){ //food
                    if(Math.random() < .5){//bunny
                        gamePieces.add(new Food(tempXLoc, (int) (Math.random()*groundLevel), Type.BUNNY));
                    }
                    else{//mouse
                        gamePieces.add(new Food(tempXLoc, (int) (Math.random()*groundLevel), Type.MOUSE));
                    }
                }
                else{//enemy
                    if(Math.random() < .5){//red fox
                        gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*groundLevel), Type.REDFOX));
                    }
                    else{//raccoon
                        gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*groundLevel), Type.RACCOON));
                    }
                }
                numGamePieces++;
                tempXLoc+=fWidth/3;
            }
        }
        else{
            while(numGamePieces < 100){
            	int bottomHalfY = ((int) (Math.random()*(fHeight/2)) + (fHeight/2));
            	int topHalfY = ((int) (Math.random()*(fHeight/2)));
            	
                if(Math.random() < .5){ //food
                    if(Math.random() < .5){//snakes
                        gamePieces.add(new Food(tempXLoc, (int) (Math.random()*groundLevel), Type.SNAKE));
                    }
                    else{//fish
                        gamePieces.add(new Food(tempXLoc, (int) (Math.random()*groundLevel), Type.FISH));
                    }
                }
                else{//enemy
                    if(Math.random() < 0.5){//eagles
                        gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*groundLevel), Type.EAGLE));
                    }
                    else{//planes
                        gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*groundLevel), Type.PLANE));
                    }
                }
                numGamePieces++;
                tempXLoc+=fWidth/3;
            }
            //System.out.println(gamePieces);
        }

        for(GamePiece gp: gamePieces) {
        	int furthestGPLoc = 0;
        	if(gp.getX() > furthestGPLoc) {
        		furthestGPLoc = gp.getX();
        		furthestGP = gp;
        		
        	}
        }
        
//        
//        for(GamePiece gp: gamePieces) {
//        	System.out.println("gp loc: " + gp.getX());
//        }
        
        

        
        System.out.println("hi, furthest gamepiece loc: " + furthestGP);
      
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
        player.setScore(player.getScore() + f.getFoodValue());
        if(player.getHealth() > 90){
            player.setHealth(100);
        }
        else{
            player.setHealth( player.getHealth() + 10);
        }
    }

    /**
     * obstacleHit() will handle the event of the player dying by resetting everything 
     * and taking them back to the level screen.
     */

    public void obstacleHit(Enemy e) {
        player.setScore(player.getScore() - e.getDamage());
        if(player.getHealth() < 20){
            player.setHealth(0);
        }
        else{
            player.setHealth(player.getHealth() - 20);
        }
    }

    /**
     * nest() will handle the event of the player nesting by displaying screen
     * showing bird nesting.
     */

    public void nest() {

    }

	public int getImgHeight() {
		return imgHeight;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<GamePiece> getCurrentGPs() {
		return currentGPs;
	}
	
	public ArrayList<GamePiece> getGamePieces() {
		return gamePieces;
	}

	//private static Direction direction;
	
	public Direction getDirection() {
		return direction;
	}
	
	public static void setDirection(Direction Direction) {
		direction = Direction;
	}
	
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getGroundLevel() {
		return groundLevel;
	}

	public void setGroundLevel(int groundLevel) {
		this.groundLevel = groundLevel;
	}

	public int getSceneNum() {
		return sceneNum;
	}

	public void setSceneNum(int sceneNum) {
		this.sceneNum = sceneNum;
	}

	public int getEnemyFrequency() {
		return enemyFrequency;
	}

	public void setEnemyFrequency(int enemyFrequency) {
		this.enemyFrequency = enemyFrequency;
	}

	public int getFoodFrequency() {
		return foodFrequency;
	}

	public void setFoodFrequency(int foodFrequency) {
		this.foodFrequency = foodFrequency;
	}

	public int getSpecialfoodFrequency() {
		return specialfoodFrequency;
	}

	public void setSpecialfoodFrequency(int specialfoodFrequency) {
		this.specialfoodFrequency = specialfoodFrequency;
	}

	public int getTotalLevelTicks() {
		return totalLevelTicks;
	}

	public void setTotalLevelTicks(int totalLevelTicks) {
		this.totalLevelTicks = totalLevelTicks;
	}

	public int getIndexOfGP() {
		return indexOfGP;
	}

	public void setIndexOfGP(int indexOfGP) {
		this.indexOfGP = indexOfGP;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	public void setFHeight(int h) {
		this.fHeight = h;
	}
	
	public void setProgress(int h) {
		this.progress = h;
	}
    
	

}
