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
import java.io.Serializable;

/**
 * Model contains and deals with the basic logic of the game, including updating
 * an object's location and direction, handling collisions, spawning obstacles,
 * and player progress.
 *
 * @author crnis
 */
public class Model implements Serializable {

    final static int TOTAL_OBSTACLES_PER_LEVEL = 30;
    final static int MAX_BIRD_HEALTH = 250;
    final static int MAX_SPECIAL_FOOD = 3;
    final static double CHANCE_SPECIAL_FOOD_SPAWNS = 0.2;
    final static double CHANCE_FOOD_SPAWNS_INSTEAD_OF_ENEMY = 0.5;
    final static double EQUAL_CHANCE_BETWEEN_2 = 0.5;
    final static int X_LOCATION_WHERE_GPS_ARE_NO_LONGER_CURRENT = -500;
    final static int X_INCREASE_AT_END_OF_LEVEL = 30;
    final static int X_LOCATION_FOR_OBSTACLE_SPAWNS = 1200;
    final static int ADDITIONAL_X_LOCATION_FOR_SPECIAL_FOOD = 400;

    public static HashMap<String, HashMap<String, String[]>> factsAndQuestions;
    public static HashMap<String, String[]> questionsToAsk = new HashMap<>();
    private static Sprite bird = Sprite.OSPREY; 
    private static int round;
    private static int questionNum;
    private static int numberOfQuestions;
    protected int fWidth;
    protected int fHeight;
    private int imgHeight;
    private int imgWidth;
    protected int groundLevel;
    private static String correctAnswer;
    private int sceneNum;
    protected ArrayList<GamePiece> gamePieces = new ArrayList<>();

    static String[] facts;
    private int totalLevelTicks;
    private static int currentFactIndex = 0;
    private static Direction direction;
    private static Player player;
    //GamePiece currentGP;
    private int indexOfGP;
    private static String currentFact;
    private CopyOnWriteArrayList<GamePiece> currentGPs = new CopyOnWriteArrayList<>();
    private int numGamePiecesInRoundLeft = TOTAL_OBSTACLES_PER_LEVEL;
    private GamePiece furthestGP = new GamePiece();
    private static boolean specialFoodEaten = false;
    private static boolean foodHit = false;
    private static boolean enemyHit = false;
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
        fWidth = fwidth;
        fHeight = fheight;
        setImgWidth(imageWidth);
        setImgHeight(imageHeight);
        player = new Player();
        setGroundLevel(fHeight - imgHeight);
        setIndexOfGP(0);
        indexOfGP = 0;

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
            if (player.getY() > 0) {      
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

        totalLevelTicks++;
    }

