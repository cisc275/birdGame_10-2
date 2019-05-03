package birdgame;

import java.util.ArrayList;

public class TestMain {
public static void main(String[] args) {
	SpecialFood.initializeQandAs();
	SpecialFood special = new SpecialFood(1,1,Type.BUNNY,1);
	ArrayList<String[]> questionGot = special.getQuestion(1);
	System.out.println(questionGot.get(0));
	String[] answers = special.getAnswers(questionGot);
	System.out.println(answers);
	System.out.println(SpecialFood.checkAnswer(answers, "4"));
}
}
