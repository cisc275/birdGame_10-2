package birdgame;

import org.junit.Test;

public class ProjectTest {
	
	//Tests for Player
		//isAlive()
	@Test
	public void playerShouldBeDeadWithZeroHealth() {
		Player p = new Player();
		p.health = 0;
		assert false == p.isAlive();
	}
	
	@Test
	public void playerShouldBeAliveIfNonZeroHealth() {
		Player p = new Player();
		p.health = 1;
		assert true == p.isAlive();
	}
		//checkCollision()
	@Test
	public void playerCollidesWithObjectInSameLocation() {
		Player p = new Player();
		p.height = 10;
		p.width = 10;
		p.xLocation = 1;
		p.yLocation = 1;
		Enemy e = new Enemy();
		e.height = 5;
		e.width = 5;
		e.xLocation = 1;
		e.yLocation = 1;
		assert true == p.checkCollision();
	}
	@Test
	public void playerDoesNotCollideWithObjectIfFarAway() {
		Player p = new Player();
		p.height = 10;
		p.width = 10;
		p.xLocation = 1;
		p.yLocation = 1;
		Enemy e = new Enemy();
		e.height = 5;
		e.width = 5;
		e.xLocation = 500;
		e.yLocation = 500;
		assert false == p.checkCollision();
	}
	
	//Tests for SpecialFood
		//checkAnswer()
			//Cannot write tests because user input required to test
		//GenerateQuestion()
			//No JUnit testing for functions with random elements
	
	//Tests for GamePiece
		//move()
	@Test
	public void pieceWithNoXChangeDoesNotMoveOnXAxis() {
		GamePiece g = new GamePiece();
		g.xLocation = 1;
		g.xincr = 0;
		g.move();
		assert g.xLocation == 1;
	}
	@Test
	public void pieceWithPlus5YChangeMovesPlus5Y() {
		GamePiece g = new GamePiece();
		g.yLocation = 1;
		g.yincr = 5;
		g.move();
		assert g.yLocation == 6;
	}
	
	//Tests for Controller
		//Cannot write JUnit tests for start()
		//Cannot write JUnit tests for keyAction methods because of user input and nature of method
		
	//Tests for View
		//Cannot write JUnit tests for LoadImages() or createImage() without animations to use
		//Cannot write JUnit tests for paint(), update(), or displayX() methods due to visual nature of method
	
	//Tests for Model
		//updateLocationAndDirection()
	@Test
	public void playerThatMovesPlus5XMovesPlus5X() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		m.gamePieces[0] = p;
		p.xLocation = 1;
		p.xincr = 5;
		m.updateLocationAndDirection();
		assert p.xLocation == 6;
	}
	@Test
	public void playerThatDoesNotMoveStaysInTheSameSpot() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		m.gamePieces[0] = p;
		p.xLocation = 1;
		p.xincr = 0;
		m.updateLocationAndDirection();
		assert p.xLocation == 1;
	}
		//Cannot write JUnit tests for handleTicks() because it deals with screen updates (Calls methods in view)
		//updateObstacles()
	@Test
	public void obstacleThatMovesPlus5XMovesPlus5X() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy();
		m.gamePieces[0] = e;
		e.xLocation = 1;
		e.xincr = 5;
		m.updateLocationAndDirection();
		assert e.xLocation == 6;
	}
	@Test
	public void obstaceThatDoesNotMoveStaysInTheSameSpot() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy();
		m.gamePieces[0] = e;
		e.xLocation = 1;
		e.xincr = 0;
		m.updateLocationAndDirection();
		assert e.xLocation == 1;
	}
		//Cannot write tests for spawnObstacle() due to random elements of spawning an object
		//eat()
	@Test
	public void playerEatingIncreasesFoodScoreBy1() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		p.score = 0;
		m.gamePieces[0] = p;
		m.eat();
		assert p.score == 1;
	}
	@Test
	public void playerEatingIncreasesHealthBy1() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		p.health = 1;
		m.gamePieces[0] = p;
		m.eat();
		assert p.health == 2;
	}
		//die()
	@Test
	public void playerDyingDecreasesHealthToZero() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		p.health = 1;
		m.gamePieces[0] = p;
		m.die();
		assert p.score == 0;
	}
	@Test
	public void playerDyingDecreasesHealthToZeroTest2() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		p.health = 10000;
		m.gamePieces[0] = p;
		m.die();
		assert p.health == 0;
	}
		//Cannot write tests for Nest due to graphic nature (It calls a method in view).
}
