/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.io.Serializable;

/**
 * Food inherits from GamePiece class; contains int foodValue that has a value
 * for the score increment that a particular food may have.
 *
 * @author crnis
 */
public class Food extends GamePiece implements Serializable {

    final static int INITIAL_FOOD_VALUE = 40;
    final static int INITIAL_X_INCREASE = 14;
    final static int INITIAL_ENEMY_WIDTH = 165;
    final static int INITIAL_ENEMY_HEIGHT = 165;

    private int foodValue;

    /**
     * 
     */
    public Food(int x, int y, Sprite foodType) {
        setSpecialFood(false);
        setX(x);
        setY(y);
        setXIncr(INITIAL_X_INCREASE);
        setYIncr(0);
        setWidth(INITIAL_ENEMY_WIDTH);
        setHeight(INITIAL_ENEMY_HEIGHT);
        setSprite(foodType);
        setPicNum(0);
        setFoodValue(INITIAL_FOOD_VALUE);
        setOffsets();
    }

    /**
     * 
     */
    public int getFoodValue() {
        return foodValue;
    }

    /**
     * 
     */
    public void setFoodValue(int foodValue) {
        this.foodValue = foodValue;
    }

}
