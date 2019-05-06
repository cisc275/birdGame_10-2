package birdgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HarrierSpecialFood extends SpecialFood{

	public HarrierSpecialFood(int x, int y, Sprite foodType) {
		super(x, y, foodType);
		factsAndQuestions = new HashMap<>();
		setFact();
		// TODO Auto-generated constructor stub
	}
	
	 public static void generateFactsAndQuestions() {
	    	HashMap<String,String[]> QandAsHarrier1 = new HashMap<>();
	    	String[] harrierFood = {"rodents", "fish", "eagles","plants", "rodents"};
	    	QandAsHarrier1.put("What do Northern Harriers eat?",harrierFood);
	    	factsAndQuestions.put("Northern Harriers Like to eat rodents like mice and bunnies", QandAsHarrier1);
	    	
	    	HashMap<String,String[]> QandAsHarrier2 = new HashMap<>();
	    	String[] harrierMigrate = {"They migrate to South America","They migrate to California", "They don't migrate", "They migrate to canada", "They don't migrate"};
	    	QandAsHarrier2.put("Where do Harriers migrate", harrierMigrate);
	    	factsAndQuestions.put("Northern Harriers are non-migratory birds",QandAsHarrier2);
	    	
	    	HashMap<String,String[]> QandAsHarrier3 = new HashMap<>();
	    	String[] harrierPred = {"Foxes", "Snakes","Cats","Humans","Foxes"};
	    	QandAsHarrier3.put("What is a major predator of Northern Harriers", harrierPred);
	    	factsAndQuestions.put("Foxes are a common predator on Northern Harriers", QandAsHarrier3);
	    	
	    	avaliableFacts = new ArrayList<String>(factsAndQuestions.keySet());
	    }

	    public void setFact() {

	    	int randomIndex = new Random().nextInt(avaliableFacts.size());
	    	System.out.println(avaliableFacts.size());
	   		fact = avaliableFacts.get(randomIndex);
	    		//avaliableFactsHarrier.remove(randomIndex);
	    		//Remove might be bugged

	    	
	    }

}
