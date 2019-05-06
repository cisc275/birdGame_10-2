package birdgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class OspreySpecialFood extends SpecialFood {


	public OspreySpecialFood(int x, int y, Sprite foodType) {
		super(x, y, foodType);
		factsAndQuestions = new HashMap<>();
		setFact();
		// TODO Auto-generated constructor stub
	}
	
	 public static void generateFactsAndQuestions() {

	    	HashMap<String,String[]> QandAsOsprey1 = new HashMap<>();
	    	String[] OspreyFood = {"Mice and Rabbits", "Snakes and Fish", "Raccoons", "Sankes and Fish"};
	    	QandAsOsprey1.put("What do Ospreys eat?", OspreyFood);
	    	factsAndQuestions.put("Ospreys like to eat Snakes and Fish", QandAsOsprey1);
	    	
	    	HashMap<String,String[]> QandAsOsprey2 = new HashMap<>();
	    	String[] OspreyMigrate = {"They migrate to South America","They migrate to California", "They don't migrate", "They migrate to canada", "They migrate to South America"};
	    	QandAsOsprey2.put("Where do Ospreys migrate", OspreyMigrate);
	    	factsAndQuestions.put("Ospreys migrate to South America for the winter", QandAsOsprey2);
	    	
	    	HashMap<String,String[]> QandAsOsprey3 = new HashMap<>();
	    	String[] OspreyPred = {"Foxes", "Snakes", "Eagles", "Eagles"};
	    	QandAsOsprey3.put("What is a major predator of Ospreys", OspreyPred);
	    	factsAndQuestions.put("Eagles are a major predator of Ospreys", QandAsOsprey3);


	    	avaliableFacts = new ArrayList<String>(factsAndQuestions.keySet());
	    }

	    public void setFact() {

	    	int randomIndex = new Random().nextInt(avaliableFacts.size());
	    	fact = avaliableFacts.get(randomIndex);
	    		//avaliableFactsOsprey.remove(randomIndex);
	    		//Remove might be bugged


	    	
	    }

}
