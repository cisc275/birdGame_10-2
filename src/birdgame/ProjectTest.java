//package birdgame;
//
//import org.junit.Test;
//
//public class ProjectTest {
//	
//	//Tests for Player
//		//isAlive()
//	@Test
//	public void playerShouldBeDeadWithZeroHealth() {
//		Player p = new Player();
//		p.setHealth(0);
//		assert false == p.isAlive();
//	}
//	
//	@Test
//	public void playerShouldBeAliveIfNonZeroHealth() {
//		Player p = new Player();
//		p.setHealth(1);
//		assert true == p.isAlive();
//	}
//		//checkCollision()
//	@Test
//	public void playerCollidesWithObjectInSameLocation() {
//		Player p = new Player();
//		p.setHeight(10);
//		p.setWidth(10);
//		p.setX(1);
//		p.setY(1);
//		Enemy e = new Enemy(1,1);
//		e.setHeight(5);
//		e.setWidth(5);
//		assert true == p.checkCollision(e);
//	}
//	@Test
//	public void playerDoesNotCollideWithObjectIfFarAway() {
//		Player p = new Player();
//		p.setHeight(10);
//		p.setWidth(10);
//		p.setX(1);
//		p.setY(1);
//		Enemy e = new Enemy(500,500);
//		e.setHeight(5);
//		e.setWidth(5);
//		assert false == p.checkCollision(e);
//	}
//	
//	//Tests for SpecialFood
//		//checkAnswer()
//			//Cannot write tests because user input required to test
//		//GenerateQuestion()
//			//No JUnit testing for functions with random elements
//	
//	//Tests for GamePiece
//		//move()
//	@Test
//	public void pieceWithNoXChangeDoesNotMoveOnXAxis() {
//		GamePiece g = new GamePiece();
//		g.setX(1);
//		g.setXIncr(0);
//		g.move();
//		assert g.getX() == 1;
//	}
//	@Test
//	public void pieceWithPlus5YChangeMovesPlus5Y() {
//		GamePiece g = new GamePiece();
//		g.setY(1);
//		g.setYIncr(5);
//		g.move();
//		assert g.getY() == -4;
//	}
//	
//	//Tests for Controller
//		//Cannot write JUnit tests for start()
//		//Cannot write JUnit tests for keyAction methods because of user input and nature of method
//		
//	//Tests for View
//		//Cannot write JUnit tests for LoadImages() or createImage() without animations to use
//		//Cannot write JUnit tests for paint(), update(), or displayX() methods due to visual nature of method
//	
//	//Tests for Model
//		//updateLocationAndDirection()
//	@Test
//	public void enemyThatMovesPlus5XMovesPlus5X() {
//		Model m = new Model(1,1,1,1);
//		Enemy e = new Enemy(1,1);
//		m.getGamePieces().add(e);
//		e.setXIncr(5);
//		m.updateLocationAndDirection();
//		assert e.getX() == -4;
//	}
//	@Test
//	public void enemyThatDoesNotMoveStaysInTheSameSpot() {
//		Model m = new Model(1,1,1,1);
//		Enemy e = new Enemy(1,1);
//		m.getGamePieces().add(e);
//		e.setXIncr(0);
//		m.updateLocationAndDirection();
//		assert e.getX() == 1;
//	}
//	@Test
//	public void playerThatDoesNotMoveStaysStill() {
//		Model m = new Model(1,1,1,1);
//		Player p = new Player();
//		m.setPlayer(p);
//		p.setX(1);
//		p.setXIncr(0);
//		m.updateLocationAndDirection();
//		assert p.getX() == 1;
//	}
//	@Test
//	public void playerWithDirectionUpMovesUp() {
//		Model m = new Model(1,1,1,1);
//		Player p = new Player();
//		m.setPlayer(p);
//		m.setDirection(Direction.UP);
//		p.setY(1);
//		p.setYIncr(5);
//		m.updateLocationAndDirection();
//		assert p.getY() == -4;
//	}
//	@Test
//	public void playerWithDirectionDownMovesDown() {
//		Model m = new Model(1,1,1,1);
//		m.setFHeight(100);
//		Player p = new Player();
//		m.setPlayer(p);
//		m.setDirection(Direction.DOWN);
//		p.setY(1);
//		p.setYIncr(-5);
//		m.updateLocationAndDirection();
//		assert p.getY() == -4;
//	}
//		//clearCurrentGP()
//	@Test
//	public void clearingGamePiecesPlayerWillHaveNoneLeft() {
//		Model m = new Model(1,1,1,1);
//		Player p = new Player();
//		m.getCurrentGPs().add(p);
//		m.clearCurrentGP();
//		assert m.getCurrentGPs().size() == 0;
//	}
//	@Test
//	public void clearingGamePiecesEnemyWillHaveNoneLeft() {
//		Model m = new Model(1,1,1,1);
//		Enemy e = new Enemy(1,1);
//		m.getCurrentGPs().add(e);
//		m.clearCurrentGP();
//		assert m.getCurrentGPs().size() == 0;
//	}
//		//seeCurrentGP()
//	@Test
//	public void seeingCurrentGPsShouldAddAllGPsOnScreen() {
//		Model m = new Model(100,1,1,1);
//		Enemy e = new Enemy(1,1);
//		m.getGamePieces().add(e);
//		m.seeCurrentGP();
//		assert m.getCurrentGPs().size() == 1;
//	}
//	@Test
//	public void seeingCurrentGPsShouldAddNoOffScreenRightGPs() {
//		Model m = new Model(100,1,1,1);
//		Enemy e = new Enemy(101,1);
//		m.getGamePieces().add(e);
//		m.seeCurrentGP();
//		assert m.getCurrentGPs().size() == 0;
//	}
//	@Test
//	public void seeingCurrentGPsShouldAddNoOffScreenLeftGPs() {
//		Model m = new Model(100,1,1,1);
//		Enemy e = new Enemy(-1,1);
//		m.getGamePieces().add(e);
//		m.seeCurrentGP();
//		assert m.getCurrentGPs().size() == 0;
//	}
//		//handleTicks()
//	@Test
//	public void handleTicksUpdatesLocationAndDirection() {
//		Model m = new Model(1,1,1,1);
//		Enemy e = new Enemy(1,1);
//		m.getGamePieces().add(e);
//		e.setXIncr(5);
//		m.handleTicks();
//		assert e.getX() == -4;
//	}
//	@Test
//	public void whenPlayerCollidesWithAGamePieceThePieceGetsRemoved() {
//		Model m = new Model(1,1,1,1);
//		Food f = new Food(1,1);
//		int origSize = m.getGamePieces().size();
//		m.getGamePieces().add(f);
//		m.getPlayer().setX(1);
//		m.getPlayer().setY(1);
//		m.handleTicks();
//		assert m.getGamePieces().size() == origSize;
//	}
//	@Test
//	public void whenPlayerCollidesWithAnEnemyObstacleHitIsCalled() {
//		Model m = new Model(1,1,1,1);
//		Enemy e = new Enemy(1,1);
//		m.getGamePieces().add(e);
//		m.getPlayer().setX(1);
//		m.getPlayer().setY(1);
//		m.getPlayer().setScore(100);
//		e.setDamage(10);
//		m.handleTicks();
//		assert m.getPlayer().getScore() == 90;
//	}
//		
//		//getProgress()
//	@Test
//	public void getProgressOf0ReturnsProgressOf0() {
//		Model m = new Model(1,1,1,1);
//		m.setProgress(0);
//		assert m.getProgress() == 0;
//	}
//	@Test
//	public void getProgressReturnsProgress() {
//		Model m = new Model(1,1,1,1);
//		m.setProgress(50);
//		assert m.getProgress() == 50;
//	}
//		//eat()
//	@Test
//	public void playerEatingIncreasesHealthUnder95by5() {
//		Model m = new Model(1,1,1,1);
//		Player p = new Player();
//		p.setHealth(1);
//		m.setPlayer(p);
//		Food f = new Food(1,1);
//		m.eat(f);
//		assert p.getHealth() == 6;
//	}
//	@Test
//	public void playerEatingIncreasesHealthAbove95to100() {
//		Model m = new Model(1,1,1,1);
//		Player p = new Player();
//		p.setHealth(97);
//		m.setPlayer(p);
//		Food f = new Food(1,1);
//		m.eat(f);
//		assert p.getHealth() == 100;
//	}
//	@Test
//	public void playerEatingIncreasesScoreBy1() {
//		Model m = new Model(1,1,1,1);
//		Player p = new Player();
//		p.setHealth(1);
//		p.setScore(1);
//		m.setPlayer(p);
//		Food f = new Food(1,1);
//		f.setFoodValue(1);
//		m.eat(f);
//		assert p.getScore() == 2;
//	}
//		//obstacleHit()
//	@Test
//	public void hittingAnEnemyWith10DamageDecreasesScoreBy10() {
//		Model m = new Model(1,1,1,1);
//		Enemy e = new Enemy(1,1);
//		m.getPlayer().setScore(100);
//		e.setDamage(10);
//		m.obstacleHit(e);
//		assert m.getPlayer().getScore() == 90;
//	}
//	@Test
//	public void hittingAnEnemyWhileHaving15HealthDecreasesHealthto0() {
//		Model m = new Model(1,1,1,1);
//		Enemy e = new Enemy(1,1);
//		m.getPlayer().setHealth(15);
//		m.obstacleHit(e);
//		assert m.getPlayer().getHealth() == 0;
//	}
//	@Test
//	public void hittingAnEnemyWhileHaving50HealthDecreasesHealthBy20() {
//		Model m = new Model(1,1,1,1);
//		Enemy e = new Enemy(1,1);
//		m.getPlayer().setHealth(50);
//		m.obstacleHit(e);
//		assert m.getPlayer().getHealth() == 30;
//	}
//		//nest()
//}
//
