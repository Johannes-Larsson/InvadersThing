package com.larsson.johannes.quickThing.enemies;

import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Enemy;
import com.larsson.johannes.quickThing.Game;
import com.larsson.johannes.quickThing.Scenes;


public class TestEnemy extends Enemy{

	public TestEnemy() {
		super(new Animation(Assets.avoidingEnemy, 60, 60, 1, 10, 10, 0));
		lives = 1;
		killScore = 10;
		sinkSpeed = .15f;
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
