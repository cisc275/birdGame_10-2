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
}
