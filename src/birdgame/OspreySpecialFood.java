package birdgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class OspreySpecialFood extends SpecialFood {


	public OspreySpecialFood(int x, int y, Sprite foodType) {
		super(x, y, foodType);
		setFact();
		// TODO Auto-generated constructor stub
	}
	
	


	    	
	    

	    public void setFact() {
	    	int randomIndex = new Random().nextInt(OspreyModel.avaliableFacts.size());
	    	fact = OspreyModel.avaliableFacts.get(randomIndex);
	    		//avaliableFactsOsprey.remove(randomIndex);
	    		//Remove might be bugged


	    	
	    }

}
