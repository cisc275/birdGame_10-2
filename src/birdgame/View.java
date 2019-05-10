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
    private JPanel OspreyRound1;
    private JPanel Map1to2;
    private JPanel OspreyRound2;
    private JPanel Map2to3;
    private JPanel OspreyNest;
    private JPanel HarrierRound;
    private JPanel Quiz;
    private JPanel GameOver;
    
    private Direction direction;
    private ArrayList<GamePiece> currentViewableGPs = new ArrayList<>();
    private int health;
    private int score;
    private JLabel fact;
    
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
        ImageIcon startScreenImg = new ImageIcon("images/BirdImages/StartScreen.png");
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
        
    }
    void createOspreyRound1Panel(){
        
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
    
}
