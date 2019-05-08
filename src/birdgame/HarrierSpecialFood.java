package birdgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HarrierSpecialFood extends SpecialFood{

	public HarrierSpecialFood(int x, int y, Sprite foodType) {
		super(x, y, foodType);
		setFact();
		// TODO Auto-generated constructor stub
	}
	
	 

	    public void setFact() {

	    	int randomIndex = new Random().nextInt(OspreyModel.avaliableFacts.size());
	    	System.out.println(OspreyModel.avaliableFacts.size());
	   		fact = OspreyModel.avaliableFacts.get(randomIndex);
	    		//avaliableFactsHarrier.remove(randomIndex);
	    		//Remove might be bugged

	    	
	    }

}
