/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.io.Serializable;

/**
 * Enemy inherits from the GamePiece class; contains int damage that has a value
 * for the amount of damage the enemy can do to the bird
 *
 * @author crnis
 */
public class Enemy extends GamePiece implements Serializable {

    final static int INITIAL_DAMAGE = 50;
    final static int INITIAL_X_INCREASE = 14;
    final static int INITIAL_ENEMY_WIDTH = 165;
    final static int INITIAL_ENEMY_HEIGHT = 165;

    private int damage;

    public Enemy(int x, int y, Sprite enemyType) {
        setSpecialFood(false);
        setX(x);
        setY(y);
        setXIncr(INITIAL_X_INCREASE);
        setYIncr(0);
        setWidth(INITIAL_ENEMY_WIDTH);
        setHeight(INITIAL_ENEMY_HEIGHT);
        setSprite(enemyType);
        setPicNum(0);
        setDamage(INITIAL_DAMAGE);
        setOffsets();

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
