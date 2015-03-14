package com.larsson.johannes.quickThing.enemies;

import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Enemy;
import com.larsson.johannes.quickThing.Scenes;

public class AimingEnemy extends Enemy {

	public AimingEnemy() {
		super(new Animation(Assets.dumbEnemy, 72, 72, 1, 12, 12, 0));
		
		lives = 1;
		killScore = 20;
		sinkSpeed = .5f;
		setSidewaySpeed(1);
		shootTime = 100;
	}

	public void update() {
		super.update();
		moveAwayFromHitWall(1);
	}
	
	public void onShoot() {
		final float maxAngle = .35f;
		final float down = -(float)Math.PI / 2;
		float a = angleTo(Scenes.game.player);
		if (a > maxAngle + down) a = maxAngle + down;
		if (a < down - maxAngle) a = down - maxAngle;
		shoot(6, a);
	}
}
