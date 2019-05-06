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
//		p.setHeight(501);
//		p.setWidth(501);
//		p.setX(100);
//		p.setY(100);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(100,100,t);
//		e.setOffsets();
//		e.setHeight(551);
//		e.setWidth(551);
//		assert true == p.checkCollision(e);
//	}
//	@Test
//	public void playerDoesNotCollideWithObjectIfFarAway() {
//		Player p = new Player();
//		p.setHeight(10);
//		p.setWidth(10);
//		p.setX(1);
//		p.setY(1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(500,500,t);
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
//		//toString()
//	@Test
//	public void toStringReturnsASpaceAndTheSpriteType() {
//		GamePiece g = new GamePiece();
//		Sprite t = Sprite.REDFOX;
//		g.setSprite(t);
//		assert g.toString().equals(" REDFOX");
//	}
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
//		//getPicNum()
//	@Test
//	public void getPicNumReturnsPicNum() {
//		GamePiece g = new GamePiece();
//		g.setPicNum(1);
//		assert g.getPicNum() == 1;
//	}
//		//isEnemy()
//	@Test
//	public void aPlaneIsAnEnemy() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.PLANE;
//		g.setSprite(s);
//		assert g.isEnemy() == true;
//	}
//	@Test
//	public void anEagleIsAnEnemy() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.EAGLE;
//		g.setSprite(s);
//		assert g.isEnemy() == true;
//	}
//	@Test
//	public void aRedFoxIsAnEnemy() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.REDFOX;
//		g.setSprite(s);
//		assert g.isEnemy() == true;
//	}
//	@Test
//	public void aRaccoonIsAnEnemy() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.RACCOON;
//		g.setSprite(s);
//		assert g.isEnemy() == true;
//	}
//		//setOffsets()
//	@Test
//	public void redfoxSetXOffsetis50() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.REDFOX;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getXOffset() == 50;
//	}
//	@Test
//	public void redfoxSetYOffsetis50() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.REDFOX;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getYOffset() == 50;
//	}
//	@Test
//	public void eagleSetXOffsetis30() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.EAGLE;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getXOffset() == 30;
//	}
//	@Test
//	public void eagleSetYOffsetis30() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.EAGLE;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getYOffset() == 30;
//	}
//	@Test
//	public void planeSetXOffsetis40() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.PLANE;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getXOffset() == 40;
//	}
//	@Test
//	public void planeSetYOffsetis20() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.PLANE;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getYOffset() == 20;
//	}
//	@Test
//	public void bunnySetXOffsetis20() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.BUNNY;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getXOffset() == 20;
//	}
//	@Test
//	public void bunnySetYOffsetis20() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.BUNNY;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getYOffset() == 20;
//	}
//	@Test
//	public void mouseSetXOffsetis10() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.MOUSE;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getXOffset() == 10;
//	}
//	@Test
//	public void mouseSetYOffsetis5() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.MOUSE;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getYOffset() == 5;
//	}
//	@Test
//	public void fishSetXOffsetis10() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.FISH;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getXOffset() == 10;
//	}
//	@Test
//	public void fishSetYOffsetis20() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.FISH;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getYOffset() == 20;
//	}
//	@Test
//	public void raccoonSetXOffsetis30() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.RACCOON;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getXOffset() == 30;
//	}
//	@Test
//	public void raccoonSetYOffsetis30() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.RACCOON;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getYOffset() == 30;
//	}
//	@Test
//	public void snakeSetXOffsetis30() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.SNAKE;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getXOffset() == 30;
//	}
//	@Test
//	public void snakeSetYOffsetis30() {
//		GamePiece g = new GamePiece();
//		Sprite s = Sprite.SNAKE;
//		g.setSprite(s);
//		g.setOffsets();
//		assert g.getYOffset() == 30;
//	}
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
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
//		m.getGamePieces().add(e);
//		e.setXIncr(5);
//		m.updateLocationAndDirection();
//		assert e.getX() == -4;
//	}
//	@Test
//	public void enemyThatDoesNotMoveStaysInTheSameSpot() {
//		Model m = new Model(1,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
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
//	@Test
//	public void playerWithDirectionUpMovesUpUnlessAtTop() {
//		Model m = new Model(1,1,1,1);
//		Player p = new Player();
//		m.setPlayer(p);
//		m.setDirection(Direction.UP);
//		p.setY(-2);
//		p.setYIncr(5);
//		m.updateLocationAndDirection();
//		assert p.getY() == -2;
//	}
//	@Test
//	public void playerWithDirectionDownMovesDownUnlessAtBottom() {
//		Model m = new Model(1,1,1,1);
//		m.setFHeight(100);
//		m.setImgHeight(10);
//		Player p = new Player();
//		m.setPlayer(p);
//		m.setDirection(Direction.DOWN);
//		p.setY(91);
//		p.setYIncr(-5);
//		m.updateLocationAndDirection();
//		assert p.getY() == 91;
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
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
//		m.getCurrentGPs().add(e);
//		m.clearCurrentGP();
//		assert m.getCurrentGPs().size() == 0;
//	}
//		//seeCurrentGP()
//	@Test
//	public void seeingCurrentGPsShouldAddAllGPsOnScreen() {
//		Model m = new Model(100,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
//		m.getGamePieces().add(e);
//		m.seeCurrentGP();
//		assert m.getCurrentGPs().size() == 1;
//	}
//	@Test
//	public void seeingCurrentGPsShouldAddNoOffScreenRightGPs() {
//		Model m = new Model(100,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(101,1,t);
//		m.getGamePieces().add(e);
//		m.seeCurrentGP();
//		assert m.getCurrentGPs().size() == 0;
//	}
//	@Test
//	public void seeingCurrentGPsShouldAddNoOffScreenLeftGPs() {
//		Model m = new Model(100,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(-1,1,t);
//		m.getGamePieces().add(e);
//		m.seeCurrentGP();
//		assert m.getCurrentGPs().size() == 0;
//	}
//		//handleTicks()
//	@Test
//	public void handleTicksUpdatesLocationAndDirection() {
//		Model m = new Model(1,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
//		m.getGamePieces().add(e);
//		e.setXIncr(5);
//		m.handleTicks();
//		assert e.getX() == -4;
//	}
//	@Test
//	public void whenPlayerCollidesWithAGamePieceThePieceGetsRemoved() {
//		Model m = new Model(1,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
//		int origSize = m.getGamePieces().size();
//		m.getGamePieces().add(e);
//		m.getPlayer().setX(1);
//		m.getPlayer().setY(1);
//		m.handleTicks();
//		assert m.getGamePieces().size() == origSize;
//	}
//	@Test
//	public void whenPlayerCollidesWithFoodTheFoodIsEaten() {
//		Model m = new Model(1,1,1,1);
//		Sprite t = Sprite.FISH;
//		Food f = new Food(1,1,t);
//		f.setFoodValue(100);
//		m.getGamePieces().add(f);
//		m.getPlayer().setX(1);
//		m.getPlayer().setY(1);
//		m.getPlayer().setScore(100);
//		m.handleTicks();
//		assert m.getPlayer().getScore() == 200;
//	}
//	/*@Test
//	public void whenPlayerCollidesWithSpecialFoodTheSpecialFoodIsEaten() {
//		Model m = new Model(1,1,1,1);
//		Sprite t = Sprite.FISH;
//		m.setBird(t);
//		SpecialFood f = new SpecialFood(1,1,t);
//		f.setFoodValue(100);
//		m.getGamePieces().add(f);
//		m.getPlayer().setX(1);
//		m.getPlayer().setY(1);
//		m.getPlayer().setScore(100);
//		m.handleTicks();
//		assert m.getPlayer().getScore() == 200;
//	}*/
//	@Test
//	public void whenPlayerCollidesWithAnEnemyObstacleHitIsCalled() {
//		Model m = new Model(1,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
//		m.getGamePieces().add(e);
//		m.getPlayer().setX(1);
//		m.getPlayer().setY(1);
//		m.getPlayer().setScore(100);
//		e.setDamage(10);
//		m.handleTicks();
//		assert m.getPlayer().getScore() == 90;
//	}
//	@Test
//	public void ifNoFarthestGPThenLevelEnds() {
//		Model m = new Model(1,1,1,1);
//		m.getPlayer().setXIncr(30);
//		m.handleTicks();
//		assert m.getPlayer().getXIncr() == 40;
//	}
//	@Test
//	public void ifFarthestGPXLessThan0ThenLevelEnds() {
//		Model m = new Model(1,1,1,1);
//		GamePiece gP = new GamePiece();
//		gP.setX(-1);
//		m.setFurthestGP(gP);
//		m.getPlayer().setXIncr(30);
//		m.handleTicks();
//		assert m.getPlayer().getXIncr() == 40;
//	}
//	@Test
//	public void ifFarthestGPXGreaterThan0ThenLevelDoesNotEnd() {
//		Model m = new Model(1,1,1,1);
//		GamePiece gP = new GamePiece();
//		gP.setX(1);
//		m.setFurthestGP(gP);
//		m.getPlayer().setXIncr(30);
//		m.handleTicks();
//		assert m.getPlayer().getXIncr() == 30;
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
//		Sprite t = Sprite.FISH;
//		Food f = new Food(1,1,t);
//		m.eat(f);
//		System.out.println(p.getHealth());
//		assert p.getHealth() == 11;
//	}
//	@Test
//	public void playerEatingIncreasesHealthAbove95to100() {
//		Model m = new Model(1,1,1,1);
//		Player p = new Player();
//		p.setHealth(97);
//		m.setPlayer(p);
//		Sprite t = Sprite.FISH;
//		Food f = new Food(1,1,t);
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
//		Sprite t = Sprite.FISH;
//		Food f = new Food(1,1,t);
//		f.setFoodValue(1);
//		m.eat(f);
//		assert p.getScore() == 2;
//	}
//		//obstacleHit()
//	@Test
//	public void hittingAnEnemyWith10DamageDecreasesScoreBy10() {
//		Model m = new Model(1,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
//		m.getPlayer().setScore(100);
//		e.setDamage(10);
//		m.obstacleHit(e);
//		assert m.getPlayer().getScore() == 90;
//	}
//	@Test
//	public void hittingAnEnemyWhileHaving15HealthDecreasesHealthto0() {
//		Model m = new Model(1,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
//		m.getPlayer().setHealth(15);
//		m.obstacleHit(e);
//		assert m.getPlayer().getHealth() == 0;
//	}
//	@Test
//	public void hittingAnEnemyWhileHaving50HealthDecreasesHealthBy20() {
//		Model m = new Model(1,1,1,1);
//		Sprite t = Sprite.REDFOX;
//		Enemy e = new Enemy(1,1,t);
//		m.getPlayer().setHealth(50);
//		m.obstacleHit(e);
//		assert m.getPlayer().getHealth() == 30;
//	}
//		//nest()
//	@Test
//	public void thisTestOnlyExistsSoNestDoesNotHaveAnyRedBarsWeWillAddMoreTestsWhenWeAddNest() {
//		Model m = new Model(1,1,1,1);
//		m.nest();
//		assert true == true;
//	}
//		//getImgHeight()
//	@Test
//	public void getImgHeightReturnsImgHeight() {
//		Model m = new Model(1,1,1,1);
//		m.setImgHeight(100);
//		assert m.getImgHeight() == 100;
//	}
//		//getDirection()
//	@Test
//	public void getDirectionReturnsDirection() {
//		Model m = new Model(1,1,1,1);
//		m.setDirection(Direction.UP);
//		assert m.getDirection() == Direction.UP;
//	}
//		//getImgWidth()
//	@Test
//	public void getImgWidthReturnsImgWidth() {
//		Model m = new Model(1,1,1,1);
//		m.setImgWidth(100);
//		assert m.getImgWidth() == 100;
//	}
//		//getGroundLevel()
//	@Test
//	public void getGroundLevelReturnsGroundLevel() {
//		Model m = new Model(1,1,1,1);
//		m.setGroundLevel(100);
//		assert m.getGroundLevel() == 100;
//	}
//		//getSceneNum()
//	@Test
//	public void getSceneNumReturnsSceneNum() {
//		Model m = new Model(1,1,1,1);
//		m.setSceneNum(100);
//		assert m.getSceneNum() == 100;
//	}
//		//getEnemyFrequency()
//	@Test
//	public void getEnemyFrequencyReturnsEnemyFrequency() {
//		Model m = new Model(1,1,1,1);
//		m.setEnemyFrequency(100);
//		assert m.getEnemyFrequency() == 100;
//	}
//		//getFoodFrequency()
//	@Test
//	public void getFoodFrequencyReturnsFoodFrequency() {
//		Model m = new Model(1,1,1,1);
//		m.setFoodFrequency(100);
//		assert m.getFoodFrequency() == 100;
//	}
//		//getSpecialfoodFrequency()
//	@Test
//	public void getSpecialFoodFrequencyReturnsSpecialFoodFrequency() {
//		Model m = new Model(1,1,1,1);
//		m.setSpecialfoodFrequency(100);
//		assert m.getSpecialfoodFrequency() == 100;
//	}
//		//getTotalLevelTicks()
//	@Test
//	public void getTotalLevelTicksReturnsTotalLevelTicks() {
//		Model m = new Model(1,1,1,1);
//		m.setTotalLevelTicks(100);
//		assert m.getTotalLevelTicks() == 100;
//	}
//		//getIndexOfGP()
//	@Test
//	public void getIndexOfGPReturnsIndexOfGP() {
//		Model m = new Model(1,1,1,1);
//		m.setIndexOfGP(100);
//		assert m.getIndexOfGP() == 100;
//	}
//	@Test
//	public void getBirdReturnsBirdSprite() {
//		Model m = new Model(1,1,1,1);
//		Sprite b = Sprite.OSPREY;
//		m.setBird(b);
//		assert m.getBird() == Sprite.OSPREY;
//	}
//	//Tests for Sprite
//		//getName()
//	@Test
//	public void getNameReturnsName() {
//		Sprite s = Sprite.OSPREY;
//		assert s.getName() == "osprey";
//	}
//}