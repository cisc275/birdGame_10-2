import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

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

	/**
     * will call the start() method in Controller
     * @param args
     */

    public static void main(String[] args) {
        Controller c = new Controller();
        started = false;
        boolean test=true;
        View.frame.addKeyListener(c);
    	View.displayStartScreen();
    	while(test) {
    		if(started==true) {
    			test=false;
        		c.start();
    		}
    		System.out.print("");
    	}

    }

}
