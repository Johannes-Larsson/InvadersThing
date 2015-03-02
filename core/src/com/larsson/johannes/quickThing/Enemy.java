package com.larsson.johannes.quickThing;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;

public class Enemy extends GameObject {
	
	protected enum Direction { Left, Right, None }
	
	protected final int standardHeight = 200;
	protected static final int minSpawn = 100;
	protected static final int maxSpawn = Game.V_W - 100;
	
	protected int killScore;
	protected boolean hasSunk;
	protected Direction hitWall;
	protected float sinkSpeed;
	protected static ArrayList<Bullet> myBullets;
	
	public Enemy(Animation animation) {
		super(animation, MathUtils.random(minSpawn, maxSpawn), Game.V_H);
		move(-getW() / 2, getH());
		myBullets = new ArrayList<Bullet>();
		lives = 1;
		sinkSpeed = .5f;
		killScore = 10;
		killOnScreenExit = true;
	}
	
	public String getType()	{ return "enemy"; }
	
	public void onCollide(GameObject g) {
		if (g.getType().contains("bullet")) {
			if (!myBullets.contains(g) && !g.dead) {
				g.dead = true;
				lives -= Integer.valueOf(g.getType().split(":")[1]);
				//System.out.println("bullet hit, hp = " + lives);
			}
		}
		else if (g.getType() == "rocket") {
			g.dead = true;
		}
	}
	
	public void onDestroy() {
		Game.getScene().toAdd.add(new Explosion(this));
		Scenes.game.score += killScore;
		Scenes.game.toAdd.add(new FloatingText("+" + killScore, getX(), getY(), 60));
		if (MathUtils.randomBoolean((killScore  - Scenes.game.player.rockets) / 100f)) Game.getScene().toAdd.add(new Powerup(getX(), getY()));
	}
	
	public void update() {
		if (getY() > Game.V_H - standardHeight) vy = -5;
		else {
			vy = -sinkSpeed;
			hasSunk = true;
		}
		
		if (lives <= 0) {
			dead = true;
		}

		final float padding = 40;
		if (getX() < padding && vx < 0) {
			vx = 0;
			hitWall = Direction.Left;
		}
		else if (getX() + getW() + padding> Game.V_W && vx > 0) {
			vx = 0;
			hitWall = Direction.Right;
		}
		else hitWall = Direction.None;
		
		super.update();
	}
	
	protected void moveAwayFromHitWall(float speed) {
		if (hitWall == Direction.Left) vx = speed;
		else if (hitWall == Direction.Right) vx = -speed;
	}
}
