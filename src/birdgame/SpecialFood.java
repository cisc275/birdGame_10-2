package birdgame;

import java.io.Serializable;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */

/**
 * SpecialFood is food with a glowing effect, gives the user a fact to be quizzed on later, and sets theur health to full
 *
 * @author crnis
 */

public class SpecialFood extends Food implements Serializable {
    final static int INITIAL_X_INCREASE = 18;

    protected String fact;

    /**
     * SpecialFood constructor takes 3 parameters and calls the Food constructor with those values, then sets initial SpecialFood values
     * 
     * @param int x is the x location of the SpecialFood
     * @param int y is the y location of the SpecialFood
     * @param Sprite foodType is the Sprite telling the user the exact animal
     */
    public SpecialFood(int x, int y, Sprite foodType) {
        super(x, y, foodType);
        setSpecialFood(true);
        setXIncr(INITIAL_X_INCREASE);
    }
}