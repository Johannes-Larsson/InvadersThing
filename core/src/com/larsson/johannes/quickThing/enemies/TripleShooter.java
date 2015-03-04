package com.larsson.johannes.quickThing.enemies;

import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Bullet;
import com.larsson.johannes.quickThing.Enemy;
import com.larsson.johannes.quickThing.Scenes;

public class TripleShooter extends Enemy {

	private int shootTimer;
	
	public TripleShooter() {
		super(new Animation(Assets.tripleShooter, 84, 84, 2, 14, 14, 0));
		lives = 2;
		killScore = 25;
		sinkSpeed = .15f;
		shootTime = 200;
	}
	
	public void update() {
		animation.setFrame(lives - 1);		
		super.update();
	}
	
	public void onShoot() {
		for (int i = -1; i < 2; i++) {
			Bullet b = new Bullet(getCenterX(), getY(), 7, (float)Math.PI / -2 + i * .2f, 1);
			Scenes.game.toAdd.add(b);
			myBullets.add(b);
		}
	}
}
