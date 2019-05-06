package birdgame;

import java.util.ArrayList;

public class HarrierModel extends Model{

	public HarrierModel(int fwidth, int fheight, int imageWidth, int imageHeight) {
		super(fwidth, fheight, imageWidth, imageHeight);
		gamePieces = new ArrayList<>();
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
                 		gamePieces.add(new Food(tempXLoc, (int) (Math.random()*groundLevel), Sprite.BUNNY));
                 	}
                     
                 
                 else{//mouse
                     gamePieces.add(new Food(tempXLoc, (int) (Math.random()*groundLevel), Sprite.MOUSE));
                 }
             }
             else{//enemy
                 if(Math.random() < .5){//red fox
                     gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*groundLevel), Sprite.REDFOX));
                 }
                 else{//raccoon
                     gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*groundLevel), Sprite.RACCOON));
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

}
