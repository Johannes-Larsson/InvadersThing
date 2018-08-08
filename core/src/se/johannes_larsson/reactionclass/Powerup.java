package se.johannes_larsson.reactionclass;

public class Powerup extends GameObject {
	
	public Powerup(float x, float y) {
		super(new Animation(Assets.powerup, 32, 32, 1, 12, 12, 0), x, y);
		killOnScreenExit = true;
	}
	
	public String getType() { return "powerup"; }
	
	public void update() {
		final float targetH = 100;
		if (Math.abs(getY() - targetH) < 40) {
			vy = -.3f;
		}
		else vy = -2f;
		super.update();
	}
}
