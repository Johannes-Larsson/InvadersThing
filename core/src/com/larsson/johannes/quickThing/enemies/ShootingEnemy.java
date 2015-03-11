package com.larsson.johannes.quickThing.enemies;

import com.badlogic.gdx.math.MathUtils;
import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Bullet;
import com.larsson.johannes.quickThing.Enemy;
import com.larsson.johannes.quickThing.Game;
import com.larsson.johannes.quickThing.Scenes;

public class ShootingEnemy extends Enemy {

	//final int shootTime = 220;
	
	private int directionChangeCounter;
	
	public ShootingEnemy() {
		super(new Animation(Assets.shootingEnemy, 84, 84, 1, 14, 14, 0));
		//vy = -3;
		lives = 1;
		killScore = 15;
		sinkSpeed = .1f;
		shootTime = 80;
	}
	
	public void onShoot() {
		Bullet b = new Bullet(getCenterX(), getY(), 7, (float)Math.PI / -2, 1);
		Scenes.game.toAdd.add(b);
		myBullets.add(b);
	}
	
	public void update() {
		if (directionChangeCounter < 300) {
			directionChangeCounter++;
		}
		else {
			directionChangeCounter = 0;
			setSidewaySpeed(1.3f);
		}
		
		moveAwayFromHitWall(1);
		
		super.update();
	}
}
