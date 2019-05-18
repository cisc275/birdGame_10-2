/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.Serializable;

/**
 * Model contains and deals with the basic logic of the game, including updating
 * an object's location and direction, handling collisions, spawning obstacles,
 * and player progress.
 *
 * @author crnis
 */
public class Model implements Serializable {

    private initialNumbers initNums = new initialNumbers();
    public static HashMap<String, HashMap<String, String[]>> factsAndQuestions;
    public static HashMap<String,String[]> questionsToAsk = new HashMap<>();
    private static Sprite bird = Sprite.OSPREY; //Solves NULL POINTER EXCEPTION, Don't touch!
    private int maxBirdHealth = initNums.birdHealth();
    private static int round;
    private static int questionNum;
    private static int numberOfQuestions;
    private int numGamePiecesInRoundLeft = initNums.obstaclesPerLevel();
    protected int fWidth;
    protected int fHeight;
    private int imgHeight;
    private int imgWidth;
    protected int groundLevel;
    private static String correctAnswer;
    private int sceneNum;
    protected ArrayList<GamePiece> gamePieces = new ArrayList<>();

    private double chanceSpecialFoodSpawns = initNums.chanceSpecialFoodSpawns();
    private double chanceHarrierFoodIsBunny = initNums.chanceHarrierFoodIsBunny();
    private double chanceFoodSpawnsInsteadOfEnemy = initNums.chanceFoodSpawnsInsteadOfEnemy();
    private double chanceHarrierEnemyIsFox = initNums.chanceFoodSpawnsInsteadOfEnemy();
    private double chanceOspreyFoodIsSnake = initNums.chanceOspreyFoodIsSnake();
    private double chanceOspreyEnemyIsEagle = initNums.chanceOspreyEnemyIsEagle();

    static String[] facts;
    private int totalLevelTicks;
    private static int currentFactIndex = 0;
    private static Direction direction;
    private static Player player;
    //GamePiece currentGP;
    private int indexOfGP;
    private static String currentFact;
    private CopyOnWriteArrayList<GamePiece> currentGPs = new CopyOnWriteArrayList<>();
    private int xLocationWhereGPsAreNoLongerCurrent = initNums.xLocationWhereGPsAreNoLongerCurrent();
    private GamePiece furthestGP = new GamePiece();
    private static boolean specialFoodEaten = false;
    static ArrayList<String> availableFacts;
    private static boolean quiz1Done;
	private static boolean quiz2Done;
	private static boolean quiz3Done;

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
//        if (player.isAlive()==false) {
//        	player.alive=false;
//        }
        clearCurrentGP();
        seeCurrentGP();

        if (currentGPs.size() == 0 && totalLevelTicks != 0) {
            if (round == 1) {
                endOfLevel();
            } else if (round == 2) {
                endOfLevel();
            } else if (round == 3) {
                endOfLevel();
            }
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
        player.setXIncr(initNums.xIncreaseAtEndOfLevel());
//    	player.setXIncr((int)(fWidth * .5));
        player.setX(player.getX() + player.getXIncr());
        if (player.getX() > (fWidth - imgWidth)) {
            if (round == 1) {
                View.setIsOspreyRound1Over(true);
            } else if (round == 2) {
                View.setIsOspreyRound2Over(true);
            } else if (round == 3) {
                View.setIsHarrierRoundOver(true);
            }
        }

    }

