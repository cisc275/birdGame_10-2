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
import java.awt.Image;
import java.awt.Point;
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
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final static int frameWidth = (int) screenSize.getWidth();
    final static int frameHeight = (int) screenSize.getHeight();
    final static int imageWidth = 165;
    final static int imageHeight = 165;
    final static int frameCount = 10;
    static int picNum =0;
    int playerXLoc = 0;
    int playerYLoc = 0;
    int gpXLoc;
    int gpYLoc;
    BufferedImage[] flyForward = new BufferedImage[10];
    BufferedImage[] flyUp = new BufferedImage[10];
    BufferedImage[] flyDown = new BufferedImage[10];
    //don't forget below
    BufferedImage[] catchPrey;
    BufferedImage[] crash;
    BufferedImage[] enterNest;
    BufferedImage[] exitNest;
    BufferedImage[] miniMap1;
    BufferedImage[] miniMap2;
    BufferedImage[] miniMap3;
    BufferedImage[] miniMap4;
    static JFrame frame;
    static JFrame frame2;
    static JPanel pane1;
    JPanel pane2;
    Direction direction;
    int xLocation;
    int yLocation;
    boolean paused;
    ArrayList<GamePiece> currentViewableGPs = new ArrayList<>();
    int x = 0;
    int health;
    int score;
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

    


    /**
     * constructor will initialize JFrame and other components that will be on it.
     */

    public View() {
        frame = new JFrame();
        frame.setContentPane(this);
        frame.setBackground(Color.white);
        frame.setSize(frameWidth, frameHeight);
        loadImages();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(false); 
//   		frame2.drawImage(ospreyImg.getImage(),100,100,100,100, null);

    }
    
    /**
     * loadImages() will load the BufferedImages into arrays.
     */

    public void loadImages() {
        //use orc images for Alpha, use actual bird images later
        
        //fly forward
        BufferedImage forwardImg = createImage("images/BirdImages/OspreyTest.png");
        for (int j = 0; j < frameCount; j++) {
            flyForward[j] = forwardImg.getSubimage(imageWidth * j, 30, imageWidth, imageHeight);
        }
        
        //fly up
        BufferedImage upImg = createImage("images/orc/orc_forward_north.png");
        for (int j = 0; j < frameCount; j++) {
            flyUp[j] = upImg.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
        }
        
        //fly down
        BufferedImage downImg = createImage("images/orc/orc_forward_south.png");
        for (int j = 0; j < frameCount; j++) {
            flyDown[j] = downImg.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
        }
    }

    /**
     * createImage() will create a BufferedImage that will be loaded into an array
     * @return a BufferedImage
     */

    public BufferedImage createImage(String path) {
        BufferedImage buff;
        try {
            buff = ImageIO.read(new File("orc/"
                    + path + ".png"));
            return buff;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
    public void paintBackground(Graphics g) {
    	Image img11;
    	Image img22;
   		ImageIcon img1 = new ImageIcon("DNERRGameBackground.jpg");
   		ImageIcon img2 = new ImageIcon("DNERRGameBackgroundMirror.jpg");
   		img11 = img1.getImage();
   		img22 = img2.getImage();
   		Graphics2D g2d = (Graphics2D)g;
   		g2d.drawImage(img11, -x, 0, frameWidth, frameHeight, null);
   		g2d.drawImage(img22, frameWidth-x, 0, frameWidth, frameHeight, null);
   		for(int i=1; i<200; i++) {
   			if(i%2 == 0) {
   				g2d.drawImage(img22, (i*frameWidth)+(frameWidth-x), 0, frameWidth, frameHeight, null);

   			}
   			else {
   				g2d.drawImage(img11, (i*frameWidth)+(frameWidth-x), 0, frameWidth, frameHeight, null);

   			}

   		}
    }
    
    
    
    /**
     * paint() will be called to update the visuals of the game.
     * @param g is a Graphics object
     */
    
    
    public void paint(Graphics g) {

   		

   		paintBackground(g);
        picNum = (picNum + 1) % frameCount;
        if(direction == Direction.UP){
            g.drawImage(flyUp[picNum], playerXLoc, playerYLoc,Color.gray, this);
            for(GamePiece gp: currentViewableGPs){
                BufferedImage bf = null;
                try {
                    bf = ImageIO.read(new File("orc/orc_forward_west.png"));
                
                } catch (IOException e) {
                    e.printStackTrace();
                } 
                g.drawImage(flyForward[picNum], gp.getX(), gp.getY(), Color.gray, this);
            }
        }
        else if(direction == Direction.DOWN){
            g.drawImage(flyDown[picNum], playerXLoc, playerYLoc,Color.gray, this);
            for(GamePiece gp: currentViewableGPs){
                BufferedImage bf = null;
                try {
                    bf = ImageIO.read(new File("orc/orc_forward_west.png"));
                
                } catch (IOException e) {
                    e.printStackTrace();
                } 
                g.drawImage(flyForward[picNum], gp.getX(), gp.getY(), Color.gray, this);
            }
        }
        else{
            g.drawImage(flyForward[picNum], playerXLoc, playerYLoc,Color.gray, this);
            for(GamePiece gp: currentViewableGPs){
                BufferedImage bf = null;
                try {
                    bf = ImageIO.read(new File("orc/orc_forward_west.png"));
                
                } catch (IOException e) {
                    e.printStackTrace();
                } 
                g.drawImage(flyForward[picNum], gp.getX(), gp.getY(), Color.gray, this);
            }
        }
        g.setColor(Color.red);
        g.drawRect(10, 10, 100, 30);
        g.fillRect(10, 10, health, 30);
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", 1, 20)); 
        g.drawRect(frameWidth-105, 20, 100, 50);
        g.drawString("Score: " + String.valueOf(score), frameWidth - 100, 50);
   		
    }

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
        frame.repaint();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * displayStartScreen() will display the visual at the beginning of the game and
     * can be recalled when the player dies or goes to the next bird.
     */

    public static void displayStartScreen() {
    	natureImg = new ImageIcon("StartScreen.jpg");
    	ospreyImg = new ImageIcon("osprey.jpg");
    	harrierImg = new ImageIcon("NorthernHarrier.jpg");
    	frame2 = new JFrame();
        osprey = new JButton("Osprey");
        harrier = new JButton("Northern Harrier");
    	osprey.setBounds(0,0,frameWidth/2,frameHeight/10);
    	harrier.setBounds(frameWidth/2,0,frameWidth/2,frameHeight/10);
    	ospreyPic = new JLabel();
    	ospreyPic.setIcon(new ImageIcon(ospreyImg.getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH)));
    	ospreyPic.setBounds(200,100,300,300);
    	harrierPic = new JLabel();
    	harrierPic.setIcon(new ImageIcon(harrierImg.getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH)));
    	harrierPic.setBounds(200+frameWidth/2,100,300,300);
    	naturePic = new JLabel();
    	naturePic.setIcon(new ImageIcon(natureImg.getImage().getScaledInstance(frameWidth,frameHeight, Image.SCALE_SMOOTH)));
    	naturePic.setBounds(0,0,frameWidth, frameHeight);
    	ospreyFact1 = new JLabel("Osprey is a bird");
    	ospreyFact1.setFont(new Font("Times New Roman",1,20));;
    	ospreyFact1.setBounds(200,500,frameWidth/2,frameHeight/4);
    	harrierFact1 = new JLabel("Northern Harrier is a bird");
    	harrierFact1.setFont(new Font("Times New Roman",1,20));;
    	harrierFact1.setBounds(200+frameWidth/2,500,frameWidth/2,frameHeight/4);
        pane1 = new JPanel();
        pane1.setLayout(null);
        osprey.addActionListener(ae->{frame2.dispose(); Main.started = true; });
        harrier.addActionListener(ae->{frame2.dispose(); Main.started = true;});
        pane1.add(osprey);
        pane1.add(harrier);
        pane1.add(ospreyPic);
        pane1.add(harrierPic);
        pane1.add(ospreyFact1);
        pane1.add(harrierFact1);
        pane1.add(naturePic);
        frame2.add(pane1);
        frame2.setSize(frameWidth, frameHeight);
        frame2.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame2.setUndecorated(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);
        frame2.setVisible(true);
    }

    /**
     * displayMiniMap() will be called every tick to update the bird's location on
     * the miniMap to see it's progress
     */

    public void displayMiniMap() {

    }

    /**
     * displayEndScreen() will be called at the end of the game once the player
     * has finished playing the game
     */

    public void displayEndScreen() {

    }
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
