/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package birdgame;

/**
 * Food inherits from GamePiece class; contains int foodValue that has a value for
 * the score increment that a particular food may have.
 * @author crnis
 */
public class Food extends GamePiece {
	
    private int foodValue;
    protected boolean isSpecialFood;
    public Food(int x, int y, Sprite foodType){
    	isSpecialFood = false;
        setX(x);
        setY(y);
        setXIncr(25);
    	setYIncr(0);
    	setWidth(165);
    	setHeight(165);
    	setSprite(foodType);
        setPicNum(0);
    	setFoodValue(10);
    	setOffsets(); //MIGHT NOT WORK
    }

	public int getFoodValue() {
		return foodValue;
	}

	public void setFoodValue(int foodValue) {
		this.foodValue = foodValue;
	}
	public boolean isSpecialFood() {
		return isSpecialFood;
	}
	public void setSpecialFood(boolean bool) {
		isSpecialFood = bool;
	}
}

