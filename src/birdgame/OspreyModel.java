package birdgame;

import java.util.ArrayList;
import java.util.HashMap;

public class OspreyModel extends Model{
	

	public OspreyModel(int fwidth, int fheight, int imageWidth, int imageHeight) {
		super(fwidth, fheight, imageWidth, imageHeight);
		gamePieces = new ArrayList<>();
		factsAndQuestions = new HashMap<>();
		generateFactsAndQuestions();
		// TODO Auto-generated constructor stub
	}
	
	public void spawnGamePieces() {
		int numGamePieces = 0;
        int numSpecialFood = 0;
        
        //background0:
            //land: 0-432px, 1776-2640px, 
        //int tempXLoc = (int)(Math.random() * 2639 + 1776);
        
        
        int tempXLoc = 500;
        int landHeight = 96;
        boolean flag = true;
        
        int bottomHalfY = ((int) (Math.random()*(fHeight/2)) + (fHeight/2));
    	int topHalfY = ((int) (Math.random()*(fHeight/2)));
    	
    	int halfOfScreen = fHeight/2;

    	
    	int maxSpecialFood = 3;
		while(numGamePieces < 40){
        	if (numSpecialFood < maxSpecialFood) {
        		if (Math.random() < .4) {
        			if (Math.random() < .5) {
        				gamePieces.add(new SpecialFood(3*tempXLoc, (int) (Math.random()*groundLevel), Sprite.SNAKE));
        			}
        			else {
        				gamePieces.add(new SpecialFood(3*tempXLoc, (int) (Math.random()*groundLevel), Sprite.FISH));

        			}
        			numSpecialFood++;
        			
        		}
        	}
        	
        
        	
            if(Math.random() < .5){ //food
                if(Math.random() < .5){//snakes
                    gamePieces.add(new Food(tempXLoc, ((int) (Math.random()*halfOfScreen) + halfOfScreen), Sprite.SNAKE));
                }
                else{//fish
                    gamePieces.add(new Food(tempXLoc, ((int) (Math.random()*halfOfScreen) + halfOfScreen), Sprite.FISH));
                }
            }
            else{//enemy
                if(Math.random() < 0.5){//eagles
                    gamePieces.add(new Enemy(tempXLoc, (int) ((Math.random()*halfOfScreen)), Sprite.EAGLE));
                }
                else{//planes
                    gamePieces.add(new Enemy(tempXLoc, (int) ((Math.random()*halfOfScreen)), Sprite.PLANE));
                }
            }
            numGamePieces++;
            tempXLoc+=fWidth/3;
        }
		
		for(GamePiece gp: gamePieces) {
        	int furthestGPLoc = 0;
        	if(gp.getX() > furthestGPLoc) {
        		furthestGPLoc = gp.getX();
        		setFurthestGP(gp);
        		
        	}
        }
        //System.out.println(gamePieces);
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
	}
}

