package birdgame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Main contains the main() method
 * @author crnis
 */
public class Main {
    public static boolean started;
    public static boolean started2;
    public static int birdCount;
	public static boolean playedBoth;

	/**
     * will call the start() method in Controller
     * @param args
     */

    public static void main(String[] args) {
        Controller c1 = new Controller();
        Controller c2 = new Controller();
        started = false;
        started2 = false;
        boolean test=true;
        playedBoth = false;
        birdCount = 0;
        c1.getView().frameOsprey.addKeyListener(c1);
        c2.getView().frameOsprey.addKeyListener(c2);
        c1.getView().displayStartScreen();
        
        
    	while(test) {
    		if(started==true) {
    			test=false;
        		c1.start();
    		}
    		
    		//if you want only one game played
//    		if (Player.getHealth()<=0) {
//    			c1.getView().displayEndScreen();
//				c1.getView().frameOsprey.dispose(); 
//    		}
    		
    		//if you want both games played
    		while(Player.getHealth()<=0) {

    			if(birdCount ==2) {
    				c1.getView().displayEndScreen();
					c1.getView().frameOsprey.dispose(); 
					break;
    			}
    			else {
					c1.getView().displayStartScreen();
    				c1.getModel().getPlayer().reset();
    				c1.start();
    			}

    		}

    		System.out.print("");
    	}

    }

}
