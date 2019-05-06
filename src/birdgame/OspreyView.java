package birdgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OspreyView extends View{
	
	public static void displayLevelStartScreen(){
        levelStartFrame = new JFrame();
        levelStartPanel = new JPanel();
        levelStartLabel = new JLabel();
        levelDisplayStart = new ImageIcon("images/BirdImages/OspreyLevelScreen0.png");
        //add JLabel to JPanel
        levelStartLabel.setIcon(new ImageIcon(levelDisplayStart.getImage().getScaledInstance(frameWidth,frameHeight, Image.SCALE_SMOOTH)));
        levelStartLabel.setBounds(0,0,frameWidth,frameHeight);
        levelStartPanel.setLayout(null);
        levelStartPanel.add(levelStartLabel);
        
        //add JPanel to JFrame
        levelStartFrame.add(levelStartPanel);
        levelStartFrame.setSize(frameWidth, frameHeight);
        levelStartFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        levelStartFrame.setUndecorated(true);
        levelStartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	levelStartFrame.setVisible(true);
    }
    public static void displayLevel1Transition(){
        
    }
    public static void displayLevel2Transition(){
        
    }
    
 public void paintBackground(Graphics g) {
    	
    	Image imgback;
    	Image imgback2;
   		ImageIcon imgOsprey = new ImageIcon("DNERRGameBackground.jpg");
   		ImageIcon imgOsprey2 = new ImageIcon("DNERRGameBackgroundMirror.jpg");
   		imgback = imgOsprey.getImage();
   		imgback2 = imgOsprey2.getImage();


   		Graphics2D g2d = (Graphics2D)g;
   		g2d.drawImage(imgback, -x, 0, frameWidth, frameHeight, null);
   		g2d.drawImage(imgback2, frameWidth-x, 0, frameWidth, frameHeight, null);
   		for(int i=1; i<200; i++) {
   			if(i%2 == 0) {
   				g2d.drawImage(imgback2, (i*frameWidth)+(frameWidth-x), 0, frameWidth, frameHeight, null);
   			}
   			else {
   				g2d.drawImage(imgback, (i*frameWidth)+(frameWidth-x), 0, frameWidth, frameHeight, null);
   			}
   		}
   		
    }
 
 public void paint(Graphics g) {
 	
   paintBackground(g);
   picNum = (picNum + 1) % frameCount;
   if (Model.specialFoodEaten() ) {
   	displayFacts(g);
   }
   
 
   g.drawImage(flyForward[picNum], playerXLoc, playerYLoc, this);
   for(GamePiece gp : currentViewableGPs) {   
   	if (gp.isSpecialFood()) {

           
           if(gp.getSprite().equals(Sprite.SNAKE)){ //snake
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

           if(gp.getSprite().equals(Sprite.SNAKE)){ //snake
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

           if(gp.getSprite().equals(Sprite.EAGLE)){ //eagles
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
   g.drawRect(frameWidth - 105, 20, 100, 50);
   g.drawString("Score: " + String.valueOf(score), frameWidth - 100, 50);

 

}


}