    /**
     * endOfLevel() increases the player's x velocity when the level ends until the player goes off the screen
     */
    public void endOfLevel() {
        player.setXIncr(X_INCREASE_AT_END_OF_LEVEL);
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
    
    /**
     * getAvailableFacts returns the facts available to the player
     * 
     * @return ArrayList<String> availableFacts
     */
    public static ArrayList<String> getAvaliableFacts() {
        return availableFacts;
    }
    
    /**
     * spawnHarrierGamePieces() will randomly generate a food, enemy, or special food of a random type for the player
     * these obstacles will match those for a harrier, and include Bunnies, Mice, Foxes, and Raccoons.
     * 
     */
    public void spawnHarrierGamePieces() {
        int numGamePieces = 0;
        int numSpecialFood = 0;
        questionsToAsk = new HashMap<String, String[]>();
        int tempXLoc = X_LOCATION_FOR_OBSTACLE_SPAWNS;
        int tempXSpecial = 0;
        int maxSpecialFood = MAX_SPECIAL_FOOD;
        while (numGamePieces < numGamePiecesInRoundLeft) {
            if (numSpecialFood < maxSpecialFood) {
                if (Math.random() < CHANCE_SPECIAL_FOOD_SPAWNS) {
                    if (Math.random() < EQUAL_CHANCE_BETWEEN_2) {
                        gamePieces.add(new SpecialFood(tempXLoc + ADDITIONAL_X_LOCATION_FOR_SPECIAL_FOOD + tempXSpecial, (int) (Math.random() * groundLevel), Sprite.BUNNY));
                        tempXSpecial += fWidth;
                    } else {
                        gamePieces.add(new SpecialFood(tempXLoc + ADDITIONAL_X_LOCATION_FOR_SPECIAL_FOOD + tempXSpecial, (int) (Math.random() * groundLevel), Sprite.MOUSE));
                        tempXSpecial += fWidth;
                    }
                    numSpecialFood++;

                }
            }

            if (Math.random() < CHANCE_FOOD_SPAWNS_INSTEAD_OF_ENEMY) {
                if (Math.random() < EQUAL_CHANCE_BETWEEN_2) {
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.BUNNY));
                } else {
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.MOUSE));
                }
            } else {
                if (Math.random() < EQUAL_CHANCE_BETWEEN_2) {
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

    /**
     * spawnOspreyGamePieces() will randomly generate a food, enemy, or special food of a random type for the player
     * these obstacles will match those for a osprey, and include Snakes, Fish, Eagles, and Planes.
     * 
     */
    public void spawnOspreyGamePieces() {
        questionsToAsk = new HashMap<String, String[]>();
        int numGamePieces = 0;
        int numSpecialFood = 0;
        int tempXSpecial = 0;
        int tempXLoc = X_LOCATION_FOR_OBSTACLE_SPAWNS;
        int maxSpecialFood = MAX_SPECIAL_FOOD;
        while (numGamePieces < numGamePiecesInRoundLeft) {
            if (numSpecialFood < maxSpecialFood) {
                if (Math.random() < CHANCE_SPECIAL_FOOD_SPAWNS) {
                    if (Math.random() < EQUAL_CHANCE_BETWEEN_2) {
                        gamePieces.add(new SpecialFood(tempXLoc + ADDITIONAL_X_LOCATION_FOR_SPECIAL_FOOD + tempXSpecial, (int) (Math.random() * groundLevel), Sprite.SNAKE));
                        tempXSpecial += fWidth;
                    } else {
                        gamePieces.add(new SpecialFood(tempXLoc + ADDITIONAL_X_LOCATION_FOR_SPECIAL_FOOD + tempXSpecial, (int) (Math.random() * groundLevel), Sprite.FISH));
                        tempXSpecial += fWidth;

                    }
                    numSpecialFood++;
                }
            }
            if (Math.random() < CHANCE_FOOD_SPAWNS_INSTEAD_OF_ENEMY) {
                if (Math.random() < EQUAL_CHANCE_BETWEEN_2) {
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.SNAKE));
                } else {
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random() * groundLevel), Sprite.FISH));
                }
            } else {
                if (Math.random() < EQUAL_CHANCE_BETWEEN_2) {
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random() * groundLevel), Sprite.EAGLE));
                } else {
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random() * groundLevel), Sprite.PLANE));
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

    /**
     * clearCurrentGP() removes all GamePieces currently being tracked by currentGPs
     */
    public void clearCurrentGP() {
        currentGPs.clear();
    }

    /**
     * clearGP() removes all GamePieces, including those not being tracked by currentGPs.
     */
    public void clearGP() {
        gamePieces.clear();
    }

    /**
     * seeCurrentGP() runs through all of the gamePieces and adds the ones that are on the screen
     */
    public void seeCurrentGP() {
        for (GamePiece g : gamePieces) {
            if (g.getX() <= fWidth && g.getX() >= X_LOCATION_WHERE_GPS_ARE_NO_LONGER_CURRENT) {
                currentGPs.add(g);
            }
        }
    }

    /**
     * clearQuestionsToAsk() removes any questions stored in questionsToAsk
     */
    void clearQuestionsToAsk() {
        questionsToAsk.clear();
    }

    /**
     * clearFactsAndQuestions() removes any facts and questions stored in factsAndQuestions
     */
    void clearFactsAndQuestions() {
        factsAndQuestions.clear();
    }

    /**
     * eat() will increment the player's score based off of what is eaten.
     * 
     * @param Food f is the food being consumed by the player
     */
    public void eat(Food f) {
        foodHit = true;
        View.setMomentFoodEaten(View.getFrameCount());
        player.setScore(player.getScore() + f.getFoodValue());
        if (player.getHealth() > MAX_BIRD_HEALTH - f.getFoodValue()) {
            player.setHealth(MAX_BIRD_HEALTH);
        } else {
            player.setHealth(player.getHealth() + f.getFoodValue());
        }
    }

    /**
     * eatSpecial takes one parameter, and adds a fact to be displayed on the screen after a specialfood is eaten
     * 
     * @param SpecialFood sf is the special food that is consumed by the player
     */
    public void eatSpecial(SpecialFood sf) {
        specialFoodEaten = true;
        View.setMomentEaten(View.getFrameCount());
        if (hasMoreFacts()) {
            currentFact = facts[currentFactIndex];
            HashMap<String, String[]> associatedFactandQuestion = factsAndQuestions.get(currentFact);
            System.out.println(associatedFactandQuestion);
            questionsToAsk.putAll(associatedFactandQuestion);
        }
        player.setScore(player.getScore() + sf.getFoodValue());
        player.setHealth(MAX_BIRD_HEALTH);
    }

    /**
     * getQuestionToAsk returns the question/answer pairing to be used next
     * 
     * @return HashMap<String, String[]> questionsToAsk is the next Q/A pairing
     */
    public static HashMap<String, String[]> getQuestionToAsk() {
        return questionsToAsk;
    }

    /**
     * specialFoodEaten returns a true or false for if a specialfood has just been eaten
     * 
     * @return boolean specialFoodEaten is a boolean that represents if a specialFood has been eaten
     */
    public static boolean specialFoodEaten() {
        return specialFoodEaten;
    }

    /**
     * setSpecialFoodEaten sets the current state of if a specialFood has been eaten
     * 
     * @param boolean bool is a true or false to represent if a specialFood has been eaten recently
     */
    public static void setSpecialFoodEaten(boolean bool) {
        specialFoodEaten = bool;
    }

    /**
     * obstacleHit() will handle the event of the player colliding with an enemy and taking damage
     * 
     * @param Enemy e is the enemy that has collided with the player
     */
    public void obstacleHit(Enemy e) {
        enemyHit = true;
        View.setMomentEnemyHit(View.getFrameCount());
        player.setScore(player.getScore() - e.getDamage());
        if (player.getHealth() < e.getDamage()) {
            player.setHealth(0);
        } else {
            player.setHealth(player.getHealth() - e.getDamage());
        }
    }

    /**
     * getImgHeight returns the height of the image
     * 
     * @return int imgHeight is the height of the image
     */
    public int getImgHeight() {
        return imgHeight;
    }

    /**
     * getplayer returns the current game player object
     * 
     * @return Player player is the object for the current Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * getCurrentGPs returns the gamePieces on the screen
     * 
     * @return CopyOnWriteArrayList<GamePiece> currentGPs is the ArrayList of GamePieces on the screen
     * 
     * this method uses a COpyOnWriteArrayList to avoid concurrent modification errors
     */
    public CopyOnWriteArrayList<GamePiece> getCurrentGPs() {
        return currentGPs;
    }

    /**
     * getGamePieces() returns all of the gamepieces that have been spawned
     * 
     * @return ArrayList<GamePiece> gamePieces is the arraylist of all gamepieces/obstacles in the game.
     */
    public ArrayList<GamePiece> getGamePieces() {
        return gamePieces;
    }

    /**
     * getDirection returns the direction the player is currently going
     * 
     * @return Direction direction is the direction (UP or DOWN) of the player
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * setDirection takes one parameter and sets the direction the bird/player is moving
     * 
     * @param Direction direction is the direction the player is moving (UP or DOWN)
     */
    public static void setDirection(Direction Direction) {
        direction = Direction;
    }

    /**
     * setImgHeight takes one paramater and sets the Model's imageheight
     * 
     * @param int imgHeight is the integer height of the image.
     */
    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    /**
     * getImgWidth returns the width of the model's image
     * 
     * @return int imgWidth is the width of the image
     */
    public int getImgWidth() {
        return imgWidth;
    }

    /**
     * setImgWidth takes one parameter and sets the width of the model's image
     * 
     * @param int imgWidth is the width of the image
     */
    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    /**
     * getGroundLevel returns the ground level coordinate
     * 
     * @return int groundLevel is the y coordinate of the ground
     */
    public int getGroundLevel() {
        return groundLevel;
    }

    /**
     * setGroundLevel takes one parameter and sets the coordinate of the ground
     * 
     * @param int groundLevel is the y coordinate that the ground is being set to
     */
    public void setGroundLevel(int groundLevel) {
        this.groundLevel = groundLevel;
    }

    /**
     * getSceneNum returns the number of the scene
     * 
     * @return int sceneNum is the number of the current scene
     */
    public int getSceneNum() {
        return sceneNum;
    }

    /**
     * setSceneNum takes one parameter and sets the number of the scene
     * 
     * @param int sceneNum is the number of the current scene
     */
    public void setSceneNum(int sceneNum) {
        this.sceneNum = sceneNum;
    }

    /**
     * getTotalLevelTicks returns the total level ticks that have passed
     * 
     * @return int totalLevelTicks is the number of ticks/updates that have passed in the game so far
     */
    public int getTotalLevelTicks() {
        return totalLevelTicks;
    }

    /**
     * setTotalLevelTicks takes one parameter and sets the total level ticks that have passed
     * 
     * @param int totalLevelTicks is the number of ticks/updates that have passed in the game so far
     */
    public void setTotalLevelTicks(int totalLevelTicks) {
        this.totalLevelTicks = totalLevelTicks;
    }
    
    /**
     * getIndexOfGP returns the index in gamePieces of the current GamePiece
     * 
     * @return int indexOfGP is the index in gamePieces of the current GamePiece
     */
    public int getIndexOfGP() {
        return indexOfGP;
    }
    
    /**
     * setIndexOfGP takes one parameter and sets the index in gamePieces of the current GamePiece
     * 
     * @param int indexOfGP is the index in gamePieces of the current Gamepiece.
     */
    public void setIndexOfGP(int indexOfGP) {
        this.indexOfGP = indexOfGP;
    }

    /**
     * setPlayer takes one parameter and sets the player in the model to a give player
     * 
     * @param Player p is the player to be used in the current game
     */
    public void setPlayer(Player p) {
        this.player = p;
    }

    /**
     * setFHeight takes one parameter and sets the FrameHeight
     * 
     * @param int h is the height of the Frame in the Model
     */
    public void setFHeight(int h) {
        this.fHeight = h;
    }

    /**
     * incrFactIndex increases the index of the currentFact array by 1
     */
    public static void incrFactIndex() {
        currentFactIndex++;
    }

    /**
     * setFurthestGP takes one parameter and sets the furthestGP equal to it
     * 
     * @param GamePiece gP is the GamePiece that is being set to the furthest one from the player
     */
    public void setFurthestGP(GamePiece gP) {
        this.furthestGP = gP;
    }

    /**
     * getBird returns the sprite of the bird in model
     * 
     * @return Sprite bird is the Sprite of the bird being played as in Model
     */
    public static Sprite getBird() {
        return bird;
    }

    /**
     * setBird takes one parameter, the Sprite of the bird being played as in Model
     * 
     * @param Sprite bird is the Sprite of the bird being played as in Model
     */
    public static void setBird(Sprite b) {
        bird = b;
    }

    /**
     * getCurrentFact returns the fact from the facts array that currentFactIndex is pointing to
     * 
     * @return String facts[currentFactIndex] is the string of the currentFact
     */
    public static String getCurrentFact() {
        return facts[currentFactIndex];
    }

    /**
     * setRound takes one parameter, and sets the round equal to the integer passed in
     * 
     * @param int r is the round of the model
     */
    public void setRound(int r) {
        round = r;
    }

    /**
     * getRound returns the value of the round currently being played in the model
     * 
     * @return int round is the round currently being played
     */
    public int getRound() {
        return round;
    }

    /**
     * generateHarrierQuestions takes the different questions that will be asked of a player who plays as a Northern Harrier and places them in an Hashmap with answers
     * the questions are then put into an arraylist to be chosen from
     */
    public void generateHarrierQuestions() {
        currentFactIndex = 0;
        facts = new String[]{"Northern Harriers, eat rodents", "Northern Harriers, are non-migratory,   birds", "Foxes are a, predator for, Northern Harriers"};
        factsAndQuestions = new HashMap<>();
        HashMap<String, String[]> QandAsHarrier1 = new HashMap<>();
        String[] harrierFood = {"Rodents", "Fish", "Eagles", "Plants", "A"};
        QandAsHarrier1.put("What do Northern Harriers eat?", harrierFood);
        factsAndQuestions.put("Northern Harriers, eat rodents", QandAsHarrier1);

        HashMap<String, String[]> QandAsHarrier2 = new HashMap<>();
        String[] harrierMigrate = {"They migrate to California", "They migrate to South America", "They don't migrate", "They migrate to canada", "C"};
        QandAsHarrier2.put("Where do Harriers migrate?", harrierMigrate);
        factsAndQuestions.put("Northern Harriers, are non-migratory,   birds", QandAsHarrier2);

        HashMap<String, String[]> QandAsHarrier3 = new HashMap<>();
        String[] harrierPred = {"Foxes", "Snakes", "Cats", "Humans", "A"};
        QandAsHarrier3.put("What is a predator of Northern Harriers?", harrierPred);
        factsAndQuestions.put("Foxes are a, predator for, Northern Harriers", QandAsHarrier3);

        availableFacts = new ArrayList<String>(factsAndQuestions.keySet());

    }

    /**
	 * generateOspreyQuestions takes the different questions that will be asked of a player who plays as a Osprey in the first stage and places them in an Hashmap with answers
     * the questions are then put into an arraylist to be chosen from
     */
    public void generateOspreyQuestions() {
        currentFactIndex = 0;
        facts = new String[]{"Ospreys like to,eat Snakes and Fish", "Ospreys migrate to, South America for, the winter", "Eagles are a, predator of,   Ospreys"};
        factsAndQuestions = new HashMap<>();
        HashMap<String, String[]> QandAsOsprey1 = new HashMap<>();
        String[] OspreyFood = {"Mice and Rabbits", "Snakes and Fish", "Raccoons", "Eagles", "B"};
        QandAsOsprey1.put("What do Ospreys eat?", OspreyFood);
        factsAndQuestions.put("Ospreys like to,eat Snakes and Fish", QandAsOsprey1);

        HashMap<String, String[]> QandAsOsprey2 = new HashMap<>();
        String[] OspreyMigrate = {"They migrate to South America", "They migrate to California", "They don't migrate", "They migrate to canada", "A"};
        QandAsOsprey2.put("Where do Ospreys migrate for winter?", OspreyMigrate);
        factsAndQuestions.put("Ospreys migrate to, South America for, the winter", QandAsOsprey2);

        HashMap<String, String[]> QandAsOsprey3 = new HashMap<>();
        String[] OspreyPred = {"Foxes", "Snakes", "Eagles", "Cats and Dogs", "C"};
        QandAsOsprey3.put("What is a predator of Ospreys?", OspreyPred);
        factsAndQuestions.put("Eagles are a, predator of,   Ospreys", QandAsOsprey3);
        availableFacts = new ArrayList<String>(factsAndQuestions.keySet());

    }

    /**
	 * generateOspreyQuestions takes the different questions that will be asked of a player who plays as a Osprey in the second stage and places them in an Hashmap with answers
     * the questions are then put into an arraylist to be chosen from
     */
    public void generateOspreyQuestions2() {
        currentFactIndex = 0;
        facts = new String[]{"Ospreys nest in, North America", "Ospreys nest in, trees", "Airplanes are a,threat to Ospreys"};
        factsAndQuestions = new HashMap<>();
        HashMap<String, String[]> QandAsOsprey1 = new HashMap<>();
        String[] OspreyNest = {"South America", "Asia", "Antartica", "North America", "D"};
        QandAsOsprey1.put("Where do Ospreys nest?", OspreyNest);
        factsAndQuestions.put("Ospreys nest in, North America", QandAsOsprey1);

        HashMap<String, String[]> QandAsOsprey2 = new HashMap<>();
        String[] OspreyLoc = {"on the floor", "in trees", "on mountains", "in caves", "B"};
        QandAsOsprey2.put("Where do Ospreys make nests?", OspreyLoc);
        factsAndQuestions.put("Ospreys nest in, trees", QandAsOsprey2);

        HashMap<String, String[]> QandAsOsprey3 = new HashMap<>();
        String[] OspreyThreat = {"Airplanes", "Hunters", "Radio waves", "Drones", "A"};
        QandAsOsprey3.put("Which is a common threat to Ospreys?", OspreyThreat);
        factsAndQuestions.put("Airplanes are a,threat to Ospreys", QandAsOsprey3);
        availableFacts = new ArrayList<String>(factsAndQuestions.keySet());

    }

    /**
     * hasMoreFacts returns a true or false for if there are more facts left to give
     * 
     * @return boolean (currentFactIndex < facts.length) is the boolean true or false for if all of the facts have already been given.
     */
    public static boolean hasMoreFacts() {
        return currentFactIndex < facts.length;
    }

    /**
     * setCorrectAnswer takes one parameter and sets the correctAnswer to a question to the String passed in
     * 
     * @param String answer is the correct answer for the current question in Model
     */
    public static void setCorrectAnswer(String answer) {
        correctAnswer = answer;
    }

    /**
     * getCorrectAnswer returns the correctAnswer for the current question in Model
     * 
     * @return String correctAnswer is the correctAnswer for the current question in Model
     */
    public static String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * incrQuestionNum increases the questionNum by 1 to move to the next question
     */
    public static void incrQuestionNum() {
        questionNum++;
    }

    /**
     * getQuestionNum returns the number of the current question
     * 
     * @return int questionNum is the number of the current question
     */
    public static int getQuestionNum() {
        return questionNum;
    }

    /**
     * setNumberOfQuestions takes one parameter and sets the maximum number of questions to be asked
     * 
     * @param int x is the number of questions that there will be
     */
    public static void setNumberOfQuestions(int x) {
        numberOfQuestions = x;
    }

    /**
     * quizOver returns the true or false for if the quiz has been completes
     * 
     * @return boolean (questionNum > numberOfQuestions) is the boolean true or false for if the quiz has been completed by the player
     */
    public static boolean quizOver() {
        return questionNum > numberOfQuestions;
    }

    /**
     * lastQuestion returns the true or false for if the current question is the final question
     * 
     * @return boolean (questionNum == numberOfQuestions) is the true/false boolean for if the quiz is on its final question
     */
    public static boolean lastQuestion() {
        return questionNum == numberOfQuestions;
    }

    /**
     * getNumberOfQuestions returns the number of questions that can be asked
     * 
     * @return int numberOfQuestions is the number of questions to ask
     */
    public static int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    /**
     * resetQuestionNum sets the questionNum to its initial value, 0
     */
    public static void resetQuestionNum() {
        questionNum = 0;
    }

    /**
     * resetModel sets the different values that change in Model to their initial values.
     */
    public void resetModel() {
        round = 0;
        enemyHit = false;
        foodHit = false;
        questionNum = 0;
        clearFactsAndQuestions();
        clearQuestionsToAsk();
        clearGP();
        setIsQuiz1Done(false);
        setIsQuiz2Done(false);
        setIsQuiz3Done(false);
        totalLevelTicks = 0;
        currentFactIndex = 0;
        player.resetPlayer();
        indexOfGP = 0;
        currentGPs.clear();
        specialFoodEaten = false;
        availableFacts.clear();
    }

    /**
     * updateNumberOfQuestions sets numberOfQuestions equal to one less than the size of the possible questions to ask
     */
    public static void updateNumberOfQuestions() {
            numberOfQuestions = getQuestionToAsk().size() - 1;
    }

    /**
     * isQuiz1Done returns if the quiz is complete or if there are no questions
     * 
     * @return boolean quiz1Done is the boolean for if the quiz is complete, the return value will also be true if the number of questions was never initialized beyond -1
     */
    public static boolean isQuiz1Done() {
        if (numberOfQuestions == -1) {
            View.set1To2Transition(true);
            return true;
        } else {
            return quiz1Done;
        }
    }

    /**
     * setIsQuiz1Done takes one parameter and sets quiz1Done to the parameter
     * 
     * @param boolean b is the boolean true/false of if the quiz1 has been completed
     */
    public static void setIsQuiz1Done(boolean b) {
        quiz1Done = b;
    }

    /**
     * isQuiz2Done returns if the quiz is complete or if there are no questions
     * 
     * @return boolean quiz2Done is the boolean for if the quiz is complete, the return value will also be true if the number of questions was never initialized beyond -1
     */
    public static boolean isQuiz2Done() {
        if (numberOfQuestions == -1) {
            View.set2To3Transition(true);
            return true;
        } else {
            return quiz2Done;
        }
    }

    /**
     * setIsQuiz2Done takes one parameter and sets quiz1Done to the parameter
     * 
     * @param boolean b is the boolean true/false of if the quiz2 has been completed
     */
    public static void setIsQuiz2Done(boolean b) {
        quiz2Done = b;

    }

    /**
     * isQuiz3Done returns if the quiz is complete or if there are no questions
     * 
     * @return boolean quiz3Done is the boolean for if the quiz is complete, the return value will also be true if the number of questions was never initialized beyond -1
     */
    public static boolean isQuiz3Done() {
        if (getNumberOfQuestions() == -1) {
            return true;
        } else {
            return quiz3Done;
        }
    }

    /**
     * setIsQuiz3Done takes one parameter and sets quiz1Done to the parameter
     * 
     * @param boolean b is the boolean true/false of if the quiz3 has been completed
     */
    public static void setIsQuiz3Done(boolean b) {
        quiz3Done = b;
    }

    /**
     * enemyHit returns the true/false for if an enemy has been hit
     * 
     * @return boolean enemyHit is the boolean true/false for if the player has hit an enemy
     */
    public static boolean enemyHit() {
        return enemyHit;
    }
    
    /**
     * foodHit returns the true/false for if a food has been hit
     * 
     * @return boolean foodHit is the boolean true/false for if the player has hit an food
     */
    public static boolean foodHit() {
        return foodHit;
    }

    /**
     * setFoodHit takes one parameter and sets the true/false for if a food has been hit
     * 
     * @param boolean b is the true/false for if a food has been hit
     */
    public static void setFoodHit(boolean b) {
        foodHit = b;
    }

    /**
     * setEnemyHit takes one parameter and sets the true/false for if a enemy has been hit
     * 
     * @param boolean b is the true/false for if an enemy has been hit
     */
    public static void setEnemyHit(boolean b) {
        enemyHit = b;
    }
}
