package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends GameObject {
	
	public String getType() { return "bullet:" + damage; }
	
	float speed;
	float angle;
	int damage;
	
	public Bullet(float x, float y, float speed, float angle, int damage) {
		super(new Animation(Assets.bullet, 10, 15, 1, 4, 6, 0), x, y);
		
		this.speed = speed;
		this.angle = angle;
		this.damage = damage;
		animation.sprite.setRotation(angle * MathUtils.radDeg + 90);
		
		move(-getW() / 2, -getH() / 2);
		
		vx = (float)Math.cos(angle) * speed;
		vy = (float)Math.sin(angle) * speed;
		killOnScreenExit = true;

		Assets.shoot.play();
	}

	public void draw(SpriteBatch batch) {
		this.animation.sprite.setColor(Scenes.game.getPrimaryColor());
		super.draw(batch);
	}
}
