/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

/**
 * Enemy inherits from the GamePiece class; contains int damage that has a value
 * for the amount of damage the enemy can do to the bird
 *
 * @author crnis
 */
public class Enemy extends GamePiece {

    int damage;

    public Enemy(int x, int y) {
        xLocation = x;
        yLocation = y;
        xincr = 25;
        yincr = 0;
        width = 165;
        height = 165;
        type = "enemy";
        damage = 20;
    }
}
