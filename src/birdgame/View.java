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
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.KeyStroke;
import java.util.ArrayList;

/**
 * View contains all the methods and attributes related directly to the display 
 * of the game.
 * @author crnis
 */
public class View extends JPanel {
    final static int frameWidth = 1000;
    final static int frameHeight = 1000;
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
    JPanel pane;
    Direction direction;
    int xLocation;
    int yLocation;
    boolean paused;
    ArrayList<GamePiece> currentViewableGPs;

    /**
     * constructor will initialize JFrame and other components that will be on it.
     */

    public View() {
        frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        loadImages();
        frame.setVisible(true);
        currentViewableGPs = new ArrayList<GamePiece>();
    }
    
    /**
     * loadImages() will load the BufferedImages into arrays.
     */

    public void loadImages() {
        //use orc images for Alpha, use actual bird images later
        
        //fly forward
        BufferedImage forwardImg = createImage("orc_forward_east");
        for (int j = 0; j < frameCount; j++) {
            flyForward[j] = forwardImg.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
        }
        
        //fly up
        BufferedImage upImg = createImage("orc_forward_north");
        for (int j = 0; j < frameCount; j++) {
            flyUp[j] = upImg.getSubimage(imageWidth * j, 0, imageWidth, imageHeight);
        }
        
        //fly down
        BufferedImage downImg = createImage("orc_forward_south");
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
            buff = ImageIO.read(new File("images/orc/"
                    + path + ".png"));
            return buff;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * paint() will be called to update the visuals of the game.
     * @param g is a Graphics object
     */

    public void paint(Graphics g) {
        picNum = (picNum + 1) % frameCount;
        if(direction == Direction.UP){
            
        }
        else if(direction == Direction.DOWN){
            
        }
        else{
            g.drawImage(flyForward[picNum], playerXLoc, playerYLoc,Color.gray, this);
            for(GamePiece gp: currentViewableGPs){
                BufferedImage bf = null;
                try {
                    bf = ImageIO.read(new File("images/square.png"));
                
                } catch (IOException e) {
                    e.printStackTrace();
                } 
                g.drawImage(bf, gp.getX(), gp.getY(), Color.gray, this);
            }
        }
    }

    /**
     * update() will update the attributes within View based off of the values that
     * are determined in Model.
     * @param xLoc is the new x location for the player from Model
     * @param yLoc is the new y location for the player from Model
     * @param dir is the new direction for the player from Model
     */

    public void update(int xLoc, int yLoc, ArrayList<GamePiece> g, Direction dir) {
        playerXLoc = xLoc;
        playerYLoc = yLoc;
        currentViewableGPs = g;
        direction = dir;
        
    }

    /**
     * displayStartScreen() will display the visual at the beginning of the game and
     * can be recalled when the player dies or goes to the next bird.
     */

    public void displayStartScreen() {

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
