package birdgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class HarrierView extends View{
	
	 public void displayMiniMap() {

	    }
	 
	 public void paintBackground(Graphics g) {
	    	
	    	Image imgback;
	    	Image imgback2;
	   		ImageIcon imgHarrier = new ImageIcon("nature2.jpg");
	   		ImageIcon imgHarrier2 = new ImageIcon("nature2Mirror.jpg");

	   		imgback = imgHarrier.getImage();
	   		imgback2 = imgHarrier2.getImage();
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
	        		if(gp.getSprite().equals(Sprite.MOUSE)){ //mice
	                    //micePicNum = (micePicNum + 1) % miceFrameCount;
	                    gp.setPicNum((gp.getPicNum() + 1)% miceFrameCount);
	                    g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
	                }
	                else if(gp.getSprite().equals(Sprite.BUNNY)){ //bunny
	                    //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
	                    gp.setPicNum((gp.getPicNum() + 1)% bunnyFrameCount);
	                    g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(),Color.RED, this);
	                }
	                
	        	}
	        	else if(gp.isFood()){
	                if(gp.getSprite().equals(Sprite.MOUSE)){ //mice
	                    //micePicNum = (micePicNum + 1) % miceFrameCount;
	                    gp.setPicNum((gp.getPicNum() + 1)% miceFrameCount);
	                    g.drawImage(mice[gp.getPicNum()], gp.getX(), gp.getY(), this);
	                }
	                else if(gp.getSprite().equals(Sprite.BUNNY)){ //bunny
	                    //bunnyPicNum = (bunnyPicNum + 1) % bunnyFrameCount;
	                    gp.setPicNum((gp.getPicNum() + 1)% bunnyFrameCount);
	                    g.drawImage(bunny[gp.getPicNum()], gp.getX(), gp.getY(), this);
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
