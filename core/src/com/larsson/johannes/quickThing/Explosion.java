package com.larsson.johannes.quickThing;

public class Explosion extends GameObject {

	private boolean damaging;
	
	public Explosion(GameObject origin) {
		this(origin.getX(), origin.getY(), origin.getW());
		vx = origin.vx;
		vy = origin.vy;
		damaging = false;
	}
	
	public Explosion(float x, float y, float size) {
		super(new Animation(Assets.explosion, size, size, 6, 20, 20, 5), x, y);
		Game.screenShake += (int)(.3f * size) - Game.screenShake / 2;
		damaging = true;
	}
	
	public String getType() { return "explosion"; }
	
	public void update() {
		if (animation.isAtEnd()) dead = true;
		super.update();
	}
	
	public void onCollide(GameObject g) {
		if (g.getType() == "enemy" && damaging) {
			g.dead = true;
		}
	}
}
