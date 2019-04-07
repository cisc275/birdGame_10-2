/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

/**
 *
 * @author crnis
 */
public class Player extends GamePiece {

    int health;
    int score;

//    public void move() {
//
//    }
    
    /*
    isAlive() returns true if the player is still alive and false otherwise
    */
    public boolean isAlive() {
        return true;
    }

    /*
    checkCollision() returns true if the player collides with another GamePiece
    and false if otherwise
    */
    public boolean checkCollision() {
        return true;
    }

//    public void eat() {
//
//    }
//
//    public void die() {
//
//    }
//
//    public void nest() {
//
//    }
//
//    public void collide() {
//
//    }

}
