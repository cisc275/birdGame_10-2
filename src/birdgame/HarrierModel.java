package birdgame;

import java.util.ArrayList;
import java.util.HashMap;

public class HarrierModel extends Model{

	public HarrierModel(int fwidth, int fheight, int imageWidth, int imageHeight) {
		super(fwidth, fheight, imageWidth, imageHeight);
		spawnGamePieces();

		 factsAndQuestions = new HashMap<>();
		 avaliableFacts = new ArrayList<String>(factsAndQuestions.keySet());
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
        
        
        
    	
    	int halfOfScreen = fHeight/2;
    	int maxSpecialFood = 3;
		 while(numGamePieces < 10){
         	if( numSpecialFood < 3) {
         		if (Math.random() < .2) {
         			if (Math.random() < .5) {
         				gamePieces.add(new SpecialFood(3*tempXLoc, (int) (Math.random()*groundLevel), Sprite.BUNNY));
         			}
         			else {
         				gamePieces.add(new SpecialFood(3*tempXLoc, (int) (Math.random()*groundLevel), Sprite.MOUSE));

         			}
         			numSpecialFood++;
         			
         		}
         	}
         	
             if(Math.random() < .5){ //food
                 if(Math.random() < .5){//bunny
                 		gamePieces.add(new Food(tempXLoc, ((int) (Math.random()*halfOfScreen) + halfOfScreen), Sprite.BUNNY));
                 	}
                     
                 
                 else{//mouse
                     gamePieces.add(new Food(tempXLoc, ((int) (Math.random()*halfOfScreen) + halfOfScreen), Sprite.MOUSE));
                 }
             }
             else{//enemy
                 if(Math.random() < .5){//red fox
                     gamePieces.add(new Enemy(tempXLoc, ((int) (Math.random()*halfOfScreen) + halfOfScreen), Sprite.REDFOX));
                 }
                 else{//raccoon
                     gamePieces.add(new Enemy(tempXLoc, ((int) (Math.random()*halfOfScreen) + halfOfScreen), Sprite.RACCOON));
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
	

}
