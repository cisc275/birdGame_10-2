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
public class View {
    static Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    final static int FRAME_WIDTH = (int) rect.getWidth();
    final static int FRAME_HEIGHT = (int) rect.getHeight();
    final static int BIRD_WIDTH = 184;
    final static int BIRD_HEIGHT = 165;
    final static int FRAME_COUNT = 6;
    private int picNum =0;
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
    
    private Direction direction;
    private ArrayList<GamePiece> currentViewableGPs = new ArrayList<>();
    private int health;
    private int score;
    private JLabel fact;
    private int backgroundLocation;
    
    public View(Controller c){
        frame = new JFrame();
        cards = new JPanel(new CardLayout());
        
        createFrame(c);
        
        createStartScreen(c);
        createOspreyPanels();
        createHarrierRound();
        createQuizPanel();
        createGameOverPanel();
    }
    
    void createFrame(Controller c){
        frame.add(cards);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setFocusable(true);
        frame.addKeyListener(c);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    } 
    
    void createStartScreen(Controller c){
        BufferedImage startScreenImg = createImage("images/BirdImages/StartScreen.png");
        startScreen = new JPanel();
        startScreen.setLayout(null);
        c.getOspreyButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH/55));
        c.getHarrierButton().setFont(new Font("Agency FB", Font.BOLD, FRAME_WIDTH/55));
        c.getOspreyButton().setBounds(FRAME_WIDTH/10,(FRAME_HEIGHT*84)/100,FRAME_WIDTH/4,FRAME_HEIGHT/15);
        c.getHarrierButton().setBounds((FRAME_WIDTH*6)/10,(FRAME_HEIGHT*37)/100,FRAME_WIDTH/4,FRAME_HEIGHT/15);
        
        startScreen.add(c.getOspreyButton());
        startScreen.add(c.getHarrierButton());
    }
    void createOspreyPanels(){
        createInitialMapPanel();
        createOspreyRound1Panel();
        createOspreyMap1to2();
        createOspreyRound2Panel();
        createOspreyMap2to3();
        createOspreyNestPanel();
    }
    void createInitialMapPanel(){
        initialMap = new JPanel();
        BufferedImage mapImg = createImage("images/BirdImages/OspreyLevelScreen0.png");
    }
    void createOspreyRound1Panel(){
        ospreyRound1 =new JPanel();
        BufferedImage background = createImage("DNERRGameBackground.jpg");
        
    }
    void createOspreyMap1to2(){
        
    }
    void createOspreyRound2Panel(){
        
    }
    void createOspreyMap2to3(){
        
    }
    void createOspreyNestPanel(){
        
    }
    void createHarrierRound(){
        
    }
    void createQuizPanel(){
        
    }
    void createGameOverPanel(){
        
    }
    public void paint(Graphics g) {
    	
        paintBackground(g);
        picNum = (picNum + 1) % FRAME_COUNT;
        if (Model.specialFoodEaten() ) {
        	//displayFacts(g);
        }
        
      
        g.drawImage(flyForward[picNum], playerXLoc, playerYLoc, this);
        for(GamePiece gp : currentViewableGPs) {   
        	if (gp.isSpecialFood()) {
        		if(gp.getSprite().equals(Sprite.MOUSE)){ //mice
                    //micePicNum = (micePicNum + 1) % miceFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% MICE_FRAME_COUNT);
                    g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
                }
                else if(gp.getSprite().equals(Sprite.BUNNY)){ //bunny
                    //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% BUNNY_FRAME_COUNT);
                    g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
                }
                else if(gp.getSprite().equals(Sprite.SNAKE)){ //snake
                    //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% snakeFrameCount);
                    g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
                }
                else{// fish
                    //fishPicNum = (fishPicNum + 1) % fishFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% fishFrameCount);
                    g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
                } 
        	}
        	else if(gp.isFood()){
                if(gp.getSprite().equals(Sprite.MOUSE)){ //mice
                    //micePicNum = (micePicNum + 1) % miceFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% MICE_FRAME_COUNT);
                    g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), this);
                }
                else if(gp.getSprite().equals(Sprite.BUNNY)){ //bunny
                    //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% BUNNY_FRAME_COUNT);
                    g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), this);
                }
                else if(gp.getSprite().equals(Sprite.SNAKE)){ //snake
                    //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% snakeFrameCount);
                    g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(), this);
                }
                else{// fish
                    //fishPicNum = (fishPicNum + 1) % fishFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% fishFrameCount);
                    g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(), this);
                }  
            }
            else if (gp.isEnemy()){
                if(gp.getSprite().equals(Sprite.REDFOX)){ //red fox
                    //redFoxPicNum = (redFoxPicNum + 1) % redFoxFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% redFoxFrameCount);
                    g.drawImage(redFox[gp.getPicNum()], gp.getX(), gp.getY(), this);
                }
                else if(gp.getSprite().equals(Sprite.RACCOON)){ //raccoons
                    //raccoonPicNum = (raccoonPicNum + 1) % raccoonFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% raccoonFrameCount);
                    g.drawImage(raccoon[gp.getPicNum()], gp.getX(), gp.getY(), this);
                }
                else if(gp.getSprite().equals(Sprite.EAGLE)){ //eagles
                    //eaglePicNum = (eaglePicNum + 1) % eagleFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% eagleFrameCount);
                    g.drawImage(eagle[gp.getPicNum()], gp.getX(), gp.getY(), this);
                }
                else{ //planes
                    //planePicNum = (planePicNum + 1) % planeFrameCount;
                    gp.setPicNum((gp.getPicNum() + 1)% planeFrameCount);
                    g.drawImage(plane[gp.getPicNum()], gp.getX(), gp.getY(), this);
                }

            }   
            
        }
        g.setColor(Color.red);
        g.drawRect(10, 10, 100 * 2, 50);
        g.fillRect(10, 10, health * 2, 50);
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", 1, 20));
        g.drawRect(FRAME_WIDTH - 105, 20, 100, 50);
        g.drawString("Score: " + String.valueOf(score), FRAME_WIDTH - 100, 50);

      

  }
    
    
    public void paintBackground(Graphics g) {
    	
    	Image backgroundImage;
    	Image backgroundImageFlipped;
   		ImageIcon imgOsprey = new ImageIcon("DNERRGameBackground.jpg");
   		ImageIcon imgOsprey2 = new ImageIcon("DNERRGameBackgroundMirror.jpg");
   		ImageIcon imgHarrier = new ImageIcon("nature2.jpg");
   		ImageIcon imgHarrier2 = new ImageIcon("nature2Mirror.jpg");
   		if(Model.getBird().equals(Sprite.OSPREY)) {
   			backgroundImage = imgOsprey.getImage();
   			backgroundImageFlipped = imgOsprey2.getImage();
   		}
   		else {
   			backgroundImage = imgHarrier.getImage();
   			backgroundImageFlipped = imgHarrier2.getImage();
   		}
   		Graphics2D g2d = (Graphics2D)g;
   		g2d.drawImage(backgroundImage, -backgroundLocation, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
   		g2d.drawImage(backgroundImageFlipped, FRAME_WIDTH-backgroundLocation, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
   		for(int i=1; i<200; i++) {
   			if(i%2 == 0) {
   				g2d.drawImage(backgroundImageFlipped, (i*FRAME_WIDTH)+(FRAME_WIDTH-backgroundLocation), 0, FRAME_WIDTH, FRAME_HEIGHT, null);
   			}
   			else {
   				g2d.drawImage(backgroundImage, (i*FRAME_WIDTH)+(FRAME_WIDTH-backgroundLocation), 0, FRAME_WIDTH, FRAME_HEIGHT, null);
   			}
   		}
   		
    }
    
    public void setPanel(String s){
        if(s.equals("START")){
            currentPanel = startScreen;
        }
        else if(s.equals("INITIAL_MAP")){
            currentPanel = initialMap;
        }
        else if(s.equals("OSPREY_ROUND_ONE")){
            currentPanel = ospreyRound1;
        }
        else if(s.equals("MAP_1_TO_2")){
            currentPanel = map1to2;
        }
        else if(s.equals("OSPREY_ROUND_TWO")){
            currentPanel = ospreyRound2;
        }
        else if(s.equals("MAP_2_TO_3")){
            currentPanel = map2to3;
        }
        else if(s.equals("OSPREY_NEST")){
            currentPanel = ospreyNest;
        }
        else if(s.equals("HARRIER_ROUND")){
            currentPanel = harrierRound;
        }
        else if(s.equals("QUIZ")){
            currentPanel = quiz;
        }
        else if(s.equals("GAME_OVER")){
            currentPanel = gameOver;
        }
        
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
        health=h;
        score=s;
        currentViewableGPs = g;
        direction = dir;
        backgroundLocation+=25;
        frame.repaint();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getBirdWidth(){
        return BIRD_WIDTH;
    }
    public int getBirdHeight(){
        return BIRD_HEIGHT;
    }
    public int getFrameHeight(){
        return FRAME_HEIGHT;
    }
    public int getFrameWidth(){
        return FRAME_WIDTH;
    }
}
