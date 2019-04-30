package birdgame;
import java.util.Random;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * SpecialFood is food that may appear larger on screen with some glowing and 
 * will offer more of a score increment to the player provided they answer the 
 * question that is prompted correctly.
 * @author crnis
 */
public class SpecialFood extends Food {

    public SpecialFood(int x, int y, Type foodType, int currentBird) {
		super(x, y, foodType);
		initializeQandAs();
	}

	
    static HashMap<String, String[]> QuizOsprey;
    static HashMap<String, String[]> QuizHarrier;
    //Answer to question will always be repeated as the last option in the answers array

    /**
     * checkAnswer() will check if the player's answer is in line with the answerKey
     * @return true if player chooses right answer and false if player chooses
     * wrong answer
     */

    public boolean checkAnswer() {
        return true;
    }
    public static void initializeQandAs() {
    	QuizOsprey = new HashMap<>();
    	QuizHarrier = new HashMap<>();
    	
    	String[] ansOsp = {"1","4","5","answer","4"};
    	String[] ansHar = {"1","4","5","answer","5"};

    	QuizOsprey.put("Whats 2 + 2", ansOsp);
    	QuizHarrier.put("Whats 4 + 1", ansHar);
    	


   }

    /**
     * generateQuestion() will randomly select a question from the answerOptions
     * HashMap to ask the player.
     */

    public String getQuestion(int birdType) {
    	if (birdType == 0) {
    		List<String> questionList = new ArrayList<String>(QuizOsprey.keySet());
    		int randomIndex = new Random().nextInt(questionList.size());
    		return questionList.get(randomIndex);
    	}
    	if (birdType == 1) {
    		List<String> questionList = new ArrayList<String>(QuizHarrier.keySet());
        	int randomIndex = new Random().nextInt(questionList.size());
        	return questionList.get(randomIndex);
    	}
    	else return "";
    	
    }

}
