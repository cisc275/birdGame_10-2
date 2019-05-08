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
	    	   if (updateImgPlayer % 2 == 0) {
	    		   picNum = (picNum + 1) % frameCount;
	    	   }
	    	   updateImgPlayer++;
	    	   
	    	   if (Model.specialFoodEaten() ) {
	    	   	displayFacts();
	    	   	Model.setSpecialFoodEaten(false);
	    	   }
	    	   
	    	 
	    	   g.drawImage(flyForward[picNum], playerXLoc, playerYLoc, this);
	    	   for(GamePiece gp : currentViewableGPs) {   
	    	   		drawGamePiece(g, gp);
	        g.setColor(Color.red);
	        g.drawRect(10, 10, 100 * 2, 50);
	        g.fillRect(10, 10, health * 2, 50);
	        g.setColor(Color.white);
	        g.setFont(new Font("Times New Roman", 1, 20));
	        g.drawRect(frameWidth - 105, 20, 100, 50);
	        g.drawString("Score: " + String.valueOf(score), frameWidth - 100, 50);

	      

	  }


}
}
