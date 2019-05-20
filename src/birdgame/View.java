/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.awt.AlphaComposite;
import java.awt.CardLayout;

/**
 *
 * @author crnis
 */
public class View extends JPanel implements Serializable {

    static Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    final static int FRAME_WIDTH = (int) rect.getWidth();
    final static int FRAME_HEIGHT = (int) rect.getHeight();
    final static int BIRD_WIDTH = 184;
    final static int BIRD_HEIGHT = 165;
    final static int FRAME_COUNT = 6;
    final static int MILLISECONDS_PER_SECOND = 1000;
    final static int FRAMES_PER_SECOND = 40;
    final static int SPECIAL_FOOD_DELAY = 100;
    final static Color YELLOW = new Color(255, 255, 0, 100);
    final static Color SNAKEYELLOW = new Color(255, 255, 0, 175);
    final static int DELAY_FOR_ANIMATION_UPDATES = 10;
    final static int BACKGROUND_LOCATION_X_INCREASE = 8;
    final static int MAX_NUM_BACKGROUNDS = 200;
    final static int SECONDS_PER_MAP_FRAME = 1;
    final static int MAX_BIRD_HEALTH = Model.MAX_BIRD_HEALTH;
    final static int NEST_DELAY_MILLISECONDS = 50;

    final static int THOUGHT_BUBBLE_SIZE = 400;
    final static int DEFAULT_BUTTON_FONT_SIZE_RATIO = 55;
    final static int DEFAULT_BUTTON_FRAMEWIDTH_RATIO = 10;
    final static int DEFAULT_BUTTON_FRAMEHEIGHT_RATIO = 100;
    final static int DEFAULT_BUTTON_Y_MULTIPLIER = 84;
    final static int HARRIER_BUTTON_Y_MULTIPLIER = 37;
    final static int HARRIER_BUTTON_X_MULTIPLIER = 6;
    final static int DEFAULT_BUTTON_X_LOCATION_RATIO = 4;
    final static int DEFAULT_BUTTON_Y_LOCATION_RATIO = 15;
    final static int MICE_FRAME_COUNT = 2;
    final static int BUNNY_FRAME_COUNT = 4;
    final static int RED_FOX_FRAME_COUNT = 4;
    final static int RACCOON_FRAME_COUNT = 4;
    final static int FISH_FRAME_COUNT = 4;
    final static int SNAKE_FRAME_COUNT = 4;
    final static int EAGLE_FRAME_COUNT = 6;
    final static int PLANE_FRAME_COUNT = 1;
    final static int MAP_1_2_TRANSITION_COUNT = 6;
    final static int MAP_2_3_TRANSITION_COUNT = 8;
    final static int DE_FRAME_COUNT = 33;
    final static int OSPREY_NEST_COUNT = 17;
    final static int HARRIER_NEST_COUNT = 17;
    final static int TUTORIAL_BUTTON_X_RATIO = 3;
    final static int TUTORIAL_BUTTON_Y_MULTIPLIER = 4;
    final static int TUTORIAL_BUTTON_Y_RATIO = 10;
    final static int TUTORIAL_BUTTON_Y_LOCATION_RATIO = 14;
    final static int TUTORIAL_LABEL_FONT_SIZE_RATIO = 30; 
    final static int TUTORIAL_LABEL_FONT_STYLE = 1;
    final static int TUTORIAL_UP_DOWN_LABEL_X_RATIO = 50;
    final static int TUTORIAL_FOOD_ENEMY_LABEL_X_RATIO = 12;
    final static int TUTORIAL_FOOD_ENEMY_LABEL_X_MULTIPLIER = 5;
    final static int TUTORIAL_LABEL_Y_RATIO = 9; //8
    final static int TUTORIAL_LABEL_DOWN_Y_RATIO = 4;
    final static int TUTORIAL_BUTTON_X_LOC_NUMERATOR = 55;
    final static int TUTORIAL_BUTTON_Y_LOC_NUMERATOR = 65;
    final static int ROUND_BUTTON_X_MULTIPLIER = 7;
    final static int FINALSCORE_DISPLAY_FONT_SIZE_RATIO = 25;
    final static int FINALSCORE_DISPLAY_X_MULTIPLIER = 4;
    final static int FINALSCORE_DISPLAY_Y_MULTIPLIER = 2;
    final static int FINALSCORE_DISPLAY_Y_RATIO = 10;

    final static int GRID_WIDTH = 3;
    final static int GRID_HEIGHT = 2;
    final static int QUIZ_FONT_STYLE = 1;
    final static int QUESTION_FONT_SIZE_RATIO = 40;
    final static int BLANK_FONT_SIZE_RATIO = 40;
    final static int QUIZ_DIMENSION_PREFERRED_X_RATIO = 3;
    final static int QUIZ_DIMENSION_PREFERRED_Y_RATIO = 4;

    private static int runningFrameCount = 0;
//changed runningframeCount to static, might be a problem
    private int ticksPerFrameUpdate = FRAMES_PER_SECOND / DELAY_FOR_ANIMATION_UPDATES;

    private int harrierPicNum = 0;
    private int ospreyPicNum = 0;

