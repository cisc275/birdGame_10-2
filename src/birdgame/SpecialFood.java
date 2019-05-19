package birdgame;

import java.io.Serializable;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */

/**
 * SpecialFood is food that may appear larger on screen with some glowing and
 * will offer more of a score increment to the player provided they answer the
 * question that is prompted correctly.
 *
 * @author crnis
 */
public class SpecialFood extends Food implements Serializable {
    final static int INITIAL_X_INCREASE = 18;

    protected String fact;

    public SpecialFood(int x, int y, Sprite foodType) {
        super(x, y, foodType);
        setSpecialFood(true);

        setXIncr(INITIAL_X_INCREASE);

    }

}
