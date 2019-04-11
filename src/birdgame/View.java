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

/**
 * View contains all the methods and attributes related directly to the display 
 * of the game.
 * @author crnis
 */
public class View extends JPanel {

    int picNum;
    int frameCount;
    BufferedImage[] flyForward;
    BufferedImage[] flyUp;
    BufferedImage[] flyDown;
    BufferedImage[] catchPrey;
    BufferedImage[] crash;
    BufferedImage[] enterNest;
    BufferedImage[] exitNest;
    BufferedImage[] miniMap1;
    BufferedImage[] miniMap2;
    BufferedImage[] miniMap3;
    BufferedImage[] miniMap4;
    JFrame frame;
    JPanel pane;
    int frameWidth;
    int frameHeight;
    int imgWidth;
    int imgHeight;
    int xLocation;
    int yLocation;
    Direction direction;
    boolean paused;

    /**
     * constructor will initialize JFrame and other components that will be on it.
     */

    public View() {

    }
    
    /**
     * loadImages() will load the BufferedImages into arrays.
     */

    public void loadImages() {

    }

    /**
     * createImage() will create a BufferedImage that will be loaded into an array
     * @return a BufferedImage
     */

    public BufferedImage createImage() {
        return null;
    }

    /**
     * paint() will be called to update the visuals of the game.
     * @param g is a Graphics object
     */

    public void paint(Graphics g) {

    }

    /**
     * update() will update the attributes within View based off of the values that
     * are determined in Model.
     * @param xLoc is the new x location for the player from Model
     * @param yLoc is the new y location for the player from Model
     * @param dir is the new direction for the player from Model
     */

    public void update(int xLoc, int yLoc, Direction dir) {

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

}