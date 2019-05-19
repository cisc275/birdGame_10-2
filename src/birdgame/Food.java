/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package birdgame;

import java.io.Serializable;

/**
 * Food inherits from GamePiece class; contains int foodValue that has a value for
 * the score increment that a particular food may have.
 * @author crnis
 */
public class Food extends GamePiece implements Serializable{
	
    private int foodValue;
    public Food(int x, int y, Sprite foodType){
    	setSpecialFood(false);
        setX(x);
        setY(y);
        setXIncr(14);
    	setYIncr(0);
    	setWidth(165);
    	setHeight(165);
    	setSprite(foodType);
        setPicNum(0);
    	setFoodValue(40);
    	setOffsets(); //MIGHT NOT WORK
    }

	public int getFoodValue() {
		return foodValue;
	}

	public void setFoodValue(int foodValue) {
		this.foodValue = foodValue;
	}
	
	
}