    private int micePicNum = 0;
    private int bunnyPicNum = 0;
    private int redFoxPicNum = 0;
    private int raccoonPicNum = 0;
    private int fishPicNum = 0;
    private int snakePicNum = 0;
    private int eaglePicNum = 0;
    private int planePicNum = 0;
    private int dePicNum = 0;
    private int playerXLoc = 0;
    private int playerYLoc = 0;
    private int map1To2TransitionPicNum = 0;
    private int map2To3TransitionPicNum = 0;
    private int ospreyNestPicNum = 0;
    private int harrierNestPicNum = 0;
    private BufferedImage[] harrierFly = new BufferedImage[FRAME_COUNT];
    private BufferedImage[] harrierRed = new BufferedImage[FRAME_COUNT];
    private BufferedImage[] harrierGreen = new BufferedImage[FRAME_COUNT];
    private BufferedImage[] ospreyFly = new BufferedImage[FRAME_COUNT];
    private BufferedImage[] ospreyRed = new BufferedImage[FRAME_COUNT];
    private BufferedImage[] ospreyGreen = new BufferedImage[FRAME_COUNT];
    private BufferedImage[] mice = new BufferedImage[MICE_FRAME_COUNT];
    private BufferedImage[] specialMice = new BufferedImage[MICE_FRAME_COUNT];
    private BufferedImage[] bunny = new BufferedImage[BUNNY_FRAME_COUNT];
    private BufferedImage[] specialBunny = new BufferedImage[BUNNY_FRAME_COUNT];
    private BufferedImage[] redFox = new BufferedImage[RED_FOX_FRAME_COUNT];
    private BufferedImage[] raccoon = new BufferedImage[RACCOON_FRAME_COUNT];
    private BufferedImage[] fish = new BufferedImage[FISH_FRAME_COUNT];
    private BufferedImage[] specialFish = new BufferedImage[FISH_FRAME_COUNT];
    private BufferedImage[] snake = new BufferedImage[SNAKE_FRAME_COUNT];
    private BufferedImage[] specialSnake = new BufferedImage[SNAKE_FRAME_COUNT];
    private BufferedImage[] plane = new BufferedImage[PLANE_FRAME_COUNT];
    private BufferedImage[] eagle = new BufferedImage[EAGLE_FRAME_COUNT];
    private BufferedImage[] map1to2transition = new BufferedImage[MAP_1_2_TRANSITION_COUNT];
    private BufferedImage[] map2to3transition = new BufferedImage[MAP_2_3_TRANSITION_COUNT];
    private BufferedImage[] delaware = new BufferedImage[DE_FRAME_COUNT];
    private BufferedImage[] ospreyNesting = new BufferedImage[OSPREY_NEST_COUNT];
    private BufferedImage[] harrierNesting = new BufferedImage[HARRIER_NEST_COUNT];
    private Image thoughtBubble;
    private static int momentFoodEaten = 0;
    private static int momentEnemyHit = 0;
    private int enemyDelay = 25;
    private int foodDelay = 25;
    private JFrame frame;
    private JPanel cards;
    private JPanel currentPanel;
    private JPanel startScreen;
    private JPanel tutorialScreen;
    private JPanel initialMap;
    private JPanel ospreyRound1;
    private JPanel map1to2;
    private JPanel ospreyRound2;
    private JPanel map2to3;
    private JPanel ospreyNest;
    private JPanel harrierRound;
    private JPanel harrierNest;
    private QuizPanel quiz;
    private JPanel gameOver;
    private JPanel movingScreen;
    private JLabel downLabel;
    private JLabel moveLabel;
    private JLabel upLabel;
    private JLabel foodLabel;
    private JLabel specialFoodLabel;

    private JLabel enemyLabel;
    private int foodX;
    private int foodX2;
    private int foodX3;

    private Direction direction;
    private CopyOnWriteArrayList<GamePiece> currentViewableGPs = new CopyOnWriteArrayList<>();
    private int health;
    private int score;
    private int backgroundLocation;
    private static int momentEaten;
    private Image backgroundImage;
    private Image backgroundImageFlipped;
    private Image startScreenImg;
    private Image initialMapImg;
    private Image gameOverImg;
    private Image homeScreenImg;
    private static boolean is2To3Transition;
    Color green = new Color(0, 255, 0, 100);
    Color red = new Color(255, 0, 0, 100);
    private static boolean isOspreyRound1Over = false;
    private static boolean isOspreyRound2Over = false;
    private static boolean is1To2Transition = false;
    private static boolean isHarrierRoundOver = false;
    boolean drawFish = true;
    boolean drawEagle = true;
    boolean drawSpecialSnake = true;

    boolean hit = false;
    boolean finished = false;
    boolean fishDone = false;
    boolean notOver = true;
	

    public View(Controller c) {
        frame = new JFrame();
        cards = new JPanel(new CardLayout());

        createStartScreen(c);
        createTutorialStartScreen(c);
        createTutorialMovingDemo(c);
        createOspreyPanels(c);
        createHarrierRound(c);
        createHarrierNestPanel(c);
        createQuizPanel();
        createGameOverPanel();
        loadImages();
        cards.add(startScreen, "START");
        cards.add(tutorialScreen, "TUTORIAL");
        cards.add(movingScreen, "MOVING_SCREEN");
        cards.add(initialMap, "INITIAL_MAP");
        cards.add(ospreyRound1, "OSPREY_ROUND_ONE");
        cards.add(map1to2, "MAP_1_TO_2");
        cards.add(ospreyRound2, "OSPREY_ROUND_TWO");
        cards.add(map2to3, "MAP_2_TO_3");
        cards.add(ospreyNest, "OSPREY_NEST");
        cards.add(harrierNest, "HARRIER_NEST");
        cards.add(harrierRound, "HARRIER_ROUND");
        cards.add(quiz, "QUIZ");
        cards.add(gameOver, "GAME_OVER");

        currentPanel = startScreen;
        createFrame(c);
    }

