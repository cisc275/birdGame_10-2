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
	public static HashMap<String,HashMap<String,String[]>> factsAndQuestionsHarrier = new HashMap<>();
	public static HashMap<String,HashMap<String,String[]>> factsAndQuestionsOsprey = new HashMap<>();
    public SpecialFood(int x, int y, Sprite foodType, int currentBird) {
		super(x, y, foodType);
		
	}
    public static void generateFactsAndQuestions() {
    	HashMap<String,String[]> QandAsHarrier1 = new HashMap<>();
    	String[] harrierFood = {"rodents", "fish", "eagles","plants", "rodents"};
    	QandAsHarrier1.put("What do Northern Harriers eat?",harrierFood);
    	factsAndQuestionsHarrier.put("Northern Harriers Like to eat rodents like mice and bunnies", QandAsHarrier1);
    	
    	HashMap<String,String[]> QandAsHarrier2 = new HashMap<>();
    	String[] harrierMigrate = {"They migrate to South America","They migrate to California", "They don't migrate", "They migrate to canada", "They don't migrate"};
    	QandAsHarrier2.put("Where do Harriers migrate", harrierMigrate);
    	factsAndQuestionsHarrier.put("Northern Harriers are non-migratory birds",QandAsHarrier2);
    	
    	HashMap<String,String[]> QandAsHarrier3 = new HashMap<>();
    	String[] harrierPred = {"Foxes", "Snakes","Cats","Humans","Foxes"};
    	QandAsHarrier3.put("What is a major predator of Northern Harriers", harrierPred);
    	factsAndQuestionsHarrier.put("Foxes are a common predator on Northern Harriers", QandAsHarrier3);
    	
    	HashMap<String,String[]> QandAsOsprey1 = new HashMap<>();
    	
    	HashMap<String,String[]> QandAsOsprey2 = new HashMap<>();
    	HashMap<String,String[]> QandAsOsprey3 = new HashMap<>();



    	
    
    	
    }

	
    
    
    
   
  
    	
    	
    	


   }

  
   

 