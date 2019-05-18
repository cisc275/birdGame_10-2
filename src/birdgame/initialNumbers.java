package birdgame;

/**
 * initialNumbers contains all of the initial values for the game,
 * including all hard-coded constants
 * @author mattward
 */

public class initialNumbers {
	//Controller	
	private int birdHealth = 0;//250;
	public int birdHealth() {
		return birdHealth;
	}
	
	private int birdXLocation = 30;
	public int birdXLocation() {
		return birdXLocation;
	}
	
	//Model

	private int obstaclesPerLevel = 15;

	public int obstaclesPerLevel() {
		return obstaclesPerLevel;
	}

	private int maxSpecialFood = 3;
	public int maxSpecialFood() {
		return maxSpecialFood;
	}
	
	private int xIncreaseAtEndOfLevel = 30;
	public int xIncreaseAtEndOfLevel() {
		return xIncreaseAtEndOfLevel;
	}
	
	private int xLocationForObstacles = 1200;
	public int xLocationForObstacles() {
		return xLocationForObstacles;
	}
	
	private int additionalXLocationForSpecialFood = 800;
	public int additionalXLocatitionForSpecialFood() {
		return additionalXLocationForSpecialFood;
	}
	
	private double chanceSpecialFoodSpawns = 0.2;
	public double chanceSpecialFoodSpawns() {
		return chanceSpecialFoodSpawns;
	}

	private double chanceHarrierFoodIsBunny = 0.5;
	public double chanceHarrierFoodIsBunny() {
		return chanceHarrierFoodIsBunny;
	}
	
	private double chanceFoodSpawnsInsteadOfEnemy = 0.5;
	public double chanceFoodSpawnsInsteadOfEnemy() {
		return chanceFoodSpawnsInsteadOfEnemy;
	}
	
	private double chanceHarrierEnemyIsFox = 0.5;
	public double chanceHarrierEnemyIsFox() {
		return chanceHarrierEnemyIsFox;
	}
	
	private double chanceOspreyFoodIsSnake = 0.5;
	public double chanceOspreyFoodIsSnake() {
		return chanceOspreyFoodIsSnake;
	}
	
	private double chanceOspreyEnemyIsEagle = 0.5;
	public double chanceOspreyEnemyIsEagle() {
		return chanceOspreyEnemyIsEagle;
	}
	
	private int xLocationWhereGPsAreNoLongerCurrent = -500;
	public int xLocationWhereGPsAreNoLongerCurrent() {
		return xLocationWhereGPsAreNoLongerCurrent;
	}
	
	//View
	
}
