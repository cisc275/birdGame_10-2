/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.util.ArrayList;
import java.util.TimerTask;

import java.awt.CardLayout;

/**
 *
 * @author crnis
 */
public class View extends JPanel {

    static Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    final static int FRAME_WIDTH = (int) rect.getWidth();
    final static int FRAME_HEIGHT = (int) rect.getHeight();
    final static int BIRD_WIDTH = 184;
    final static int BIRD_HEIGHT = 165;
    final static int FRAME_COUNT = 6;
    final static int TICKS_PER_FRAME_UPDATE = 5;
    final static int MILLISECONDS_PER_SECOND = 1000;
    final static int FRAMES_PER_SECOND = 50;
    private static int runningFrameCount = 0;
    private int picNum = 0;
    final static int MICE_FRAME_COUNT = 2;
    final static int BUNNY_FRAME_COUNT = 4;
    final static int redFoxFrameCount = 4;
    final static int raccoonFrameCount = 4;
    final static int fishFrameCount = 4;
    final static int snakeFrameCount = 4;
    final static int eagleFrameCount = 6;
    final static int planeFrameCount = 1;
    private int micePicNum = 0;
    private int bunnyPicNum = 0;
    private int redFoxPicNum = 0;
    private int raccoonPicNum = 0;
    private int fishPicNum = 0;
    private int snakePicNum = 0;
    private int eaglePicNum = 0;
    private int planePicNum = 0;
    private int playerXLoc = 0;
    private int playerYLoc = 0;
    private BufferedImage[] flyForward = new BufferedImage[6];
    private BufferedImage[] mice = new BufferedImage[2];
    private BufferedImage[] bunny = new BufferedImage[4];
    private BufferedImage[] redFox = new BufferedImage[4];
    private BufferedImage[] raccoon = new BufferedImage[4];
    private BufferedImage[] fish = new BufferedImage[4];
    private BufferedImage[] snake = new BufferedImage[4];
    private BufferedImage[] plane = new BufferedImage[1];
    private BufferedImage[] eagle = new BufferedImage[6];
    private BufferedImage[] enterNest;
    private Image thoughtBubble;

    private JFrame frame;
    private JPanel cards;
    private JPanel currentPanel;
    private JPanel startScreen;
    private JPanel initialMap;
    private JPanel ospreyRound1;
    private JPanel map1to2;
    private JPanel ospreyRound2;
    private JPanel map2to3;
    private JPanel ospreyNest;
    private JPanel harrierRound;
    private JPanel quiz;
    private JPanel gameOver;
    private int specialFoodDelay = 300;

    private Direction direction;
    private ArrayList<GamePiece> currentViewableGPs = new ArrayList<>();
    private int health;
    private int score;
    private int backgroundLocation;
    private static int momentEaten;
    private Image backgroundImage;
    private Image backgroundImageFlipped;
    private Image startScreenImg;
    private Image initialMapImg;

