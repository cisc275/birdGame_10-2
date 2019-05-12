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
    public Enemy(int x, int y, Sprite enemyType){
    	setSpecialFood(false);
        setX(x);
        setY(y);
        setXIncr(10);
    	setYIncr(0);
    	setWidth(165);
    	setHeight(165);
    	setSprite(enemyType);
        setPicNum(0);
//        if(enemyType.equals(Type.EAGLE)){
//            setPicNum(6);
//        }
//        else if(enemyType.equals(Type.PLANE)){
//            setPicNum(1);
//        }
//        else{
//            setPicNum(4);
//        }
    	setDamage(20);
    	setOffsets(); //MIGHT NOT WORK

    }
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
    
    
}
