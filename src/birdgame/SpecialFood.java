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
	public static HashMap<String,HashMap<String,String[]>> factsAndQuestionsHarrier = new HashMap<>();
	public static HashMap<String,HashMap<String,String[]>> factsAndQuestionsOsprey = new HashMap<>();
	public static ArrayList<String> avaliableFactsHarrier;
	public static ArrayList<String> avaliableFactsOsprey;
	private String fact;
    public SpecialFood(int x, int y, Sprite foodType) {
		super(x, y, foodType);
		setSpecialFood(true);
		setFact(Model.bird);
		
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
    	String[] OspreyFood = {"Mice and Rabbits", "Snakes and Fish", "Raccoons", "Sankes and Fish"};
    	QandAsOsprey1.put("What do Ospreys eat?", OspreyFood);
    	factsAndQuestionsOsprey.put("Ospreys like to eat Snakes and Fish", QandAsOsprey1);
    	
    	HashMap<String,String[]> QandAsOsprey2 = new HashMap<>();
    	String[] OspreyMigrate = {"They migrate to South America","They migrate to California", "They don't migrate", "They migrate to canada", "They migrate to South America"};
    	QandAsOsprey2.put("Where do Ospreys migrate", OspreyMigrate);
    	factsAndQuestionsOsprey.put("Ospreys migrate to South America for the winter", QandAsOsprey2);
    	
    	HashMap<String,String[]> QandAsOsprey3 = new HashMap<>();
    	String[] OspreyPred = {"Foxes", "Snakes", "Eagles", "Eagles"};
    	QandAsHarrier3.put("What is a major predator of Ospreys", OspreyPred);
    	factsAndQuestionsHarrier.put("Eagles are a major predator of Ospreys", QandAsOsprey3);


    	avaliableFactsOsprey = new ArrayList<String>(factsAndQuestionsOsprey.keySet());
    	avaliableFactsHarrier = new ArrayList<String>(factsAndQuestionsOsprey.keySet());
    }

    public void setFact(Sprite birdType) {
    	if (birdType.equals(Sprite.OSPREY)) {
    		int randomIndex = new Random().nextInt(avaliableFactsOsprey.size());
    		fact = avaliableFactsOsprey.get(randomIndex);
    		avaliableFactsOsprey.remove(randomIndex);
    		//Remove might be bugged
    	}
    	else {
    		int randomIndex = new Random().nextInt(avaliableFactsHarrier.size());
    		fact = avaliableFactsHarrier.get(randomIndex);
    		avaliableFactsHarrier.remove(randomIndex);
    		//Remove might be bugged
    	}
    	
    }
    public String getFact() {
		return fact;
	}
    
    
    
   
  
    	
    	
    	


   }

  
   

 