    public View(Controller c) {
        frame = new JFrame();
        cards = new JPanel(new CardLayout());

        createStartScreen(c);
        createOspreyPanels(c);
        createHarrierRound();
        createQuizPanel();
        createGameOverPanel();
        loadImages();
        cards.add(startScreen, "START");
        cards.add(initialMap, "INITIAL_MAP");
        cards.add(ospreyRound1, "OSPREY_ROUND_ONE");
//        cards.add(map1to2, "MAP_1_TO_2");
//        cards.add(ospreyRound2, "OSPREY_ROUND_TWO");
//        cards.add(map2to3, "MAP_2_TO_3");
//        cards.add(ospreyNest, "OSPREY_NEST");
        cards.add(harrierRound, "HARRIER_ROUND");
//        cards.add(quiz, "QUIZ");
//        cards.add(gameOver, "GAME_OVER");

        currentPanel = startScreen;
        createFrame(c);
    }
    void loadImages() {
        for(int i = 0; i < FRAME_COUNT; i++){
            flyForward[i] = createImage("images/BirdImages/Bird" + i + ".png");
        }
        for(int i = 0; i < MICE_FRAME_COUNT; i++){
            mice[i] = createImage("images/BirdImages/Mice" + i + ".png");
        }
        for(int i = 0; i < BUNNY_FRAME_COUNT; i++){
            bunny[i] = createImage("images/BirdImages/Bunny" + i + ".png");
        }
        for(int i = 0; i < redFoxFrameCount; i++){
            redFox[i] = createImage("images/BirdImages/RedFox" + i + ".png");
        }
        for(int i = 0; i < raccoonFrameCount; i++){
            raccoon[i] = createImage("images/BirdImages/Raccoon" + i + ".png");
        }
        for(int i = 0; i < fishFrameCount; i++){
            fish[i] = createImage("images/BirdImages/Fish" + i + ".png");
        }
        for(int i = 0; i < snakeFrameCount; i++){
            snake[i] = createImage("images/BirdImages/Snake" + i + ".png");
        }
        for(int i = 0; i < eagleFrameCount; i++){
            eagle[i] = createImage("images/BirdImages/Eagle" + i + ".png");
        }
        for(int i = 0; i < planeFrameCount; i++){
            plane[i] = createImage("images/BirdImages/Plane" + i + ".png");
        }
        thoughtBubble = createImage("images/bub.png").getScaledInstance
       		(400, 400, Image.SCALE_SMOOTH);
    }
    void createFrame(Controller c) {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(cards);
        frame.setFocusable(true);
        frame.addKeyListener(c);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    void createStartScreen(Controller c) {
        startScreen = new StartScreenPanel();
        startScreenImg = createImage("images/BirdImages/StartScreen.png");

        startScreen.setLayout(null);
        c.getOspreyButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / 55));
        c.getHarrierButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / 55));
        c.getOspreyButton().setBounds(FRAME_WIDTH / 10, (FRAME_HEIGHT * 84) / 100, FRAME_WIDTH / 4, FRAME_HEIGHT / 15);
        c.getHarrierButton().setBounds((FRAME_WIDTH * 6) / 10, (FRAME_HEIGHT * 37) / 100, FRAME_WIDTH / 4, FRAME_HEIGHT / 15);

        startScreen.add(c.getOspreyButton());
        startScreen.add(c.getHarrierButton());
    }

    void createOspreyPanels(Controller c) {
        createInitialMapPanel(c);
        createOspreyRound1Panel();
        createOspreyMap1to2();
        createOspreyRound2Panel();
        createOspreyMap2to3();
        createOspreyNestPanel();
    }

    void createInitialMapPanel(Controller c) {
        initialMap = new InitialMapPanel();
        initialMapImg = createImage("images/BirdImages/OspreyLevelScreen0.png");

        initialMap.setLayout(null);
        c.getRound1Button().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH / 55));
        c.getRound1Button().setBounds((FRAME_WIDTH * 7) / 10, (FRAME_HEIGHT * 84) / 100, FRAME_WIDTH / 4, FRAME_HEIGHT / 15);

        initialMap.add(c.getRound1Button());
    }

    void createOspreyRound1Panel() {
        ospreyRound1 = new OspreyPanel();
        

    }

    void createOspreyMap1to2() {

    }

    void createOspreyRound2Panel() {

    }

    void createOspreyMap2to3() {

    }

    void createOspreyNestPanel() {

    }

    void createHarrierRound() {
        harrierRound = new HarrierPanel();
    }

    void createQuizPanel() {

    }

    void createGameOverPanel() {

    }

