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
		Enemy e = new Enemy(0, 0);
		e.height = 5;
		e.width = 5;
		e.xLocation = 1;
		e.yLocation = 1;
		assert true == p.checkCollision(e);
	}
	@Test
	public void playerDoesNotCollideWithObjectIfFarAway() {
		Player p = new Player();
		p.height = 10;
		p.width = 10;
		p.xLocation = 1;
		p.yLocation = 1;
		Enemy e = new Enemy(0, 0);
		e.height = 5;
		e.width = 5;
		e.xLocation = 500;
		e.yLocation = 500;
		assert false == p.checkCollision(e);
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
		//
}
