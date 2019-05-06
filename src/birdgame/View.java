package birdgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.util.ArrayList;

/**
 * View contains all the methods and attributes related directly to the display 
 * of the game.
 * @author crnis
 */
public class View extends JPanel {
//    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    final static int frameWidth = (int) rect.getWidth();
    final static int frameHeight = (int) rect.getHeight();
    final static int imageWidth = 184;
    final static int imageHeight = 165;
    final static int frameCount = 6;
    static int picNum =0;
    final static int miceFrameCount = 2;
    final static int bunnyFrameCount = 4;
    final static int redFoxFrameCount = 4;
    final static int raccoonFrameCount = 4;
    final static int fishFrameCount = 4;
    final static int snakeFrameCount = 4;
    final static int eagleFrameCount = 6;
    final static int planeFrameCount = 1;
    static int micePicNum = 0;
    static int bunnyPicNum = 0;
    static int redFoxPicNum = 0;
    static int raccoonPicNum = 0;
    static int fishPicNum = 0;
    static int snakePicNum = 0;
    static int eaglePicNum = 0;
    static int planePicNum = 0;
    int playerXLoc = 0;
    int playerYLoc = 0;
    int gpXLoc;
    int gpYLoc;
    BufferedImage[] flyForward = new BufferedImage[6];
    BufferedImage[] mice = new BufferedImage[2];
    BufferedImage[] bunny = new BufferedImage[4]; 
    BufferedImage[] redFox = new BufferedImage[4]; 
    BufferedImage[] raccoon = new BufferedImage[4];
    BufferedImage[] fish = new BufferedImage[4];
    BufferedImage[] snake = new BufferedImage[4];
    BufferedImage[] plane = new BufferedImage[1];
    BufferedImage[] eagle = new BufferedImage[6];
    //don't forget below
    BufferedImage[] catchPrey;
    BufferedImage[] crash;
    BufferedImage[] enterNest;
    BufferedImage[] exitNest;
    BufferedImage[] miniMap1;
    BufferedImage[] miniMap2;
    BufferedImage[] miniMap3;
    BufferedImage[] miniMap4;
    Image thoughtBubble;

    static JPanel thoughtbub;
    JFrame frameOsprey;

static JFrame frameHarrier;
    static JFrame frame2;
    static JPanel pane1;
    static JPanel pane2;
    Direction direction;
    int xLocation;
    int yLocation;
    boolean paused;
    ArrayList<GamePiece> currentViewableGPs = new ArrayList<>();
    int x = 0;
    int health;
    int score;
	private JLabel fact;
    static JButton osprey;
    static JButton harrier;
    static ImageIcon ospreyImg;
    static ImageIcon harrierImg;
    static JLabel ospreyFact1;
    static JLabel harrierFact1;
    static JLabel ospreyPic;
    static JLabel harrierPic;
    static ImageIcon natureImg;
    static JLabel naturePic;
    static JFrame frame3;
    static JLabel finalScore;
    static JLabel gameOver;
    static JLabel bubble;
    static boolean isDone;
    static JFrame levelStartFrame;
    static JPanel levelStartPanel;
    static JLabel levelStartLabel;
    static ImageIcon levelDisplayStart;
    
    

    /**
     * constructor will initialize JFrame and other components that will be on it.
     */

    public View() {
    	
        frameOsprey = new JFrame();
        frameOsprey.setContentPane(this);
        frameOsprey.setBackground(Color.white);
        frameOsprey.setSize(frameWidth, frameHeight);
        loadImages();
        frameOsprey.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frameOsprey.setUndecorated(true);
        frameOsprey.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frameOsprey.setVisible(false); 
//        frameHarrier = new JFrame();
//        frameHarrier.setContentPane(this);
//        frameHarrier.setBackground(Color.white);
//        frameHarrier.setSize(frameWidth, frameHeight);
//        loadImages();
//        frameHarrier.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//        frameHarrier.setUndecorated(true);
//        frameHarrier.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//        frameHarrier.setVisible(false); 

    }
    
    /**
     * loadImages() will load the BufferedImages into arrays.
     */

