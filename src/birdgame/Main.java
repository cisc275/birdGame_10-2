package birdgame;

import java.io.Serializable;


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
public class Main implements Serializable {

    /**
     * main instantiates a controller and then calls the start() method in Controller
     *
     * @param String[] args
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.start();
    }
}
