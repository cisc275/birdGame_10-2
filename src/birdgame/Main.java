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
 *
 * @author crnis
 */
public class Main {

    public static boolean started;
    public static boolean started2;
    public static int birdCount;
    public static boolean playedBoth;
	public static boolean isOsprey;
	public static Controller c1;
	public static Controller c2;
	
    /**
     * will call the start() method in Controller
     *
     * @param args
     */
    public static void main(String[] args) {
        c1 = new Controller();
        c2 = new Controller();
        started = false;
        started2 = false;
        boolean test = true;
        playedBoth = false;
        birdCount = 0;
        isOsprey=false;
        c1.getView().frameOsprey.addKeyListener(c1);
        c2.getView().frameOsprey.addKeyListener(c2);
        c1.getView().displayStartScreen();

        while (test) {
            if (started == true) {
                test = false;
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
    				c2.getView().displayEndScreen();
					c2.getView().frameOsprey.dispose(); 
					break;
    			}
    			else {
					c2.getView().displayStartScreen();
					c1.getView().frameOsprey.dispose(); 
					
					if(isOsprey==false) {
						c2.getModel().setBird(Sprite.OSPREY);
					}
					else {
						c2.getModel().setBird(Sprite.NORTHERN_HARRIER);
					}
					c2.getModel().getPlayer().reset();
    				c2.start();
    			}

    		}

            System.out.print("");
        }

    }

}
