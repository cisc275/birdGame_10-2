/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controller class will handle flow of game and will take user input.
 * @author crnis
 */
public class Controller implements KeyListener{

    private Model model;
    private View view;

    /**
     *start() will be called from the main() method in the Main class and will 
     * have a loop to iterate through the game.
     */
    public void start() {

    }

    /**
     * keyTyped() will handle a KeyEvent that the user might perform.
     * @param e is a KeyEvent that a user might input (up, down). 
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * keyPressed() will handle the user input if they hold down the arrowkeys to 
     * move the bird.
     * @param e is a KeyEvent that a user might input (up, down).
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_UP){
            Model.direction = Direction.UP;
        }
        else if(key == KeyEvent.VK_DOWN){
            Model.direction = Direction.DOWN;
        }
    }

    /**
     * keyReleased will not be used for our game
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