    public void loadImages() {
        for(int i = 0; i < frameCount; i++){
            flyForward[i] = createImage("images/BirdImages/Bird" + i + ".png");
        }
        for(int i = 0; i < miceFrameCount; i++){
            mice[i] = createImage("images/BirdImages/Mice" + i + ".png");
        }
        for(int i = 0; i < bunnyFrameCount; i++){
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
       		(300, 300, Image.SCALE_SMOOTH);

    }
    

    /**
     * createImage() will create a BufferedImage that will be loaded into an array
     * @return a BufferedImage
     */

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

    
    
//    public void paintBackground(Graphics g) {
//    	
//    	Image imgback;
//    	Image imgback2;
//   		ImageIcon imgOsprey = new ImageIcon("DNERRGameBackground.jpg");
//   		ImageIcon imgOsprey2 = new ImageIcon("DNERRGameBackgroundMirror.jpg");
//   		ImageIcon imgHarrier = new ImageIcon("nature2.jpg");
//   		ImageIcon imgHarrier2 = new ImageIcon("nature2Mirror.jpg");
//   		if(Model.getBird().equals(Sprite.OSPREY)) {
//   			imgback = imgOsprey.getImage();
//   			imgback2 = imgOsprey2.getImage();
//   		}
//   		else {
//   			imgback = imgHarrier.getImage();
//   			imgback2 = imgHarrier2.getImage();
//   		}
//   		Graphics2D g2d = (Graphics2D)g;
//   		g2d.drawImage(imgback, -x, 0, frameWidth, frameHeight, null);
//   		g2d.drawImage(imgback2, frameWidth-x, 0, frameWidth, frameHeight, null);
//   		for(int i=1; i<200; i++) {
//   			if(i%2 == 0) {
//   				g2d.drawImage(imgback2, (i*frameWidth)+(frameWidth-x), 0, frameWidth, frameHeight, null);
//   			}
//   			else {
//   				g2d.drawImage(imgback, (i*frameWidth)+(frameWidth-x), 0, frameWidth, frameHeight, null);
//   			}
//   		}
//   		
//    }
    
    
    
    /**
     * paint() will be called to update the visuals of the game.
     * @param g is a Graphics object
     */
    
    

//    public void paint(Graphics g) {
//    	
////        paintBackground(g);
//        picNum = (picNum + 1) % frameCount;
//        if (Model.specialFoodEaten() ) {
//        	displayFacts(g);
//        }
//        
//      
//        g.drawImage(flyForward[picNum], playerXLoc, playerYLoc, this);
//        for(GamePiece gp : currentViewableGPs) {   
//        	if (gp.isSpecialFood()) {
//        		if(gp.getSprite().equals(Sprite.MOUSE)){ //mice
//                    //micePicNum = (micePicNum + 1) % miceFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% miceFrameCount);
//                    g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
//                }
//                else if(gp.getSprite().equals(Sprite.BUNNY)){ //bunny
//                    //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% bunnyFrameCount);
//                    g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
//                }
//                else if(gp.getSprite().equals(Sprite.SNAKE)){ //snake
//                    //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% snakeFrameCount);
//                    g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
//                }
//                else{// fish
//                    //fishPicNum = (fishPicNum + 1) % fishFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% fishFrameCount);
//                    g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
//                } 
//        	}
//        	else if(gp.isFood()){
//                if(gp.getSprite().equals(Sprite.MOUSE)){ //mice
//                    //micePicNum = (micePicNum + 1) % miceFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% miceFrameCount);
//                    g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                }
//                else if(gp.getSprite().equals(Sprite.BUNNY)){ //bunny
//                    //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% bunnyFrameCount);
//                    g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                }
//                else if(gp.getSprite().equals(Sprite.SNAKE)){ //snake
//                    //snakePicNum = (snakePicNum + 1) % snakeFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% snakeFrameCount);
//                    g.drawImage(snake[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                }
//                else{// fish
//                    //fishPicNum = (fishPicNum + 1) % fishFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% fishFrameCount);
//                    g.drawImage(fish[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                }  
//            }
//            else if (gp.isEnemy()){
//                if(gp.getSprite().equals(Sprite.REDFOX)){ //red fox
//                    //redFoxPicNum = (redFoxPicNum + 1) % redFoxFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% redFoxFrameCount);
//                    g.drawImage(redFox[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                }
//                else if(gp.getSprite().equals(Sprite.RACCOON)){ //raccoons
//                    //raccoonPicNum = (raccoonPicNum + 1) % raccoonFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% raccoonFrameCount);
//                    g.drawImage(raccoon[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                }
//                else if(gp.getSprite().equals(Sprite.EAGLE)){ //eagles
//                    //eaglePicNum = (eaglePicNum + 1) % eagleFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% eagleFrameCount);
//                    g.drawImage(eagle[gp.getPicNum()], gp.getX(), gp.getY(), this);
//                }
//                else{ //planes
//                    //planePicNum = (planePicNum + 1) % planeFrameCount;
//                    gp.setPicNum((gp.getPicNum() + 1)% planeFrameCount);
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
//        g.drawRect(frameWidth - 105, 20, 100, 50);
//        g.drawString("Score: " + String.valueOf(score), frameWidth - 100, 50);
//
//      
//
//  }


    /**
     * update() will update the attributes within View based off of the values that
     * are determined in Model.
     * @param xLoc is the new x location for the player from Model
     * @param yLoc is the new y location for the player from Model
     * @param dir is the new direction for the player from Model
     */

    public void update(int xLoc, int yLoc, ArrayList<GamePiece> g, Direction dir, int h, int s) {
    	
        playerXLoc = xLoc;
        playerYLoc = yLoc;
        health=h;
        score=s;
        currentViewableGPs = g;
        direction = dir;
        x+=25;
        frameOsprey.repaint();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * displayStartScreen() will display the visual at the beginning of the game and
     * can be recalled when the player dies or goes to the next bird.
     */

    public void displayStartScreen() {
    	natureImg = new ImageIcon("images/BirdImages/StartScreen.png");

    	frame2 = new JFrame();
        osprey = new JButton("Play as Osprey");
        osprey.setFont(new Font("Agency FB", Font.BOLD, frameWidth/55));
        harrier = new JButton("Play as Northern Harrier");
        harrier.setFont(new Font("Agency FB", Font.BOLD, frameWidth/65));


    	osprey.setBounds(frameWidth/10,(frameHeight*84)/100,frameWidth/4,frameHeight/15);
    	harrier.setBounds((frameWidth*6)/10,(frameHeight*37)/100,frameWidth/4,frameHeight/15);

    	naturePic = new JLabel();
    	naturePic.setIcon(new ImageIcon(natureImg.getImage().getScaledInstance(frameWidth,frameHeight, Image.SCALE_SMOOTH)));
    	naturePic.setBounds(0,0,frameWidth, frameHeight);

        pane1 = new JPanel();
        pane1.setLayout(null);
        osprey.addActionListener(ae -> {
        	OspreyView.displayLevelStartScreen();
            frame2.dispose();
//            Model.setBird(Sprite.OSPREY);
            Main.birdCount++;
            Main.isOsprey=true;
            new Timer(5000, ae2 -> {
        		levelStartFrame.dispose();
        		Main.started = true;
              }).start();

        });
        harrier.addActionListener(ae -> {

            Main.isOsprey=false;
            frame2.dispose();
//            Model.setBird(Sprite.NORTHERN_HARRIER);
            Main.birdCount++;
            Main.started = true;
            
        });
        pane1.add(osprey);
        pane1.add(harrier);
//        pane1.add(ospreyPic);
//        pane1.add(harrierPic);
//        pane1.add(ospreyFact1);
//        pane1.add(harrierFact1);
        pane1.add(naturePic);
        frame2.add(pane1);
        frame2.setSize(frameWidth, frameHeight);
        frame2.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame2.setUndecorated(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frameOsprey.setVisible(true);
        frame2.setVisible(true);
    }

    /**
     * displayMiniMap() will be called every tick to update the bird's location on
     * the miniMap to see it's progress
     */

//    public void displayMiniMap() {
//
//    }

    /**
     * displayEndScreen() will be called at the end of the game once the player
     * has finished playing the game
     */

    public void displayEndScreen() {
    	frame3 = new JFrame();
    	naturePic = new JLabel();
    	naturePic.setIcon(new ImageIcon(natureImg.getImage().getScaledInstance(frameWidth,frameHeight, Image.SCALE_SMOOTH)));
    	naturePic.setBounds(0,0,frameWidth, frameHeight);
    	gameOver = new JLabel("Game Over");
    	gameOver.setFont(new Font("Times New Roman",1,70));
    	gameOver.setBounds(frameWidth/2-150,frameHeight/2-100,1000,200);
    	finalScore = new JLabel("Score: " + Player.getScore());
    	finalScore.setFont(new Font("Times New Roman",1,20));
    	finalScore.setBounds(frameWidth/2-100,frameHeight/2,100,100);
    	pane2 = new JPanel();
    	pane2.setLayout(null);
    	pane2.add(finalScore);
    	pane2.add(gameOver);
    	pane2.add(naturePic);
    	frame3.add(pane2);
    	frame3.setSize(frameWidth, frameHeight);
        frame3.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame3.setUndecorated(true);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	frame3.setVisible(true);
    	
    }
 public void displayFacts(Graphics g) {
    	
    	bubble = new JLabel(new ImageIcon(thoughtBubble));
    	System.out.println(Model.getCurrentFact());
    	fact = new JLabel(Model.getCurrentFact());
    	fact.setFont(new Font("Times New Roman",1,60));
    	g.drawImage(thoughtBubble, playerXLoc + 184, playerYLoc -200, this);
    	
    	
   
    	
    	
    }
    
//    public static void displayLevelStartScreen(){
//        levelStartFrame = new JFrame();
//        levelStartPanel = new JPanel();
//        levelStartLabel = new JLabel();
//        levelDisplayStart = new ImageIcon("images/BirdImages/OspreyLevelScreen0.png");
//        //add JLabel to JPanel
//        levelStartLabel.setIcon(new ImageIcon(levelDisplayStart.getImage().getScaledInstance(frameWidth,frameHeight, Image.SCALE_SMOOTH)));
//        levelStartLabel.setBounds(0,0,frameWidth,frameHeight);
//        levelStartPanel.setLayout(null);
//        levelStartPanel.add(levelStartLabel);
//        
//        //add JPanel to JFrame
//        levelStartFrame.add(levelStartPanel);
//        levelStartFrame.setSize(frameWidth, frameHeight);
//        levelStartFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//        levelStartFrame.setUndecorated(true);
//        levelStartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//    	levelStartFrame.setVisible(true);
//    }
//    public static void displayLevel1Transition(){
//        
//    }
//    public static void displayLevel2Transition(){
//        
//    }
   
    public int getWidth(){
        return frameWidth;
    }
    public int getHeight(){
        return frameHeight;
    }
    public int getImageWidth(){
        return imageWidth;
    }
    public int getImageHeight(){
        return imageHeight;
    }

}
