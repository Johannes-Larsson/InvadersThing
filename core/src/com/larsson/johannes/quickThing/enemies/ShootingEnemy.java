package com.larsson.johannes.quickThing.enemies;

import com.badlogic.gdx.math.MathUtils;
import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Bullet;
import com.larsson.johannes.quickThing.Enemy;
import com.larsson.johannes.quickThing.Game;
import com.larsson.johannes.quickThing.Scenes;

public class ShootingEnemy extends Enemy {

	final int shootTime = 220;
	
	private int shootTimer, directionChangeCounter;
	
	public ShootingEnemy() {
		super(new Animation(Assets.shootingEnemy, 84, 84, 1, 14, 14, 0));
		//vy = -3;
		lives = 1;
		killScore = 15;
		sinkSpeed = .1f;
	}
	
	public void update() {
		if (shootTimer > 0) {
			shootTimer--;
		}
		else if (hasSunk) {
			shootTimer = shootTime;
			Bullet b = new Bullet(getCenterX(), getY(), 7, (float)Math.PI / -2, 1);
			Scenes.game.toAdd.add(b);
			myBullets.add(b);
		}
		
		if (directionChangeCounter < 300) {
			directionChangeCounter++;
		}
		else {
			directionChangeCounter = 0;
			if (MathUtils.randomBoolean()) {
				vx = 1f;
			}
			else {
				vx = -1f;
			}
		}
		
		moveAwayFromHitWall(1);
		
		super.update();
	}
}
