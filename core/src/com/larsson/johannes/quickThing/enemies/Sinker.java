package com.larsson.johannes.quickThing.enemies;

import java.util.ArrayList;

import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Enemy;
import com.larsson.johannes.quickThing.Game;
import com.larsson.johannes.quickThing.GameObject;

public class Sinker extends Enemy {

	final float speed = 2;
	
	public Sinker() {
		super(new Animation(Assets.sinker, 64, 64, 1, 12, 12, 0));
		sinkSpeed = .35f;
		killScore = 15;
		lives = 1;
	}

	public void update() {
		float dx = Float.MAX_VALUE;
		ArrayList<GameObject> bullets = new ArrayList<GameObject>();
		for (GameObject g : Game.getScene().objects) if (g.getType().contains("bullet")) bullets.add(g);
		
		if (bullets.size() > 0) {
			GameObject closestBullet = bullets.get(0);
			float dist = distTo(closestBullet);
			for (GameObject g : bullets) {
				float nDist = distTo(g);
				if (nDist < dist) {
					closestBullet = g;
					dist = nDist;
				}
			}
			if (dist < 300) {
				dx = getCenterX() - closestBullet.getCenterX();
				if (Math.abs(dx) < 30) {
					if (dx < 0) vx = -speed;
					else vx = speed;
				}
			}
			else if (Math.abs(vx) > 0) vx /= Math.abs(vx);
			else setSidewaySpeed(1);
		}
		
		super.update();
	}
	
}