    void loadImages() {
        for (int i = 0; i < FRAME_COUNT; i++) {
            harrierFly[i] = createImage("images/BirdImages/Harrier" + i + ".png");
            harrierRed[i] = dye(harrierFly[i], red);
            harrierGreen[i] = dye(harrierFly[i], green);
        }
        for (int i = 0; i < FRAME_COUNT; i++) {
            ospreyFly[i] = createImage("images/BirdImages/Osprey" + i + ".png");
            ospreyRed[i] = dye(ospreyFly[i], red);
            ospreyGreen[i] = dye(ospreyFly[i], green);
        }
        for (int i = 0; i < MICE_FRAME_COUNT; i++) {
            mice[i] = createImage("images/BirdImages/Mice" + i + ".png");
            specialMice[i] = dye(mice[i], YELLOW);
        }
        for (int i = 0; i < BUNNY_FRAME_COUNT; i++) {
            bunny[i] = createImage("images/BirdImages/Bunny" + i + ".png");
            specialBunny[i] = dye(bunny[i], YELLOW);
        }
        for (int i = 0; i < RED_FOX_FRAME_COUNT; i++) {
            redFox[i] = createImage("images/BirdImages/RedFox" + i + ".png");
        }
        for (int i = 0; i < RACCOON_FRAME_COUNT; i++) {
            raccoon[i] = createImage("images/BirdImages/Raccoon" + i + ".png");
        }
        for (int i = 0; i < FISH_FRAME_COUNT; i++) {
            fish[i] = createImage("images/BirdImages/Fish" + i + ".png");
            specialFish[i] = dye(fish[i], YELLOW);
        }
        for (int i = 0; i < SNAKE_FRAME_COUNT; i++) {
            snake[i] = createImage("images/BirdImages/Snake" + i + ".png");
            specialSnake[i] = dye(snake[i], SNAKEYELLOW);
        }
        for (int i = 0; i < EAGLE_FRAME_COUNT; i++) {
            eagle[i] = createImage("images/BirdImages/Eagle" + i + ".png");
        }
        for (int i = 0; i < PLANE_FRAME_COUNT; i++) {
            plane[i] = createImage("images/BirdImages/Plane" + i + ".png");
        }
        for (int i = 0; i < MAP_1_2_TRANSITION_COUNT; i++) {
            map1to2transition[i] = createImage("images/BirdImages/OspreyLevelScreen" + i + ".png");
        }
        for (int i = 0; i < MAP_2_3_TRANSITION_COUNT; i++) {
            map2to3transition[i] = createImage("images/BirdImages/OspreyLevelScreen" + (i + 5) + ".png");
        }
        for (int i = 0; i < OSPREY_NEST_COUNT; i++) {
            ospreyNesting[i] = createImage("images/BirdImages/OspreyNesting/OspreyNest" + i + ".png");
        }
        for (int i = 0; i < HARRIER_NEST_COUNT; i++) {
            harrierNesting[i] = createImage("images/BirdImages/HarrierNesting/HarrierNest" + i + ".png");
        }
        thoughtBubble = createImage("images/bub.png").getScaledInstance(THOUGHT_BUBBLE_SIZE, THOUGHT_BUBBLE_SIZE, Image.SCALE_SMOOTH);
        for (int i = 0; i < DE_FRAME_COUNT; i++) {
            delaware[i] = createImage("images/MiniMapImages/delaware" + i + ".jpg");
        }
        gameOverImg = createImage("images/BirdImages/GameOver.png");
        homeScreenImg = createImage("images/BirdImages/HomeScreen.png");
    }