    public static ArrayList<String> getAvaliableFacts() {
        return availableFacts;
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
        questionsToAsk = new HashMap<String, String[]>();
        //background0:
        //land: 0-432px, 1776-2640px, 
        //int tempXLoc = (int)(Math.random() * 2639 + 1776);
        int tempXLoc = initNums.xLocationForObstacles();
        int additionalXLocationForSpecialFood = initNums.additionalXLocatitionForSpecialFood();

        //int bottomHalfY = ((int) (Math.random()*(fHeight/2)) + (fHeight/2));
        //int topHalfY = ((int) (Math.random()*(fHeight/2)));
        int maxSpecialFood = 3;
        while (numGamePieces < numGamePiecesInRoundLeft) {
            if (numSpecialFood < maxSpecialFood) {
                if (Math.random() < chanceSpecialFoodSpawns) {
                    if (Math.random() < chanceHarrierFoodIsBunny) {
                        gamePieces.add(new SpecialFood(tempXLoc + additionalXLocationForSpecialFood, (int) (Math.random() * groundLevel), Sprite.BUNNY));
                    } else {
                        gamePieces.add(new SpecialFood(tempXLoc + additionalXLocationForSpecialFood, (int) (Math.random() * groundLevel), Sprite.MOUSE));

                    }
                    numSpecialFood++;

                }
            }

            if (Math.random() < chanceFoodSpawnsInsteadOfEnemy) {
                if (Math.random() < chanceHarrierFoodIsBunny) {
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.BUNNY));
                } else {
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.MOUSE));
                }
            } else {
                if (Math.random() < chanceHarrierEnemyIsFox) {
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random() * groundLevel), Sprite.REDFOX));
                } else {
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
        questionsToAsk = new HashMap<String, String[]>();
        int numGamePieces = 0;
        int numSpecialFood = 0;
        int tempXLoc = initNums.xLocationForObstacles();
        int additionalXLocationForSpecialFood = initNums.additionalXLocatitionForSpecialFood();
        int maxSpecialFood = initNums.maxSpecialFood();
        while (numGamePieces < numGamePiecesInRoundLeft) {
            if (numSpecialFood < maxSpecialFood) {
                // instead of 1
                if (Math.random() < chanceSpecialFoodSpawns) {
                    if (Math.random() < chanceOspreyFoodIsSnake) {
                        gamePieces.add(new SpecialFood(tempXLoc + additionalXLocationForSpecialFood, (int) (Math.random() * groundLevel), Sprite.SNAKE));
                    } else {
                        gamePieces.add(new SpecialFood(tempXLoc + additionalXLocationForSpecialFood, (int) (Math.random() * groundLevel), Sprite.FISH));

                    }
                    numSpecialFood++;

                }
            }

            if (Math.random() < chanceFoodSpawnsInsteadOfEnemy) {
                if (Math.random() < chanceOspreyFoodIsSnake) {
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.SNAKE));
                } else {
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.FISH));
                }
            } else {
                if (Math.random() < chanceOspreyEnemyIsEagle) {
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random() * groundLevel), Sprite.EAGLE));
                } else {
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

    public void clearGP() {
        gamePieces.clear();
    }

    public void seeCurrentGP() {
        for (GamePiece g : gamePieces) {
            if (g.getX() <= fWidth && g.getX() >= xLocationWhereGPsAreNoLongerCurrent) {
                currentGPs.add(g);
            }
        }
    }
    
    void clearQuestionsToAsk(){
        questionsToAsk.clear();
    }
    void clearFactsAndQuestions(){
        factsAndQuestions.clear();
    }

    //minimap
    /**
     * will return an int value describing progress throughout the level to
     * update the minimap.
     *
     * @return an int value that describes the progress of the user
     */
    /**
     * eat() will increment the player's score based off of what is eaten.
     */
    public void eat(Food f) {
        player.setScore(player.getScore() + f.getFoodValue());
        if (player.getHealth() > maxBirdHealth - f.getFoodValue()) {
            player.setHealth(maxBirdHealth);
        } else {
            player.setHealth(player.getHealth() + f.getFoodValue());
        }
    }

    public void eatSpecial(SpecialFood sf) {
        specialFoodEaten = true;
        View.setMomentEaten(View.getFrameCount());
        if (hasMoreFacts()) {
            currentFact = facts[currentFactIndex];
            HashMap<String, String[]> associatedFactandQuestion = factsAndQuestions.get(currentFact);
            questionsToAsk.putAll(associatedFactandQuestion);
            //String[] associatedQuestion

            //	questionsToAsk.add()
        }
        player.setScore(player.getScore() + sf.getFoodValue());
        player.setHealth(maxBirdHealth);
    }

    public static HashMap<String, String[]> getQuestionToAsk() {
        return questionsToAsk;
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
        facts = new String[]{"Northern Harriers, eat rodents", "Northern Harriers are, non-migratory birds", "Foxes are a predator, for Northern Harriers"};
        factsAndQuestions = new HashMap<>();
        HashMap<String, String[]> QandAsHarrier1 = new HashMap<>();
        String[] harrierFood = {"rodents", "fish", "eagles", "plants", "A"};
        QandAsHarrier1.put("What do Northern Harriers eat?", harrierFood);
        factsAndQuestions.put("Northern Harriers, eat rodents", QandAsHarrier1);

        HashMap<String, String[]> QandAsHarrier2 = new HashMap<>();
        String[] harrierMigrate = { "They migrate to California","They migrate to South America", "They don't migrate", "They migrate to canada", "C"};
        QandAsHarrier2.put("Where do Harriers migrate?", harrierMigrate);
        factsAndQuestions.put("Northern Harriers are, non-migratory birds", QandAsHarrier2);

        HashMap<String, String[]> QandAsHarrier3 = new HashMap<>();
        String[] harrierPred = {"Foxes", "Snakes", "Cats", "Humans", "A"};
        QandAsHarrier3.put("What is a major predator of Northern Harriers", harrierPred);
        factsAndQuestions.put("Foxes are a predator, for Northern Harriers", QandAsHarrier3);

        availableFacts = new ArrayList<String>(factsAndQuestions.keySet());

    }

    public void generateOspreyQuestions() {
        currentFactIndex = 0;
        facts = new String[]{"Ospreys like to,eat Snakes and Fish", "Ospreys migrate to, South America for, the winter", "Eagles are a, predator of Ospreys"};
        factsAndQuestions = new HashMap<>();
        HashMap<String, String[]> QandAsOsprey1 = new HashMap<>();
        String[] OspreyFood = {"Mice and Rabbits", "Snakes and Fish", "Raccoons", "Eagles","B"};
        QandAsOsprey1.put("What do Ospreys eat?", OspreyFood);
        factsAndQuestions.put("Ospreys like to,eat Snakes and Fish", QandAsOsprey1);

        HashMap<String, String[]> QandAsOsprey2 = new HashMap<>();
        String[] OspreyMigrate = {"They migrate to South America", "They migrate to California", "They don't migrate", "They migrate to canada", "A"};
        QandAsOsprey2.put("Where do Ospreys migrate for winter?", OspreyMigrate);
        factsAndQuestions.put("Ospreys migrate to, South America for, the winter", QandAsOsprey2);

        HashMap<String, String[]> QandAsOsprey3 = new HashMap<>();
        String[] OspreyPred = {"Foxes", "Snakes", "Eagles", "Cats and Dogs", "C"};
        QandAsOsprey3.put("What is a major predator of Ospreys?", OspreyPred);
        factsAndQuestions.put("Eagles are a, predator of Ospreys", QandAsOsprey3);
        availableFacts = new ArrayList<String>(factsAndQuestions.keySet());

    }

    public void generateOspreyQuestions2() {
    	currentFactIndex = 0;
    	facts = new String[] {"Ospreys nest in, North America","Ospreys nest in, trees","Airplanes are a,threat to Ospreys"};
        factsAndQuestions = new HashMap<>();
        HashMap<String, String[]> QandAsOsprey1 = new HashMap<>();
        String[] OspreyNest = {"South America", "Asia", "Antartica", "North America","D"};
        QandAsOsprey1.put("Where do Ospreys nest?", OspreyNest);
        factsAndQuestions.put("Ospreys nest in, North America", QandAsOsprey1);

        HashMap<String, String[]> QandAsOsprey2 = new HashMap<>();
        String[] OspreyLoc = {"on the floor", "in trees", "on mountains", "in caves", "B"};
        QandAsOsprey2.put("Where do Ospreys nest?", OspreyLoc);
        factsAndQuestions.put("Ospreys nest in, trees", QandAsOsprey2);

        HashMap<String, String[]> QandAsOsprey3 = new HashMap<>();
        String[] OspreyThreat = {"Airplanes", "Hunters", "Radio waves","Drones", "A"};
        QandAsOsprey3.put("Which is a common threat to Ospreys", OspreyThreat);
        factsAndQuestions.put("Airplanes are a,threat to Ospreys", QandAsOsprey3);
        availableFacts = new ArrayList<String>(factsAndQuestions.keySet());

    }

    public static boolean hasMoreFacts() {
        return currentFactIndex < facts.length;
    }

    public static void setCorrectAnswer(String answer) {
        correctAnswer = answer;
    }

    public static String getCorrectAnswer() {
        return correctAnswer;
    }

    public static void incrQuestionNum() {
        questionNum++;
    }

    public static int getQuestionNum() {
        return questionNum;
    }

    public static void setNumberOfQuestions(int x) {
        numberOfQuestions = x;
    }

    public static boolean quizOver() {
        return questionNum > numberOfQuestions;
    }

    public static boolean lastQuestion() {
        return questionNum == numberOfQuestions;
    }

    public static int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public static void resetQuestionNum() {
        questionNum = 0;
    }
    
    public void resetModel(){
        round = 0;
        questionNum = 0;
        clearFactsAndQuestions();
        clearQuestionsToAsk();
        clearGP();
        totalLevelTicks = 0;
        currentFactIndex = 0;
        //player.setHealth(250);
        player.resetPlayer();
        indexOfGP = 0;
        currentGPs.clear();
        specialFoodEaten = false;
        availableFacts.clear();
        System.out.println("resetModel reached");
    }
    
public static void updateNumberOfQuestions() {
	//numberOfQuestions = x;
	if (Model.getQuestionToAsk().equals(null)){
		numberOfQuestions = -1;
	}
	else {
		numberOfQuestions = getQuestionToAsk().size() - 1;
	}
}

public static boolean isQuiz1Done() {
	if (numberOfQuestions == -1) {
		View.set1To2Transition(true);
		return true;
	}
		else {
			return quiz1Done;
		}
}
public static void setIsQuiz1Done(boolean b) {
	quiz1Done = b;
}

public static boolean isQuiz2Done() {
	if (numberOfQuestions == -1) {
		View.set2To3Transition(true);
		return true;
	}
		else {
			return quiz2Done;
		}
}

public static void setIsQuiz2Done(boolean b) {
	quiz2Done = b;
	
}

public static boolean isQuiz3Done() {
	if (getNumberOfQuestions() == -1) {
		return true;
	}
	else {
	return quiz3Done;
	}
}
public static void setIsQuiz3Done(boolean b) {
	quiz3Done = b;
}
}
