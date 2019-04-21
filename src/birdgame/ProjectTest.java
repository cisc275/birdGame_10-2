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
		Enemy e = new Enemy(1,1);
		e.height = 5;
		e.width = 5;
		assert true == p.checkCollision(e);
	}
	@Test
	public void playerDoesNotCollideWithObjectIfFarAway() {
		Player p = new Player();
		p.height = 10;
		p.width = 10;
		p.xLocation = 1;
		p.yLocation = 1;
		Enemy e = new Enemy(500,500);
		e.height = 5;
		e.width = 5;
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
		assert g.yLocation == -4;
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
	public void enemyThatMovesPlus5XMovesPlus5X() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy(1,1);
		m.gamePieces.add(e);
		e.xincr = 5;
		m.updateLocationAndDirection();
		assert e.xLocation == -4;
	}
	@Test
	public void enemyThatDoesNotMoveStaysInTheSameSpot() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy(1,1);
		m.gamePieces.add(e);
		e.xincr = 0;
		m.updateLocationAndDirection();
		assert e.xLocation == 1;
	}
	@Test
	public void playerThatDoesNotMoveStaysStill() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		m.player = p;
		p.xLocation = 1;
		p.xincr = 0;
		m.updateLocationAndDirection();
		assert p.xLocation == 1;
	}
	@Test
	public void playerWithDirectionUpMovesUp() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		m.player = p;
		m.direction = Direction.UP;
		p.yLocation = 1;
		p.yincr = 5;
		m.updateLocationAndDirection();
		assert p.yLocation == -4;
	}
	@Test
	public void playerWithDirectionDownMovesDown() {
		Model m = new Model(1,1,1,1);
		m.fHeight = 100;
		Player p = new Player();
		m.player = p;
		m.direction = Direction.DOWN;
		p.yLocation = 1;
		p.yincr = -5;
		m.updateLocationAndDirection();
		assert p.yLocation == -4;
	}
		//clearCurrentGP()
	@Test
	public void clearingGamePiecesPlayerWillHaveNoneLeft() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		m.currentGPs.add(p);
		m.clearCurrentGP();
		assert m.currentGPs.size() == 0;
	}
	@Test
	public void clearingGamePiecesEnemyWillHaveNoneLeft() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy(1,1);
		m.currentGPs.add(e);
		m.clearCurrentGP();
		assert m.currentGPs.size() == 0;
	}
		//seeCurrentGP()
	@Test
	public void seeingCurrentGPsShouldAddAllGPsOnScreen() {
		Model m = new Model(100,1,1,1);
		Enemy e = new Enemy(1,1);
		m.gamePieces.add(e);
		m.seeCurrentGP();
		assert m.currentGPs.size() == 1;
	}
	@Test
	public void seeingCurrentGPsShouldAddNoOffScreenRightGPs() {
		Model m = new Model(100,1,1,1);
		Enemy e = new Enemy(101,1);
		m.gamePieces.add(e);
		m.seeCurrentGP();
		assert m.currentGPs.size() == 0;
	}
	@Test
	public void seeingCurrentGPsShouldAddNoOffScreenLeftGPs() {
		Model m = new Model(100,1,1,1);
		Enemy e = new Enemy(-1,1);
		m.gamePieces.add(e);
		m.seeCurrentGP();
		assert m.currentGPs.size() == 0;
	}
		//handleTicks()
	@Test
	public void handleTicksUpdatesLocationAndDirection() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy(1,1);
		m.gamePieces.add(e);
		e.xincr = 5;
		m.handleTicks();
		assert e.xLocation == -4;
	}
	@Test
	public void whenPlayerCollidesWithAGamePieceThePieceGetsRemoved() {
		Model m = new Model(1,1,1,1);
		Food f = new Food(1,1);
		int origSize = m.gamePieces.size();
		m.gamePieces.add(f);
		m.player.xLocation = 1;
		m.player.yLocation = 1;
		m.handleTicks();
		assert m.gamePieces.size() == origSize;
	}
	@Test
	public void whenPlayerCollidesWithAnEnemyObstacleHitIsCalled() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy(1,1);
		m.gamePieces.add(e);
		m.player.xLocation = 1;
		m.player.yLocation = 1;
		m.player.score = 100;
		e.damage = 10;
		m.handleTicks();
		assert m.player.score == 90;
	}
		
		//getProgress()
	@Test
	public void getProgressOf0ReturnsProgressOf0() {
		Model m = new Model(1,1,1,1);
		m.progress = 0;
		assert m.getProgress() == 0;
	}
	@Test
	public void getProgressReturnsProgress() {
		Model m = new Model(1,1,1,1);
		m.progress = 50;
		assert m.getProgress() == 50;
	}
		//eat()
	@Test
	public void playerEatingIncreasesHealthUnder95by5() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		p.health = 1;
		m.player = p;
		Food f = new Food(1,1);
		m.eat(f);
		assert p.health == 6;
	}
	@Test
	public void playerEatingIncreasesHealthAbove95to100() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		p.health = 97;
		m.player = p;
		Food f = new Food(1,1);
		m.eat(f);
		assert p.health == 100;
	}
	@Test
	public void playerEatingIncreasesScoreBy1() {
		Model m = new Model(1,1,1,1);
		Player p = new Player();
		p.health = 1;
		p.score = 1;
		m.player = p;
		Food f = new Food(1,1);
		f.foodValue = 1;
		m.eat(f);
		assert p.score == 2;
	}
		//obstacleHit()
	@Test
	public void hittingAnEnemyWith10DamageDecreasesScoreBy10() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy(1,1);
		m.player.score = 100;
		e.damage = 10;
		m.obstacleHit(e);
		assert m.player.score == 90;
	}
	@Test
	public void hittingAnEnemyWhileHaving15HealthDecreasesHealthto0() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy(1,1);
		m.player.health = 15;
		m.obstacleHit(e);
		assert m.player.health == 0;
	}
	@Test
	public void hittingAnEnemyWhileHaving50HealthDecreasesHealthBy20() {
		Model m = new Model(1,1,1,1);
		Enemy e = new Enemy(1,1);
		m.player.health = 50;
		m.obstacleHit(e);
		assert m.player.health == 30;
	}
		//nest()
}