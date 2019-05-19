package birdgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Quiz implements Serializable{
	static HashMap<String, String[]> QuizOsprey = new HashMap<>();
    static HashMap<String, String[]> QuizHarrier = new HashMap<>();
    
	
    //Check answers compares the user inputted answer against the correct answer
    // which is located at the end of the String array returned by getAnswersAndSolution
    public static boolean checkAnswer(String[] answers, String userAns) {
    	String rightAns = answers[answers.length -1];
    	return rightAns.equals(userAns);
    }
    //Returns the answers and solutions for a given question, correct answer is repeated at the
    //end of the String array
    public static String[] getAnswersAndSolution(Sprite bird, String question) {
    	if (bird.equals(Sprite.NORTHERN_HARRIER)) {
    		return QuizHarrier.get(question);
    	}
    	else {
    		return QuizOsprey.get(question);
    	}
    }
    //Randomly chooses a question out of the avaliable questions for a given bird
    public static String getQuestion(Sprite bird) {
    	if (bird.equals(Sprite.OSPREY)) {
    		List<String> questionList = new ArrayList<String>(QuizOsprey.keySet());
    		int randomIndex = new Random().nextInt(questionList.size());
    		String question = questionList.get(randomIndex);
    		return question;

    	}
    	else {
    		List<String> questionList = new ArrayList<String>(QuizHarrier.keySet());
    		System.out.println(questionList); //Question is normal here
    		int randomIndex = new Random().nextInt(questionList.size());
    		String question = questionList.get(randomIndex);
    		return question;
    	}
    	
    	
    }
    //Gets the answer options that will be visible to the player, does not include
    //The correct answer which appears at the end of the array returned by getAnswerAndSolutions
    public static String[] getAnswerOptions(String[] answers) {
    	String[] validAnswers = Arrays.copyOfRange(answers, 0, answers.length -1);
    	return validAnswers;
    }

}
