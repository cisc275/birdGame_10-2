package birdgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.Serializable;

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
public class Main implements Serializable{
    /**
     * will call the start() method in Controller
     *
     * @param args
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.start();
    }
}