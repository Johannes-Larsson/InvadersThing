package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject {
	
	protected enum Direction { Left, Right, None }

	protected Direction hitWall;
	public final String type = "base";
	public String getType() { return type; }
	
	protected float shadowDepth;
	
	public Animation animation;
	
	public float depth;
	public int lives;
	public float vx, vy;
	public boolean dead;
	public boolean dontCallOnDestroy;
	public boolean killOnScreenExit;
	
	protected float paralax;
	
	public GameObject(Animation animation, float x, float y) {
		this.animation = animation;
		animation.sprite.setOriginCenter();
		setX(x);
		setY(y);
		shadowDepth = .1f;
		paralax = GameScene.PARALAX;
		hitWall = Direction.None;
	}
	
	public void update() {
		if (hitWall == Direction.None) move(Scenes.game.player.vx * paralax, 0);
		else {
			if (hitWall == Direction.Left) move(1, 0);
			else move(-1, 0);
		}
		
		move(vx, vy);
		if (killOnScreenExit && !isOnScreen()) dead = true;
		if (dead) {
			Game.getScene().toRemove.add(this);
			if (!dontCallOnDestroy) onDestroy();
		}
	}
	
	public void onDestroy() {
		
	}
	
	public void onCollide(GameObject object) {
		
	}
	
	public void draw(SpriteBatch batch) {
		animation.draw(batch);
	}
	
	public void drawShadow(SpriteBatch batch) {
		final float scale = .9f;
		Color oldColor = animation.sprite.getColor();
		animation.sprite.setColor(new Color(.4f, .4f, .4f, .5f));
		float oldX = animation.sprite.getX();
		float oldY = animation.sprite.getY();
		animation.sprite.setX(oldX + (Game.V_W / 2 - oldX - animation.sprite.getWidth() / 2) * shadowDepth);
		animation.sprite.setY(oldY + (Game.V_H / 2 - oldY - animation.sprite.getHeight() / 2) * shadowDepth);
		animation.sprite.setScale(scale);
		animation.sprite.draw(batch);
		animation.sprite.setColor(oldColor);
		animation.sprite.setX(oldX);
		animation.sprite.setY(oldY);
		animation.sprite.setScale(1);
	}
	
	public void setX(float x) {
		animation.sprite.setX(x);
	}
	
	public void setY(float y) {
		animation.sprite.setY(y);
	}
	
	public float getX() {
		return animation.sprite.getX();
	}
	
	public float getY() {
		return animation.sprite.getY();
	}
	
	public float getW() {
		return animation.sprite.getWidth();
	}
	
	public float getH() {
		return animation.sprite.getHeight();
	}
	
	public float getCenterX() {
		return getX() + getW() / 2;
	}
	
	public float getCenterY() {
		return getY() + getH() / 2;
	}
	
	public float angleTo(GameObject g) {
		return angleTo(g.getX(), g.getY());
	}
	
	public float angleTo(float x, float y) {
		return (float)Math.atan2(y - getY(), x - getX());
	}
	
	public void move(float x, float y) {
		setX(getX() + x);
		setY(getY() + y);
	}
	
	public void setPos(float x, float y) {
		setX(x);
		setY(y);
	}
	
	public boolean isOnScreen() {
		return intersects(0, 0, Game.V_W, Game.V_H);
	}
	
	public boolean intersects(float x, float y, float w, float h) {
		return (getX() >= x && getX() <= x + w 
				|| getX() + getW() >= x && getX() + getW() <= x + w)
				&& (getY() >= y && getY() <= y + w || getY() + getW() >= y && getY() + getW() <= y + w);
	}
	
	public boolean intersects(GameObject g) {
		return intersects(g.getX(), g.getY(), g.getW(), g.getH());
	}
	
	public float distTo(float x, float y) {
		return (float) Math.sqrt(Math.pow(x - this.getCenterX(),  2) + Math.pow(y - this.getCenterY(), 2));
	}
	
	public float distTo(GameObject g) {
		return distTo(g.getCenterX(), g.getCenterY());
	}
}
