package birdgame;
import java.util.Random;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * SpecialFood is food that may appear larger on screen with some glowing and 
 * will offer more of a score increment to the player provided they answer the 
 * question that is prompted correctly.
 * @author crnis
 */
public class SpecialFood extends Food {
	//All of these static fields are defined in the generateFactsAndQuestions method!
//	public static HashMap<String,HashMap<String,String[]>> factsAndQuestionsHarrier = new HashMap<>();
	
//	public static ArrayList<String> avaliableFactsHarrier;
	protected String fact;
    public SpecialFood(int x, int y, Sprite foodType) {
		super(x, y, foodType);
		setSpecialFood(true);

		setFact();
		setXIncr(15);

		
		
	}
    public void setFact() {

    	int randomIndex = new Random().nextInt(Model.avaliableFacts.size());
    	//System.out.println(Model.avaliableFacts.size());
   		fact = Model.avaliableFacts.get(randomIndex);
   		//needs a way to avoid repeats!
    }

    public String getFact() {
		return fact;
	}
    
    
    
   
  
    	
    	
    	


   }

  
   

 