/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

/**
 * Player class contains all the attributes and methods related to the bird (user).
 * @author crnis
 */
public class Player extends GamePiece {

    int health;
    int score;

    /**
     * isAlive() checks if the player is still alive
     * @return true if player is still alive and false otherwise
     */

    public boolean isAlive() {
        return true;
    }

    /**
     * checks if the Player collides with a GamePiece
     * @return true if player collides with GamePiece and false otherwise
     */

    public boolean checkCollision() {
        return true;
    }

}
