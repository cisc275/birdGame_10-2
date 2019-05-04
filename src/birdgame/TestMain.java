package birdgame;

import java.util.ArrayList;
import java.util.Arrays;

public class TestMain {
public static void main(String[] args) {
	String[] ansOsp = {"1","4","5","6","4"};
	String[] ansHar = {"1","4","5","7","5"};
	Quiz.QuizOsprey.put("Whats 2 + 2", ansOsp);
	Quiz.QuizHarrier.put("Whats 4 + 1", ansHar);
	
	String question = Quiz.getQuestion(Sprite.OSPREY);
	System.out.println(question);
	String[] answerAndSolution = Quiz.getAnswersAndSolution(Sprite.OSPREY, question);
	System.out.println(Arrays.deepToString(answerAndSolution));
	System.out.println(Arrays.deepToString(Quiz.getAnswerOptions(answerAndSolution)));
	System.out.println(Quiz.checkAnswer(answerAndSolution, "4"));
	
	
}
}