    private static BufferedImage dye(BufferedImage image, Color color) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        g.dispose();
        return dyed;
    }

    void createFrame(Controller c) {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(cards);
        frame.setFocusable(true);
        frame.addKeyListener(c);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true); //added
        frame.setResizable(false);
        frame.setVisible(true);
    }

    void createStartScreen(Controller c) {
        startScreen = new StartScreenPanel();
        startScreenImg = createImage("images/BirdImages/StartScreen.png");

        startScreen.setLayout(null);
        c.getOspreyButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
        c.getHarrierButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
        c.getOspreyButton().setBounds(FRAME_WIDTH / DEFAULT_BUTTON_FRAMEWIDTH_RATIO, (FRAME_HEIGHT * DEFAULT_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT / DEFAULT_BUTTON_Y_LOCATION_RATIO);
        c.getHarrierButton().setBounds((FRAME_WIDTH * HARRIER_BUTTON_X_MULTIPLIER) / DEFAULT_BUTTON_FRAMEWIDTH_RATIO, (FRAME_HEIGHT * HARRIER_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT / DEFAULT_BUTTON_Y_LOCATION_RATIO);

        startScreen.add(c.getOspreyButton());
        startScreen.add(c.getHarrierButton());
    }

    void createTutorialStartScreen(Controller c) {
        tutorialScreen = new TutorialScreenPanel();
        tutorialScreen.setLayout(null);
        c.getTutorialButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
        c.getTutorialButton().setBounds((FRAME_WIDTH * TUTORIAL_BUTTON_X_LOC_NUMERATOR) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, (FRAME_HEIGHT * TUTORIAL_BUTTON_Y_LOC_NUMERATOR) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / TUTORIAL_BUTTON_X_RATIO, FRAME_HEIGHT / TUTORIAL_BUTTON_Y_LOCATION_RATIO);
        tutorialScreen.add(c.getTutorialButton());

    }

    void createTutorialMovingDemo(Controller c) {
        movingScreen = new MovingScreenPanel();
        movingScreen.setLayout(null);
        moveLabel = new JLabel("Moving the bird makes it lose health");
        moveLabel.setFont(new Font("Times New Roman", TUTORIAL_LABEL_FONT_STYLE, FRAME_WIDTH / TUTORIAL_LABEL_FONT_SIZE_RATIO));
        moveLabel.setBounds(TUTORIAL_FOOD_ENEMY_LABEL_X_MULTIPLIER * FRAME_WIDTH / TUTORIAL_FOOD_ENEMY_LABEL_X_RATIO, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO, FRAME_WIDTH, FRAME_HEIGHT / 2);
        upLabel = new JLabel("Use the UP arrow key to move up");
        upLabel.setFont(new Font("Times New Roman", TUTORIAL_LABEL_FONT_STYLE, FRAME_WIDTH / TUTORIAL_LABEL_FONT_SIZE_RATIO));
        upLabel.setBounds(FRAME_WIDTH / TUTORIAL_UP_DOWN_LABEL_X_RATIO, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO, FRAME_WIDTH, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO);
        downLabel = new JLabel("Use the DOWN arrow key to move down");
        downLabel.setFont(new Font("Times New Roman", TUTORIAL_LABEL_FONT_STYLE, FRAME_WIDTH / TUTORIAL_LABEL_FONT_SIZE_RATIO));
        downLabel.setBounds(FRAME_WIDTH / TUTORIAL_UP_DOWN_LABEL_X_RATIO, FRAME_HEIGHT - FRAME_HEIGHT / TUTORIAL_LABEL_DOWN_Y_RATIO, FRAME_WIDTH, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO);
        foodLabel = new JLabel("Now try to hit the moving food");
        foodLabel.setFont(new Font("Times New Roman", TUTORIAL_LABEL_FONT_STYLE, FRAME_WIDTH / TUTORIAL_LABEL_FONT_SIZE_RATIO));
        foodLabel.setBounds(TUTORIAL_FOOD_ENEMY_LABEL_X_MULTIPLIER * FRAME_WIDTH / TUTORIAL_FOOD_ENEMY_LABEL_X_RATIO, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO, FRAME_WIDTH, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO);
        specialFoodLabel = new JLabel("Now try to hit the golden moving food");
        specialFoodLabel.setFont(new Font("Times New Roman", TUTORIAL_LABEL_FONT_STYLE, FRAME_WIDTH / TUTORIAL_LABEL_FONT_SIZE_RATIO));
        specialFoodLabel.setBounds(TUTORIAL_FOOD_ENEMY_LABEL_X_MULTIPLIER * FRAME_WIDTH / TUTORIAL_FOOD_ENEMY_LABEL_X_RATIO, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO, FRAME_WIDTH, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO);
        enemyLabel = new JLabel("Now avoid the predators and obstacles");
        enemyLabel.setFont(new Font("Times New Roman", TUTORIAL_LABEL_FONT_STYLE, FRAME_WIDTH / TUTORIAL_LABEL_FONT_SIZE_RATIO));
        enemyLabel.setBounds(TUTORIAL_FOOD_ENEMY_LABEL_X_MULTIPLIER * FRAME_WIDTH / TUTORIAL_FOOD_ENEMY_LABEL_X_RATIO, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO, FRAME_WIDTH, FRAME_HEIGHT / TUTORIAL_LABEL_Y_RATIO);
        c.getTutorialMovingButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
        c.getTutorialMovingButton().setBounds(FRAME_WIDTH / TUTORIAL_BUTTON_X_RATIO, TUTORIAL_BUTTON_Y_MULTIPLIER * FRAME_HEIGHT / TUTORIAL_BUTTON_Y_RATIO, FRAME_WIDTH / TUTORIAL_BUTTON_X_RATIO, FRAME_HEIGHT / TUTORIAL_BUTTON_Y_LOCATION_RATIO);
        movingScreen.add(upLabel);
        movingScreen.add(downLabel);
        movingScreen.add(foodLabel);
        movingScreen.add(specialFoodLabel);
        movingScreen.add(enemyLabel);
        movingScreen.add(moveLabel);
        movingScreen.add(c.getTutorialMovingButton());
        foodX = playerXLoc + FRAME_WIDTH;
        foodX2 = playerXLoc + FRAME_WIDTH;
        foodX3 = playerXLoc + FRAME_WIDTH;
        moveLabel.setVisible(true);
        foodLabel.setVisible(false);
        specialFoodLabel.setVisible(false);
        enemyLabel.setVisible(false);
        c.getTutorialMovingButton().setVisible(false);

    }

    void createOspreyPanels(Controller c) {
        createInitialMapPanel(c);
        createOspreyRound1Panel(c);
        createOspreyMap1to2();
        createOspreyRound2Panel(c);
        createOspreyMap2to3(c);
        createOspreyNestPanel(c);
    }

    void createInitialMapPanel(Controller c) {
        initialMap = new InitialMapPanel();
        initialMapImg = createImage("images/BirdImages/OspreyLevelScreen0.png");

        initialMap.setLayout(null);
        c.getRound1Button().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
        c.getRound1Button().setBounds((FRAME_WIDTH * ROUND_BUTTON_X_MULTIPLIER) / DEFAULT_BUTTON_FRAMEWIDTH_RATIO, (FRAME_HEIGHT * DEFAULT_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT / DEFAULT_BUTTON_Y_LOCATION_RATIO);

        initialMap.add(c.getRound1Button());
    }

    void createOspreyRound1Panel(Controller c) {
        ospreyRound1 = new OspreyPanel();
        c.getOsprey1SaveGameButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
        c.getOsprey1SaveGameButton().setBounds(FRAME_WIDTH / DEFAULT_BUTTON_FRAMEWIDTH_RATIO, (FRAME_HEIGHT * DEFAULT_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT);
        ospreyRound1.add(c.getOsprey1SaveGameButton());
    }

    void createOspreyMap1to2() {

        map1to2 = new Map1to2Panel();
        map1to2.setLayout(null);
        Controller.getRound2Button().setEnabled(true);
        map1to2.add(Controller.getRound2Button());
    }

    void createOspreyRound2Panel(Controller c) {
        ospreyRound2 = new OspreyPanel();
        c.getOsprey2SaveGameButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
        c.getOsprey2SaveGameButton().setBounds(FRAME_WIDTH / DEFAULT_BUTTON_FRAMEWIDTH_RATIO, (FRAME_HEIGHT * DEFAULT_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT);
        ospreyRound2.add(c.getOsprey2SaveGameButton());
    }

    void createOspreyMap2to3(Controller c) {
        map2to3 = new Map2to3Panel();
        map2to3.setLayout(null);
        Controller.getReturnToStartButton().setEnabled(true);
        map2to3.add(Controller.getReturnToStartButton());
    }

    void createOspreyNestPanel(Controller c) {
        ospreyNest = new OspreyNestPanel();
    }

    void createHarrierRound(Controller c) {
        harrierRound = new HarrierPanel();
        c.getHarrierSaveGameButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
        c.getHarrierSaveGameButton().setBounds(FRAME_WIDTH / DEFAULT_BUTTON_FRAMEWIDTH_RATIO, (FRAME_HEIGHT * DEFAULT_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT);
        harrierRound.add(c.getHarrierSaveGameButton());
    }

    void createHarrierNestPanel(Controller c) {
        harrierNest = new HarrierNestPanel();
    }

    void createQuizPanel() {
        quiz = new QuizPanel();

    }

    void clearPreviousQuizResult() {
        quiz.blank.setText("");
    }

    public void prepareQuiz() {

        HashMap<String, String[]> questionsToAsk = Model.getQuestionToAsk();
        Set<String> strings = questionsToAsk.keySet();
        Object[] temp = strings.toArray();
        String[] questions = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            questions[i] = temp[i].toString();
        }
        quiz.setQuestion(questions[Model.getQuestionNum()]);
        Controller.setAnswers(questionsToAsk.get(questions[Model.getQuestionNum()]));

        Model.setCorrectAnswer(questionsToAsk.get(questions[Model.getQuestionNum()])[4]);
    }

    public void answeredCorrectly(boolean bool) {
        if (bool) {
            quiz.blank.setForeground(Color.green);
            quiz.blank.setText("Correct! Nice Job");
            quiz.blank.setVisible(true);
        } else {
            quiz.blank.setForeground(Color.red);
            quiz.blank.setText("Incorrect");
            quiz.blank.setVisible(true);
        }
    }

    public void setFalse() {
        quiz.blank.setVisible(false);
    }

    void createGameOverPanel() {
        gameOver = new GameOverPanel();
    }

    public void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, -backgroundLocation, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g2d.drawImage(backgroundImageFlipped, FRAME_WIDTH - backgroundLocation, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        for (int i = 1; i < MAX_NUM_BACKGROUNDS; i++) {
            if (i % 2 == 0) {
                g2d.drawImage(backgroundImageFlipped, (i * FRAME_WIDTH) + (FRAME_WIDTH - backgroundLocation), 0, FRAME_WIDTH, FRAME_HEIGHT, null);
            } else {
                g2d.drawImage(backgroundImage, (i * FRAME_WIDTH) + (FRAME_WIDTH - backgroundLocation), 0, FRAME_WIDTH, FRAME_HEIGHT, null);
            }
        }

    }

    public void setBackground(ImageIcon i1, ImageIcon i2) {
        backgroundImage = i1.getImage();
        backgroundImageFlipped = i2.getImage();
    }

    public void setPanel(String s) {
        ((CardLayout) cards.getLayout()).show(cards, s);
        if (s.equals("START")) {
            currentPanel = startScreen;
        } else if (s.equals("TUTORIAL")) {
            currentPanel = tutorialScreen;
        } else if (s.equals("INITIAL_MAP")) {
            currentPanel = initialMap;
        } else if (s.equals("OSPREY_ROUND_ONE")) {
            currentPanel = ospreyRound1;
        } else if (s.equals("MAP_1_TO_2")) {
            currentPanel = map1to2;
        } else if (s.equals("OSPREY_ROUND_TWO")) {
            currentPanel = ospreyRound2;
        } else if (s.equals("MAP_2_TO_3")) {
            currentPanel = map2to3;
        } else if (s.equals("OSPREY_NEST")) {
            currentPanel = ospreyNest;
        } else if (s.equals("HARRIER_ROUND")) {
            currentPanel = harrierRound;
        } else if (s.equals("HARRIER_NEST")) {
            currentPanel = harrierNest;
        } else if (s.equals("QUIZ")) {
            currentPanel = quiz;
        } else if (s.equals("GAME_OVER")) {
            currentPanel = gameOver;
        } else if (s.equals("MOVING_SCREEN")) {
            currentPanel = movingScreen;
        }

    }

    public JPanel getPanel() {
        return currentPanel;
    }

    public BufferedImage createImage(String path) {

        BufferedImage buff;
        try {
            buff = ImageIO.read(new File(path));
            return buff;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void update(int xLoc, int yLoc, CopyOnWriteArrayList<GamePiece> g, Direction dir, int h, int s) {
        playerXLoc = xLoc;
        playerYLoc = yLoc;
        health = h;
        score = s;
        currentViewableGPs = g;
        direction = dir;
        backgroundLocation += BACKGROUND_LOCATION_X_INCREASE;
        frame.repaint();
        try {
            Thread.sleep(MILLISECONDS_PER_SECOND / FRAMES_PER_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class QuizPanel extends JPanel {

        String[] questions;
        JButton ButtonA;
        JButton ButtonB;
        JButton ButtonC;
        JButton ButtonD;
        JLabel question;
        JLabel blank;

        QuizPanel() {
            setLayout(new GridLayout(GRID_WIDTH, GRID_HEIGHT));    		
            ButtonA = Controller.getOptionAButton();
            ButtonB = Controller.getOptionBButton();
            ButtonC = Controller.getOptionCButton();
            ButtonD = Controller.getOptionDButton();
            question = new JLabel("Blank");
            blank = new JLabel();
            question.setFont(new Font("Times New Roman", QUIZ_FONT_STYLE, FRAME_WIDTH / QUESTION_FONT_SIZE_RATIO));
            question.setPreferredSize(new Dimension(FRAME_WIDTH / QUIZ_DIMENSION_PREFERRED_X_RATIO, FRAME_HEIGHT / QUIZ_DIMENSION_PREFERRED_Y_RATIO));
            blank.setFont(new Font("Times New Roman", QUIZ_FONT_STYLE, FRAME_WIDTH / BLANK_FONT_SIZE_RATIO));
            blank.setPreferredSize(new Dimension(FRAME_WIDTH / QUIZ_DIMENSION_PREFERRED_X_RATIO, FRAME_HEIGHT / QUIZ_DIMENSION_PREFERRED_Y_RATIO));
            add(question);
            add(blank);
            blank.setVisible(false);
            add(Controller.getOptionAButton());
            add(Controller.getOptionBButton());
            add(Controller.getOptionCButton());
            add(Controller.getOptionDButton());
        }

        public void setQuestion(String text) {
            question.setText(text);

        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }

    }

    class GameOverPanel extends JPanel {

        public JLabel finalScore = new JLabel("Score: " + score);

        GameOverPanel() {
            setLayout(null);
            finalScore.setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / 25));
            finalScore.setBounds((FRAME_WIDTH * 4) / DEFAULT_BUTTON_FRAMEWIDTH_RATIO, (FRAME_HEIGHT * 4) / 10, FRAME_WIDTH / 4, FRAME_HEIGHT / 15);
            Controller.getRestartGameButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
            Controller.getExitGameButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
            Controller.getRestartGameButton().setBounds(FRAME_WIDTH / DEFAULT_BUTTON_FRAMEWIDTH_RATIO, (FRAME_HEIGHT * 60) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / 4, FRAME_HEIGHT / 15);
            Controller.getExitGameButton().setBounds((FRAME_WIDTH * 6) / DEFAULT_BUTTON_FRAMEWIDTH_RATIO, (FRAME_HEIGHT * 60) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / 4, FRAME_HEIGHT / 15);

            add(Controller.getRestartGameButton());
            add(Controller.getExitGameButton());
            add(finalScore);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(gameOverImg, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
            finalScore.setText("Score: " + score);
        }
    }

    class StartScreenPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(startScreenImg, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        }
    }

    class TutorialScreenPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(homeScreenImg, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        }

    }

    class MovingScreenPanel extends JPanel {

        final static int FOOD_X_INCREASE = 14;
        final int DISTANCE_TO_NOT_DRAW_FOOD = 3 * FRAME_WIDTH / 28;
        final int HEIGHT_DIFFERENCE_TO_GET_FOOD = FRAME_HEIGHT / 8;
        final int DISTANCE_WHERE_PLAYER_MISSED_FOOD = 5 * FRAME_WIDTH / 28;

        protected void paintComponent(Graphics g) {
            runningFrameCount++;
            paintBackground(g);
            if (runningFrameCount % ticksPerFrameUpdate == 0) {
                ospreyPicNum = (ospreyPicNum + 1) % FRAME_COUNT;
                fishPicNum = (fishPicNum + 1) % FISH_FRAME_COUNT;
                eaglePicNum = (eaglePicNum + 1) % EAGLE_FRAME_COUNT;
                snakePicNum = (snakePicNum + 1) % SNAKE_FRAME_COUNT;

            }
            g.drawImage(ospreyFly[ospreyPicNum], playerXLoc, playerYLoc, this);
            if (notOver) {
                if (Controller.getUpArrowKeyTried() >= 1 && Controller.getDownArrowKeyTried() >= 1 && drawFish) {
                	moveLabel.setVisible(false);
                    foodX -= FOOD_X_INCREASE;
                    if (drawFish) {
                        g.drawImage(fish[fishPicNum], foodX, FRAME_HEIGHT / 2, this);
                    }
                    if (playerXLoc >= foodX - DISTANCE_TO_NOT_DRAW_FOOD && (playerYLoc >= FRAME_HEIGHT / 2 - HEIGHT_DIFFERENCE_TO_GET_FOOD && playerYLoc <= FRAME_HEIGHT / 2 + HEIGHT_DIFFERENCE_TO_GET_FOOD)) {
                        drawFish = false;
                        foodLabel.setVisible(false);
                        fishDone = true;
                        specialFoodLabel.setVisible(true);
                    } else if (playerXLoc >= foodX + DISTANCE_WHERE_PLAYER_MISSED_FOOD) {
                        foodX = playerXLoc + FRAME_WIDTH;
                    }
                }

                if (fishDone) {
                    foodX3 -= FOOD_X_INCREASE;
                    if (drawSpecialSnake) {
                        g.drawImage(specialSnake[snakePicNum], foodX3, FRAME_HEIGHT / 3, this);
                    }
                    if (playerXLoc >= foodX3 - DISTANCE_TO_NOT_DRAW_FOOD && (playerYLoc >= FRAME_HEIGHT / 3 - FRAME_HEIGHT / 16 && playerYLoc <= FRAME_HEIGHT / 3 + FRAME_HEIGHT / 16)) {
                        drawSpecialSnake = false;
                        specialFoodLabel.setVisible(false);
                        enemyLabel.setVisible(true);
                    } else if (playerXLoc >= foodX3 + DISTANCE_WHERE_PLAYER_MISSED_FOOD) {
                        foodX3 = playerXLoc + FRAME_WIDTH;
                    }
                }
                if (enemyLabel.isVisible()) {
                    foodX2 -= FOOD_X_INCREASE;
                    hit = false;
                    if (drawEagle) {
                        g.drawImage(thoughtBubble, playerXLoc + FRAME_WIDTH / 8, playerYLoc - FRAME_HEIGHT / 3, this);
                        g.setFont(new Font("Times New Roman", 1, FRAME_WIDTH / 60));
                        //(Model.getCurrentFact());
                        String[] lines = "Hitting a Special,Food displays a fact, and gives you full,        health".split(",");
                        int yOffset = 0;
                        for (String line : lines) {
                            yOffset += g.getFontMetrics().getHeight(); 
                            g.drawString(line, playerXLoc + FRAME_WIDTH / 6, playerYLoc - 18 * FRAME_HEIGHT / 72 + yOffset);
                        }
                        g.drawImage(eagle[eaglePicNum], foodX2, FRAME_HEIGHT / 4, this);
                    }

                    if (playerXLoc >= foodX2 - DISTANCE_TO_NOT_DRAW_FOOD && (playerYLoc >= FRAME_HEIGHT / 3 - FRAME_HEIGHT / 8 && playerYLoc <= FRAME_HEIGHT / 3 + FRAME_HEIGHT / 8)) {
                        drawEagle = false;
                        hit = true;
                    } else if (playerXLoc >= foodX2 + 3 * FRAME_WIDTH / 14) {
                        hit = false;
                        finished = true;
                    }
                    if (hit) {
                        drawEagle = true;
                        foodX2 = playerXLoc + FRAME_WIDTH;
                    }
                }
            }
            if (!hit && finished) {
                enemyLabel.setVisible(false);
                notOver = false;
                Controller.getTutorialMovingButton().setVisible(true);
            }

        }
    }

    class InitialMapPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(initialMapImg, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        }
    }

    class OspreyPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            runningFrameCount++;
            paintBackground(g);
            if (runningFrameCount % ticksPerFrameUpdate == 0) {
                ospreyPicNum = (ospreyPicNum + 1) % FRAME_COUNT;
            }
            if (Model.specialFoodEaten()) {
                displayFacts(g);
                if (runningFrameCount > momentEaten + SPECIAL_FOOD_DELAY) {
                    Model.setSpecialFoodEaten(false);
                    Model.incrFactIndex();
                }
            }
            if (Model.enemyHit()) {
                g.drawImage(ospreyRed[ospreyPicNum], playerXLoc, playerYLoc, this);
            } else if (Model.foodHit()) {
                g.drawImage(ospreyGreen[ospreyPicNum], playerXLoc, playerYLoc, this);
            } else {

                g.drawImage(ospreyFly[ospreyPicNum], playerXLoc, playerYLoc, this);
            }

            if (runningFrameCount > momentFoodEaten + foodDelay) {
                Model.setFoodHit(false);
            }
            if (runningFrameCount > momentEnemyHit + enemyDelay) {
                Model.setEnemyHit(false);
            }

            Iterator<GamePiece> it = currentViewableGPs.iterator();
            while (it.hasNext()) {
                GamePiece gp = it.next();
                if (gp.isSpecialFood()) {
                    if (gp.getSprite().equals(Sprite.SNAKE)) { 
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % SNAKE_FRAME_COUNT);
                        }
                        g.drawImage(specialSnake[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else {
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % FISH_FRAME_COUNT);
                        }
                        g.drawImage(specialFish[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }
                } else if (gp.isFood()) {
                    if (gp.getSprite().equals(Sprite.SNAKE)) { 
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % SNAKE_FRAME_COUNT);
                        }
                        g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else {
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % FISH_FRAME_COUNT);
                        }
                        g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }
                } else if (gp.isEnemy()) {
                    if (gp.getSprite().equals(Sprite.EAGLE)) {
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % EAGLE_FRAME_COUNT);
                        }
                        g.drawImage(eagle[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else {
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % PLANE_FRAME_COUNT);
                        }
                        g.drawImage(plane[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }

                }

            }
            g.setColor(Color.green);
            if (health < MAX_BIRD_HEALTH / 2) {
                g.setColor(Color.red);
            }
            g.drawRect(FRAME_WIDTH / 105, FRAME_HEIGHT / 75, MAX_BIRD_HEALTH * 2, FRAME_HEIGHT / 17);
            g.fillRect(FRAME_WIDTH / 105, FRAME_HEIGHT / 75, health * 2, FRAME_HEIGHT / 17);
            g.setColor(Color.black);
            g.setFont(new Font("Times New Roman", 1, FRAME_WIDTH / 47));
            g.drawString("Score: " + String.valueOf(score), FRAME_WIDTH - FRAME_WIDTH / 8, FRAME_HEIGHT / 17);

        }
    }

    void displayFacts(Graphics g) {
        g.drawImage(thoughtBubble, playerXLoc + FRAME_WIDTH / 8, playerYLoc - FRAME_HEIGHT / 3, this);
        g.setFont(new Font("Times New Roman", 1, FRAME_WIDTH / 57));
        String[] lines = Model.getCurrentFact().split(",");
        int yOffset = 0;
        for (String line : lines) {
            yOffset += g.getFontMetrics().getHeight();
            g.drawString(line, playerXLoc + FRAME_WIDTH / 6 - 5, playerYLoc - 17 * FRAME_HEIGHT / 72 + yOffset);
        }

    }

    class HarrierPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            runningFrameCount++;
            paintBackground(g);
            if (runningFrameCount % ticksPerFrameUpdate == 0) {
                harrierPicNum = (harrierPicNum + 1) % FRAME_COUNT;
            }
            if (Model.specialFoodEaten() && Model.hasMoreFacts()) {
                displayFacts(g);
                if (runningFrameCount > momentEaten + SPECIAL_FOOD_DELAY) {
                    Model.setSpecialFoodEaten(false);
                    Model.incrFactIndex();
                }
            }
            if (Model.enemyHit()) {
                g.drawImage(harrierRed[harrierPicNum], playerXLoc, playerYLoc, this);
            } else if (Model.foodHit()) {
                g.drawImage(harrierGreen[harrierPicNum], playerXLoc, playerYLoc, this);
            } else {

                g.drawImage(harrierFly[harrierPicNum], playerXLoc, playerYLoc, this);
            }

            if (runningFrameCount > momentFoodEaten + foodDelay) {
                Model.setFoodHit(false);
            }
            if (runningFrameCount > momentEnemyHit + enemyDelay) {
                Model.setEnemyHit(false);
            }

            for (GamePiece gp : currentViewableGPs) {
                if (gp.isSpecialFood()) {
                    if (gp.getSprite().equals(Sprite.MOUSE)) {
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % MICE_FRAME_COUNT);
                        }
                        g.drawImage(specialMice[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else {
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % BUNNY_FRAME_COUNT);
                        }
                        g.drawImage(specialBunny[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }
                } else if (gp.isFood()) {
                    if (gp.getSprite().equals(Sprite.MOUSE)) {
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % MICE_FRAME_COUNT);
                        }
                        g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else { 
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % BUNNY_FRAME_COUNT);
                        }
                        g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }
                } else if (gp.isEnemy()) {
                    if (gp.getSprite().equals(Sprite.REDFOX)) { 
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % RED_FOX_FRAME_COUNT);
                        }
                        g.drawImage(redFox[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else {
                        if (runningFrameCount % ticksPerFrameUpdate == 0) {
                            gp.setPicNum((gp.getPicNum() + 1) % RACCOON_FRAME_COUNT);
                        }
                        g.drawImage(raccoon[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }

                }

            }

            g.setColor(Color.green);
            if (health < MAX_BIRD_HEALTH / 2) {
                g.setColor(Color.red);
            }
            g.drawRect(FRAME_WIDTH / 105, FRAME_HEIGHT / 75, MAX_BIRD_HEALTH * 2, FRAME_HEIGHT / 17);
            g.fillRect(FRAME_WIDTH / 105, FRAME_HEIGHT / 75, health * 2, FRAME_HEIGHT / 17);
            g.setColor(Color.black);
            g.setFont(new Font("Times New Roman", 1, FRAME_WIDTH / 47));
            g.drawString("Score: " + String.valueOf(score), FRAME_WIDTH - FRAME_WIDTH / 8, FRAME_HEIGHT / 17);
            if (runningFrameCount % 30 == 0) {
                dePicNum = (dePicNum + 1) % DE_FRAME_COUNT;
            }
            g.drawImage(delaware[dePicNum], FRAME_WIDTH - 3*FRAME_WIDTH/28, FRAME_HEIGHT - 7*FRAME_HEIGHT/16, 3*FRAME_WIDTH/28, 7*FRAME_HEIGHT/16, this);
        }
    }

    class Map1to2Panel extends JPanel {

        protected void paintComponent(Graphics g) {
            if (map1To2TransitionPicNum < MAP_1_2_TRANSITION_COUNT) {
                Controller.getRound2Button().setVisible(false);
                g.drawImage(map1to2transition[map1To2TransitionPicNum], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
                map1To2TransitionPicNum++;
                try {
                    Thread.sleep(SECONDS_PER_MAP_FRAME * MILLISECONDS_PER_SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                g.drawImage(map1to2transition[MAP_1_2_TRANSITION_COUNT - 1], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
                Controller.getRound2Button().setVisible(true);
                Controller.getRound2Button().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
                Controller.getRound2Button().setBounds((FRAME_WIDTH * ROUND_BUTTON_X_MULTIPLIER) / DEFAULT_BUTTON_FRAMEWIDTH_RATIO,
                        (FRAME_HEIGHT * DEFAULT_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT / DEFAULT_BUTTON_Y_LOCATION_RATIO);
                add(Controller.getRound2Button());
            }

        }
    }

    class Map2to3Panel extends JPanel {

        protected void paintComponent(Graphics g) {
            if (map2To3TransitionPicNum < MAP_2_3_TRANSITION_COUNT) {
                Controller.getOspreyNestButton().setVisible(false);
                g.drawImage(map2to3transition[map2To3TransitionPicNum], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
                map2To3TransitionPicNum++;
                try {
                    Thread.sleep(SECONDS_PER_MAP_FRAME * MILLISECONDS_PER_SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                Controller.getOspreyNestButton().setVisible(true);
                g.drawImage(map2to3transition[MAP_2_3_TRANSITION_COUNT - 1], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
                Controller.getOspreyNestButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
                Controller.getOspreyNestButton().setBounds((FRAME_WIDTH * ROUND_BUTTON_X_MULTIPLIER) / DEFAULT_BUTTON_FRAMEWIDTH_RATIO,
                        (FRAME_HEIGHT * DEFAULT_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT / DEFAULT_BUTTON_Y_LOCATION_RATIO);
                add(Controller.getOspreyNestButton());
            }

        }
    }

    class OspreyNestPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            if (ospreyNestPicNum < OSPREY_NEST_COUNT) {
                Controller.getReturnToStartButton().setVisible(false);
                g.drawImage(ospreyNesting[ospreyNestPicNum], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
                ospreyNestPicNum++;
                try {
                    Thread.sleep(NEST_DELAY_MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Controller.getReturnToStartButton().setVisible(true);
                g.drawImage(ospreyNesting[OSPREY_NEST_COUNT - 1], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
                Controller.getReturnToStartButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
                Controller.getReturnToStartButton().setBounds((FRAME_WIDTH) / DEFAULT_BUTTON_FRAMEWIDTH_RATIO,
                        (FRAME_HEIGHT * DEFAULT_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT / DEFAULT_BUTTON_Y_LOCATION_RATIO);
                add(Controller.getReturnToStartButton());
            }
        }
    }

    class HarrierNestPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            if (harrierNestPicNum < HARRIER_NEST_COUNT) {
                Controller.getReturnToStartButton().setVisible(false);
                g.drawImage(harrierNesting[harrierNestPicNum], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
                harrierNestPicNum++;
                try {
                    Thread.sleep(NEST_DELAY_MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Controller.getReturnToStartButton().setVisible(true);
                g.drawImage(harrierNesting[HARRIER_NEST_COUNT - 1], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
                Controller.getReturnToStartButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / DEFAULT_BUTTON_FONT_SIZE_RATIO));
                Controller.getReturnToStartButton().setBounds((FRAME_WIDTH) / DEFAULT_BUTTON_FRAMEWIDTH_RATIO,
                        (FRAME_HEIGHT * DEFAULT_BUTTON_Y_MULTIPLIER) / DEFAULT_BUTTON_FRAMEHEIGHT_RATIO, FRAME_WIDTH / DEFAULT_BUTTON_X_LOCATION_RATIO, FRAME_HEIGHT / DEFAULT_BUTTON_Y_LOCATION_RATIO);
                add(Controller.getReturnToStartButton());
                Controller.setHarrierNested(true);
            }
        }
    }

    public int getBirdWidth() {
        return BIRD_WIDTH;
    }

    public int getBirdHeight() {
        return BIRD_HEIGHT;
    }

    public int getFrameHeight() {
        return FRAME_HEIGHT;
    }

    public int getFrameWidth() {
        return FRAME_WIDTH;
    }

    public static int getFrameCount() {
        return runningFrameCount;
    }

    public static void setMomentEaten(int i) {
        momentEaten = i;
    }

    public JPanel getStartScreen() {
        return startScreen;
    }

    public boolean getIsOspreyRound1Over() {
        return isOspreyRound1Over;
    }

    public boolean getIsOspreyRound2Over() {
        return isOspreyRound2Over;
    }

    public boolean getIsHarrierRoundOver() {
        return isHarrierRoundOver;
    }

    public static void setIsOspreyRound1Over(boolean b) {
        isOspreyRound1Over = b;
    }

    public static void setIsOspreyRound2Over(boolean b) {
        isOspreyRound2Over = b;
    }

    public static void setIsHarrierRoundOver(boolean b) {
        isHarrierRoundOver = b;
    }

    public static boolean is1To2Transition() {
        return is1To2Transition;
    }

    public static void set1To2Transition(boolean b) {
        is1To2Transition = b;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void resetView() {
        runningFrameCount = 0;
        harrierPicNum = 0;
        ospreyPicNum = 0;
        micePicNum = 0;
        bunnyPicNum = 0;
        redFoxPicNum = 0;
        raccoonPicNum = 0;
        fishPicNum = 0;
        snakePicNum = 0;
        eaglePicNum = 0;
        planePicNum = 0;
        dePicNum = 0;
        map1To2TransitionPicNum = 0;
        map2To3TransitionPicNum = 0;
        ospreyNestPicNum = 0;
        harrierNestPicNum = 0;
        currentViewableGPs.clear();
        health = 0;
        score = 0;
        backgroundLocation = 0;
        momentEaten = 0;
        isOspreyRound1Over = false;
        isOspreyRound2Over = false;
        is1To2Transition = false;
        is2To3Transition = false;
        isHarrierRoundOver = false;
    }

    public static boolean is2To3Transition() {
        return is2To3Transition;
    }

    public static void set2To3Transition(boolean b) {
        is2To3Transition = b;

    }

    public JLabel getUpLabel() {
        return upLabel;
    }

    public JLabel getDownLabel() {
        return downLabel;
    }

    public JLabel getFoodLabel() {
        return foodLabel;
    }

    public JPanel getQuiz() {
        return quiz;
    }

    public JLabel getBlank() {
        return quiz.blank;
    }

    public static void setMomentFoodEaten(int frameCount) {
        momentFoodEaten = frameCount;

    }

    public static void setMomentEnemyHit(int frameCount) {
        momentEnemyHit = frameCount;
    }

}
