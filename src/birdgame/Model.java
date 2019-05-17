/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Model contains and deals with the basic logic of the game, including updating
 * an object's location and direction, handling collisions, spawning obstacles,
 * and player progress.
 *
 * @author crnis
 */
public class Model {

    public static HashMap<String, HashMap<String, String[]>> factsAndQuestions;
    public static HashMap<String,String[]> questionsToAsk;
    private static Sprite bird = Sprite.OSPREY; //Solves NULL POINTER EXCEPTION, Don't touch!
    private static int round;
    private int numGamePiecesInRoundLeft = 50;
    protected int fWidth;
    protected int fHeight;
    private int imgHeight;
    private int imgWidth;
    protected int groundLevel;
    private int sceneNum;
    protected ArrayList<GamePiece> gamePieces = new ArrayList<>();
    static String[] facts;
    private int progress;
    private int enemyFrequency;
    private int foodFrequency;
    private int specialfoodFrequency;
    private int totalLevelTicks;
    private static int currentFactIndex = 0;
    private static Direction direction;
    private static Player player;
    //GamePiece currentGP;
    private int indexOfGP;
    private static String currentFact;
    private CopyOnWriteArrayList<GamePiece> currentGPs = new CopyOnWriteArrayList<>();
    private GamePiece furthestGP = new GamePiece();
    private static boolean specialFoodEaten = false;
    static ArrayList<String> avaliableFacts;
    
    

    /**
     * Model constructor will take in four variables defined below
     *
     * @param fwidth is an int for the width of the frame
     * @param fheight is an int for the height of the frame
     * @param imageWidth is an int for the width of the image
     * @param imageHeight is an int for the height of the image
     */
    public Model(int fwidth, int fheight, int imageWidth, int imageHeight) {
        //gamePieces = new ArrayList<>();
        fWidth = fwidth;
        fHeight = fheight;
        setImgWidth(imageWidth);
        setImgHeight(imageHeight);
        player = new Player();
//        setGroundLevel((int)(0.8*fHeight));
        setGroundLevel(fHeight - imgHeight);
        setIndexOfGP(0);
        indexOfGP = 0;
        //round = 1;

    }

    /**
     * updateLocationAndDirection() will increment/decrement the object's
     * location and change the object's direction based off of user input from
     * Controller
     */
    public void updateLocationAndDirection() {
        for (GamePiece gameObjs : gamePieces) {
            gameObjs.move();
        }

        if (direction == Direction.UP) {
            if (player.getY() > 0) {      //Needs to be adjusted
                player.move(Direction.UP);
            }
        }
        if (direction == Direction.DOWN) {
            if (player.getY() < fHeight - imgHeight) {
                player.move(Direction.DOWN);
            }
        }

    }

    /**
     * the handleTicks() method will update the screen every tick, including
     * updating the player's direction and location, spawning obstacles
     * periodically, updating progress on minimap, and checking win/lose
     * conditions.
     */
    public void handleTicks() {
        updateLocationAndDirection();
        //for(GamePiece g: gamePieces){
        Iterator<GamePiece> it = gamePieces.iterator();
        while (it.hasNext()) {
            GamePiece g = (GamePiece) it.next();
            if (player.checkCollision(g)) {
                if (g.isSpecialFood()) {
                    eatSpecial((SpecialFood) g);
                }
                if (g.isFood()) {
                    eat((Food) g);
                } else {
                    obstacleHit((Enemy) g);
                }
                it.remove();
            }
        }
        if (player.isAlive()==false) {
        	player.alive=false;
        }
        clearCurrentGP();
        seeCurrentGP();

        if (currentGPs.size() == 0 && totalLevelTicks != 0) {
            if(round == 1)
                endOfLevel();
            else if(round == 2)
                endOfLevel();
            else if(round == 3)
            	endOfLevel();
        }

//        if (player.getX() > (fWidth - imgWidth)) {
//            if(round == 1){
//                View.setIsOspreyRound1Over(true);
//                round = 2;
//                player.setX(30);
//            }
//            else if(round == 2){
//                View.setIsOspreyRound2Over(true);
//                round = 0;
//                player.setX(30);
//            }
//        }
        //System.out.println(gamePieces);
        totalLevelTicks++;
    }

    public void endOfLevel() {
        player.setXIncr(30);
//    	player.setXIncr((int)(fWidth * .5));
        player.setX(player.getX() + player.getXIncr());
        if (player.getX() > (fWidth - imgWidth)) {
            if(round == 1){
                View.setIsOspreyRound1Over(true);
            }
            else if(round == 2){
                View.setIsOspreyRound2Over(true);
            }
            else if(round == 3) {
            	View.setIsHarrierRoundOver(true);
            }
        }

    }