//    public void paint(Graphics g) {
//        paintBackground(g);
//        picNum = (picNum + 1) % FRAME_COUNT;
//        if (Model.specialFoodEaten()) {
//            //displayFacts(g);
//        }
//
//        g.drawImage(flyForward[picNum], playerXLoc, playerYLoc, this);
//        for (GamePiece gp : currentViewableGPs) {
//            if (gp.isSpecialFood()) {
//                if (gp.getSprite().equals(Sprite.MOUSE)) { //mice
//                    //micePicNum = (micePicNum + 1) % miceFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % MICE_FRAME_COUNT);
//                    g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
//                } else if (gp.getSprite().equals(Sprite.BUNNY)) { //bunny
//                    //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % BUNNY_FRAME_COUNT);
//                    g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
//                } else if (gp.getSprite().equals(Sprite.SNAKE)) { //snake
//                    //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % snakeFrameCount);
//                    g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
//                } else {// fish
//                    //fishPicNum = (fishPicNum + 1) % fishFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % fishFrameCount);
//                    g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
//                }
//            } else if (gp.isFood()) {
//                if (gp.getSprite().equals(Sprite.MOUSE)) { //mice
//                    //micePicNum = (micePicNum + 1) % miceFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % MICE_FRAME_COUNT);
//                    g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                } else if (gp.getSprite().equals(Sprite.BUNNY)) { //bunny
//                    //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % BUNNY_FRAME_COUNT);
//                    g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                } else if (gp.getSprite().equals(Sprite.SNAKE)) { //snake
//                    //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % snakeFrameCount);
//                    g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                } else {// fish
//                    //fishPicNum = (fishPicNum + 1) % fishFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % fishFrameCount);
//                    g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                }
//            } else if (gp.isEnemy()) {
//                if (gp.getSprite().equals(Sprite.REDFOX)) { //red fox
//                    //redFoxPicNum = (redFoxPicNum + 1) % redFoxFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % redFoxFrameCount);
//                    g.drawImage(redFox[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                } else if (gp.getSprite().equals(Sprite.RACCOON)) { //raccoons
//                    //raccoonPicNum = (raccoonPicNum + 1) % raccoonFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % raccoonFrameCount);
//                    g.drawImage(raccoon[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                } else if (gp.getSprite().equals(Sprite.EAGLE)) { //eagles
//                    //eaglePicNum = (eaglePicNum + 1) % eagleFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % eagleFrameCount);
//                    g.drawImage(eagle[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                } else { //planes
//                    //planePicNum = (planePicNum + 1) % planeFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1) % planeFrameCount);
//                    g.drawImage(plane[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                }
//
//            }
//
//        }
//        g.setColor(Color.red);
//        g.drawRect(10, 10, 100 * 2, 50);
//        g.fillRect(10, 10, health * 2, 50);
//        g.setColor(Color.white);
//        g.setFont(new Font("Times New Roman", 1, 20));
//        g.drawRect(FRAME_WIDTH - 105, 20, 100, 50);
//        g.drawString("Score: " + String.valueOf(score), FRAME_WIDTH - 100, 50);
//
//    }

    public void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, -backgroundLocation, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g2d.drawImage(backgroundImageFlipped, FRAME_WIDTH - backgroundLocation, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        for (int i = 1; i < 200; i++) {
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
        } else if (s.equals("QUIZ")) {
            currentPanel = quiz;
        } else if (s.equals("GAME_OVER")) {
            currentPanel = gameOver;
        }

    }
    public JPanel getPanel(){
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

    public void update(int xLoc, int yLoc, ArrayList<GamePiece> g, Direction dir, int h, int s) {
        playerXLoc = xLoc;
        playerYLoc = yLoc;
        health = h;
        score = s;
        currentViewableGPs = g;
        direction = dir;
        backgroundLocation += 8;
        frame.repaint();
        try {
            Thread.sleep(MILLISECONDS_PER_SECOND / FRAMES_PER_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class StartScreenPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(startScreenImg, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
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
            if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
            	picNum = (picNum + 1) % FRAME_COUNT;
            }
            if (Model.specialFoodEaten()) {
                displayFacts(g);
                if (runningFrameCount > momentEaten + specialFoodDelay) {
                	Model.setSpecialFoodEaten(false);
                }
            }

            g.drawImage(flyForward[picNum], playerXLoc, playerYLoc, this);
            for (GamePiece gp : currentViewableGPs) {
                if (gp.isSpecialFood()) {
                    if (gp.getSprite().equals(Sprite.MOUSE)) { //mice
                        //micePicNum = (micePicNum + 1) % miceFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % MICE_FRAME_COUNT);
                    	}
                        g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
                    } else if (gp.getSprite().equals(Sprite.BUNNY)) { //bunny
                        //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % BUNNY_FRAME_COUNT);
                    	}
                        g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
                    } else if (gp.getSprite().equals(Sprite.SNAKE)) { //snake
                        //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % snakeFrameCount);
                    	}
                        g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
                    } else {// fish
                        //fishPicNum = (fishPicNum + 1) % fishFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % fishFrameCount);
                    	}
                        g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
                    }
                } else if (gp.isFood()) {
                    if (gp.getSprite().equals(Sprite.MOUSE)) { //mice
                        //micePicNum = (micePicNum + 1) % miceFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % MICE_FRAME_COUNT);
                    	}
                        g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else if (gp.getSprite().equals(Sprite.BUNNY)) { //bunny
                        //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % BUNNY_FRAME_COUNT);
                    	}
                        g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else if (gp.getSprite().equals(Sprite.SNAKE)) { //snake
                        //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % snakeFrameCount);
                    	}
                        g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else {// fish
                        //fishPicNum = (fishPicNum + 1) % fishFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % fishFrameCount);
                    	}
                        g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }
                } else if (gp.isEnemy()) {
                    if (gp.getSprite().equals(Sprite.REDFOX)) { //red fox
                        //redFoxPicNum = (redFoxPicNum + 1) % redFoxFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % redFoxFrameCount);
                    	}
                        g.drawImage(redFox[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else if (gp.getSprite().equals(Sprite.RACCOON)) { //raccoons
                        //raccoonPicNum = (raccoonPicNum + 1) % raccoonFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % raccoonFrameCount);
                    	}
                        g.drawImage(raccoon[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else if (gp.getSprite().equals(Sprite.EAGLE)) { //eagles
                        //eaglePicNum = (eaglePicNum + 1) % eagleFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % eagleFrameCount);
                    	}
                        g.drawImage(eagle[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else { //planes
                        //planePicNum = (planePicNum + 1) % planeFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % planeFrameCount);
                    	}
                        g.drawImage(plane[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }

                }

            }
            g.setColor(Color.red);
            g.drawRect(10, 10, 250, 50);
            g.fillRect(10, 10, health, 50);
            g.setColor(Color.white);
            g.setFont(new Font("Times New Roman", 1, 20));
            g.drawRect(FRAME_WIDTH - 105, 20, 250, 50);
            g.drawString("Score: " + String.valueOf(score), FRAME_WIDTH - 100, 50);
        }
    }
    
    void displayFacts(Graphics g) {
    //	JLabel img = new JLabel(new ImageIcon(thoughtBubble));
	// 	img.setBounds(playerXLoc + 300,playerYLoc ,300,300);
	// 	getPanel().add(img);
    	g.drawImage(thoughtBubble, playerXLoc + 300, playerYLoc -300, this);
        g.setFont(new Font("Times New Roman", 1, 20));
        System.out.println(Model.getCurrentFact());
    	g.drawString(Model.getCurrentFact(),playerXLoc + 300,playerYLoc -100 );

    }
    
    
    class HarrierPanel extends JPanel{
    
        protected void paintComponent(Graphics g) {
        	runningFrameCount++;
            paintBackground(g);
            if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
            	picNum = (picNum + 1) % FRAME_COUNT;
            }
            if (Model.specialFoodEaten()) {
                displayFacts(g);
            }

            g.drawImage(flyForward[picNum], playerXLoc, playerYLoc, this);
            for (GamePiece gp : currentViewableGPs) {
                if (gp.isSpecialFood()) {
                    if (gp.getSprite().equals(Sprite.MOUSE)) { //mice
                        //micePicNum = (micePicNum + 1) % miceFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % MICE_FRAME_COUNT);
                    	}
                        g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
                    } else if (gp.getSprite().equals(Sprite.BUNNY)) { //bunny
                        //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % BUNNY_FRAME_COUNT);
                    	}
                        g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
                    } else if (gp.getSprite().equals(Sprite.SNAKE)) { //snake
                        //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % snakeFrameCount);
                    	}
                        g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
                    } else {// fish
                        //fishPicNum = (fishPicNum + 1) % fishFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % fishFrameCount);
                    	}
                        g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(), Color.RED, this);
                    }
                } else if (gp.isFood()) {
                    if (gp.getSprite().equals(Sprite.MOUSE)) { //mice
                        //micePicNum = (micePicNum + 1) % miceFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % MICE_FRAME_COUNT);
                    	}
                        g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else if (gp.getSprite().equals(Sprite.BUNNY)) { //bunny
                        //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % BUNNY_FRAME_COUNT);
                    	}
                        g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else if (gp.getSprite().equals(Sprite.SNAKE)) { //snake
                        //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % snakeFrameCount);
                    	}
                        g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else {// fish
                        //fishPicNum = (fishPicNum + 1) % fishFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % fishFrameCount);
                    	}
                        g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }
                } else if (gp.isEnemy()) {
                    if (gp.getSprite().equals(Sprite.REDFOX)) { //red fox
                        //redFoxPicNum = (redFoxPicNum + 1) % redFoxFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % redFoxFrameCount);
                    	}
                        g.drawImage(redFox[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else if (gp.getSprite().equals(Sprite.RACCOON)) { //raccoons
                        //raccoonPicNum = (raccoonPicNum + 1) % raccoonFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % raccoonFrameCount);
                    	}
                        g.drawImage(raccoon[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else if (gp.getSprite().equals(Sprite.EAGLE)) { //eagles
                        //eaglePicNum = (eaglePicNum + 1) % eagleFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % eagleFrameCount);
                    	}
                        g.drawImage(eagle[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    } else { //planes
                        //planePicNum = (planePicNum + 1) % planeFrameCount;
                    	if (runningFrameCount % TICKS_PER_FRAME_UPDATE == 0) {
                    		gp.setPicNum((gp.getPicNum() + 1) % planeFrameCount);
                    	}
                        g.drawImage(plane[gp.getPicNum()], gp.getX(), gp.getY(), this);
                    }

                }

            }
            g.setColor(Color.red);
            g.drawRect(10, 10, 250, 50);
            g.fillRect(10, 10, health, 50);
            g.setColor(Color.white);
            g.setFont(new Font("Times New Roman", 1, 20));
            g.drawRect(FRAME_WIDTH - 105, 20, 250, 50);
            g.drawString("Score: " + String.valueOf(score), FRAME_WIDTH - 100, 50);
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
}
