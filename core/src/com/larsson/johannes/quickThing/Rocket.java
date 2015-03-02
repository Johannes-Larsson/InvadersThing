package com.larsson.johannes.quickThing;

public class Rocket extends GameObject {

	public Rocket(float x, float y) {
		super(new Animation(Assets.powerup, 32, 32, 1, 12, 12, 0), x, y);
		vy = 5;
		move(-getW() / 2, 0);
		killOnScreenExit = true;
	}
	
	public String getType() { return "rocket"; }
	
	public void update() {
		vy *= 1.01f;
		super.update();
	}
	
	public void onCollide(GameObject g) {
		if (g.getType().contains("enemy")) dead = true;
	}
	
	public void onDestroy() {
		if (isOnScreen()) {
			final int size = 200;
			Game.getScene().toAdd.add(new Explosion(getCenterX() - size / 2, getCenterY() - size / 2, size));
		}
	}
}
