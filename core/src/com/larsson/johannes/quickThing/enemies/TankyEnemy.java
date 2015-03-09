package com.larsson.johannes.quickThing.enemies;

import com.badlogic.gdx.math.MathUtils;
import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Enemy;
import com.larsson.johannes.quickThing.Scenes;

public class TankyEnemy extends Enemy {
	
	private boolean hasDirection;
	private int timeSinceDirectionChange;
	
	public TankyEnemy() {
		super(new Animation(Assets.tankyEnemy, 70, 70, 2, 12, 12, 0));
		lives = 2;
		killScore = 15;
		sinkSpeed = .1f;
		setSidewaySpeed(.6f);
	}
	
	public void update() {
		timeSinceDirectionChange++;
		final float speed = 1f;
		if (hasSunk && !hasDirection) {
			vx = (MathUtils.randomBoolean() ? -speed : speed);
			hasDirection = true;
		}
		moveAwayFromHitWall(speed);
		
		animation.setFrame(lives - 1);
		
		if (Math.abs(getCenterX() - Scenes.game.player.getCenterX()) < 20 && timeSinceDirectionChange > 30) {
			timeSinceDirectionChange = 0;
			vx = -vx;
		}
		
		super.update();
	}
}