    public static ArrayList<String> getAvaliableFacts() {
        return avaliableFacts;
    }

    /**
     * spawnGamePieces() will randomly generate an obstacle on the screen,
     * including buildings, enemies, food, etc.
     */
    //randomize the location of the GamePieces (1 every screen)
    public void spawnHarrierGamePieces() {
        //SpecialFood.generateFactsAndQuestions();
        int numGamePieces = 0;
        int numSpecialFood = 0;
        //background0:
        //land: 0-432px, 1776-2640px, 
        //int tempXLoc = (int)(Math.random() * 2639 + 1776);
        int tempXLoc = 1200;

        //int bottomHalfY = ((int) (Math.random()*(fHeight/2)) + (fHeight/2));
        //int topHalfY = ((int) (Math.random()*(fHeight/2)));
        int maxSpecialFood = 5;
        while (numGamePieces < numGamePiecesInRoundLeft) {
            if (numSpecialFood < maxSpecialFood) {
                if (Math.random() < .3) {
                    if (Math.random() < .5) {
                        gamePieces.add(new SpecialFood(2 * tempXLoc, (int) (Math.random() * groundLevel), Sprite.BUNNY));
                    } else {
                        gamePieces.add(new SpecialFood(2 * tempXLoc, (int) (Math.random() * groundLevel), Sprite.MOUSE));

                    }
                    numSpecialFood++;

                }
            }

            if (Math.random() < .5) { //food
                if (Math.random() < .5) {//bunny
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.BUNNY));
                } else {//mouse
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.MOUSE));
                }
            } else {//enemy
                if (Math.random() < .5) {//red fox
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random() * groundLevel), Sprite.REDFOX));
                } else {//raccoon
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random() * groundLevel), Sprite.RACCOON));
                }
            }
            numGamePieces++;
            tempXLoc += fWidth / 3;

        }
        for (GamePiece gp : gamePieces) {
            int furthestGPLoc = 0;
            if (gp.getX() > furthestGPLoc) {
                furthestGPLoc = gp.getX();
                setFurthestGP(gp);
            }
        }

    }

    public void spawnOspreyGamePieces() {
        int numGamePieces = 0;
        int numSpecialFood = 0;
        int tempXLoc = 1200;
        int maxSpecialFood = 3;
        while (numGamePieces < numGamePiecesInRoundLeft) {
            if (numSpecialFood < maxSpecialFood) {
                if (Math.random() < .3) {
                    if (Math.random() < .5) {
                        gamePieces.add(new SpecialFood(2 * tempXLoc, (int) (Math.random() * groundLevel), Sprite.SNAKE));
                    } else {
                        gamePieces.add(new SpecialFood(2 * tempXLoc, (int) (Math.random() * groundLevel), Sprite.FISH));

                    }
                    numSpecialFood++;

                }
            }

            if (Math.random() < .5) { //food
                if (Math.random() < .5) {//snakes
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.SNAKE));
                } else {//fish
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.FISH));
                }
            } else {//enemy
                if (Math.random() < 0.5) {//eagles
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random() * groundLevel), Sprite.EAGLE));
                } else {//planes
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random() * groundLevel), Sprite.PLANE));
                }
            }
            numGamePieces++;
            tempXLoc += fWidth / 3;
        }
        //System.out.println(gamePieces);
        for (GamePiece gp : gamePieces) {
            int furthestGPLoc = 0;
            if (gp.getX() > furthestGPLoc) {
                furthestGPLoc = gp.getX();
                setFurthestGP(gp);

            }
        }
        //System.out.println(gamePieces);
    }

    public void clearCurrentGP() {
        currentGPs.clear();
    }
    public void clearGP(){
        gamePieces.clear();
    }

    public void seeCurrentGP() {
        for (GamePiece g : gamePieces) {
            if (g.getX() <= fWidth && g.getX() >= -500) {
                currentGPs.add(g);
            }
        }
    }

    //minimap
    /**
     * will return an int value describing progress throughout the level to
     * update the minimap.
     *
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
        if (player.getHealth() > 225) {
            player.setHealth(250);
        } else {
            player.setHealth(player.getHealth() + 25);
        }
    }

    public void eatSpecial(SpecialFood sf) {
        specialFoodEaten = true;
        View.setMomentEaten(View.getFrameCount());
        if (hasMoreFacts()) {
        currentFact = facts[currentFactIndex];
        }
        player.setHealth(250);
    }

    public static boolean specialFoodEaten() {
        return specialFoodEaten;
    }

    public static void setSpecialFoodEaten(boolean bool) {
        specialFoodEaten = bool;
    }

    /**
     * obstacleHit() will handle the event of the player dying by resetting
     * everything and taking them back to the level screen.
     */
    public void obstacleHit(Enemy e) {
        player.setScore(player.getScore() - e.getDamage());
        if (player.getHealth() < e.getDamage()) {
            player.setHealth(0);
        } else {
            player.setHealth(player.getHealth() - e.getDamage());
        }
    }

    /**
     * nest() will handle the event of the player nesting by displaying screen
     * showing bird nesting.
     */
    public void nest() {

    }
    public 

    public int getImgHeight() {
        return imgHeight;
    }

    public Player getPlayer() {
        return player;
    }

    public CopyOnWriteArrayList<GamePiece> getCurrentGPs() {
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
    public static void incrFactIndex() {
    	currentFactIndex++;
    }

    public void setProgress(int h) {
        this.progress = h;
    }

    public void setFurthestGP(GamePiece gP) {
        this.furthestGP = gP;
    }

    public static Sprite getBird() {
        return bird;
    }

    public static void setBird(Sprite b) {
        bird = b;
    }

    public static String getCurrentFact() {
        return facts[currentFactIndex];
    }

    public void setRound(int r) {
        round = r;
    }

    public int getRound() {
        return round;
    }

    public void generateHarrierQuestions() {
    	currentFactIndex = 0;
    	facts = new String[] {"Northern Harriers, eat rodents","Northern Harriers are, non-migratory birds","Foxes are a predator, for Northern Harriers"};
        factsAndQuestions = new HashMap<>();
        HashMap<String, String[]> QandAsHarrier1 = new HashMap<>();
        String[] harrierFood = {"rodents", "fish", "eagles", "plants", "rodents"};
        QandAsHarrier1.put("What do Northern Harriers eat?", harrierFood);
        factsAndQuestions.put("Northern Harriers eat rodents", QandAsHarrier1);

        HashMap<String, String[]> QandAsHarrier2 = new HashMap<>();
        String[] harrierMigrate = {"They migrate to South America", "They migrate to California", "They don't migrate", "They migrate to canada", "They don't migrate"};
        QandAsHarrier2.put("Where do Harriers migrate", harrierMigrate);
        factsAndQuestions.put("Northern Harriers are non-migratory birds", QandAsHarrier2);

        HashMap<String, String[]> QandAsHarrier3 = new HashMap<>();
        String[] harrierPred = {"Foxes", "Snakes", "Cats", "Humans", "Foxes"};
        QandAsHarrier3.put("What is a major predator of Northern Harriers", harrierPred);
        factsAndQuestions.put("Foxes are a predator for Northern Harriers", QandAsHarrier3);

        avaliableFacts = new ArrayList<String>(factsAndQuestions.keySet());

    }

    public void generateOspreyQuestions() {
    	currentFactIndex = 0;
    	facts = new String[] {"Ospreys like to,eat Snakes and Fish","Ospreys migrate to, South America for, the winter","Eagles are a, predator of Ospreys"};
        factsAndQuestions = new HashMap<>();
        HashMap<String, String[]> QandAsOsprey1 = new HashMap<>();
        String[] OspreyFood = {"Mice and Rabbits", "Snakes and Fish", "Raccoons", "Sankes and Fish"};
        QandAsOsprey1.put("What do Ospreys eat?", OspreyFood);
        factsAndQuestions.put("Ospreys like to eat Snakes and Fish", QandAsOsprey1);

        HashMap<String, String[]> QandAsOsprey2 = new HashMap<>();
        String[] OspreyMigrate = {"They migrate to South America", "They migrate to California", "They don't migrate", "They migrate to canada", "They migrate to South America"};
        QandAsOsprey2.put("Where do Ospreys migrate", OspreyMigrate);
        factsAndQuestions.put("Ospreys migrate to South America for the winter", QandAsOsprey2);

        HashMap<String, String[]> QandAsOsprey3 = new HashMap<>();
        String[] OspreyPred = {"Foxes", "Snakes", "Eagles", "Eagles"};
        QandAsOsprey3.put("What is a major predator of Ospreys", OspreyPred);
        factsAndQuestions.put("Eagles are a predator of Ospreys", QandAsOsprey3);
        avaliableFacts = new ArrayList<String>(factsAndQuestions.keySet());

    }
    public static boolean hasMoreFacts() {
    	return currentFactIndex < facts.length;
    }
  

}
