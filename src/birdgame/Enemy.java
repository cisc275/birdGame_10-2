/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package birdgame;

/**
 * Enemy inherits from the GamePiece class; contains int damage that has a value
 * for the amount of damage the enemy can do to the bird
 * @author crnis
 */
public class Enemy extends GamePiece {

    private int damage;
    public Enemy(int x, int y, Type enemyType){
        setX(x);
        setY(y);
        setXIncr(10);
    	setYIncr(0);
    	setWidth(165);
    	setHeight(165);
    	setType(enemyType);
    	setDamage(20);

    }
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
    
    
}
