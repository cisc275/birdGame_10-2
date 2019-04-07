/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdgame;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author crnis
 */
public class SpecialFood extends Food {

    HashMap<String, String> answerKey;
    HashMap<String, String[]> answerOptions;

    /*
    checkAnswer() will return true if the player chooses the right answer to the
    question and will return false if player chooses the wrong answer
    */
    public boolean checkAnswer() {
        return true;
    }

    /*
    generateQuestion() will randomly select a question from the answerOptions
    HashMap to ask the player
    */
    public void generateQuestion() {

    }

}
