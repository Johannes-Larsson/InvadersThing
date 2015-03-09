package com.larsson.johannes.quickThing.enemies;

import com.badlogic.gdx.math.MathUtils;
import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Bullet;
import com.larsson.johannes.quickThing.Enemy;
import com.larsson.johannes.quickThing.Game;
import com.larsson.johannes.quickThing.Player;
import com.larsson.johannes.quickThing.Scenes;

public class SmarterShooter extends Enemy {	
	
	public SmarterShooter() {
		super(new Animation(Assets.smarterShooter, 90, 90, 1, 14, 14, 0));
		lives = 1;
		killScore = 20;
		sinkSpeed = .07f;
		shootTime = 80;
	}
	
	public void update() {
		final float speed = 1f;
		Player p = Scenes.game.player;
		
		if (p.getCenterX() > getCenterX()) {
			vx = speed;
		}
		else if (p.getCenterX() < getCenterX()) {
			vx = -speed;
		}
		
		moveAwayFromHitWall(0);
		
		super.update();
	}
	
	public void onShoot() {
		Bullet b = new Bullet(getCenterX(), getCenterY(), 7, -MathUtils.PI / 2, 1);
		myBullets.add(b);
		Game.getScene().toAdd.add(b);
	}
}
