package se.johannes_larsson.reactionclass.enemies;

import se.johannes_larsson.reactionclass.Animation;
import se.johannes_larsson.reactionclass.Assets;
import se.johannes_larsson.reactionclass.Enemy;
import se.johannes_larsson.reactionclass.Game;
import se.johannes_larsson.reactionclass.Scenes;


public class TestEnemy extends Enemy{

	public TestEnemy() {
		super(new Animation(Assets.avoidingEnemy, 60, 60, 1, 10, 10, 0));
		lives = 1;
		killScore = 10;
		sinkSpeed = .3f;
	}

	public void update() {
		
		if (Math.abs(getCenterX() - Scenes.game.player.getCenterX()) < 100) {
			if (getCenterX() < Scenes.game.player.getCenterX()) vx = -1f;
			else if (getCenterX() > Scenes.game.player.getCenterX()) vx = 1f;
		}
		else vx = 0;
				
		super.update();
	}
}
