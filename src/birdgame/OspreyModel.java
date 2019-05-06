package birdgame;

import java.util.ArrayList;

public class OspreyModel extends Model{
	

	public OspreyModel(int fwidth, int fheight, int imageWidth, int imageHeight) {
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
        	if (numSpecialFood < maxSpecialFood) {
        		if (Math.random() < .2) {
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
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random()*groundLevel), Sprite.SNAKE));
                }
                else{//fish
                    gamePieces.add(new Food(tempXLoc, (int) (Math.random()*groundLevel), Sprite.FISH));
                }
            }
            else{//enemy
                if(Math.random() < 0.5){//eagles
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*groundLevel), Sprite.EAGLE));
                }
                else{//planes
                    gamePieces.add(new Enemy(tempXLoc, (int) (Math.random()*groundLevel), Sprite.PLANE));
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
	}

