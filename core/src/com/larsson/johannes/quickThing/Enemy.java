package com.larsson.johannes.quickThing;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;

public class Enemy extends GameObject {
	
	protected final int standardHeight = 200;
	private static final int spawnPadding = 150;
	
	protected int killScore;
	protected boolean hasSunk;
	protected float sinkSpeed;
	protected static ArrayList<Bullet> myBullets;
	protected int shootTime;
	
	private int shootTimer;
	
	public Enemy(Animation animation) {
		super(animation, getSpawnX(), Game.V_H);
		move(-getW() / 2, getH());
		myBullets = new ArrayList<Bullet>();
		lives = 1;
		sinkSpeed = .5f;
		killScore = 10;
		killOnScreenExit = true;
		shootTime = -1;
	}
	
	private static float getSpawnX() {
		return MathUtils.random(spawnPadding, Game.V_W - spawnPadding);
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
	
	protected void onShoot() {
		
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
		if (getX() < padding) {
			vx = 0;
			hitWall = Direction.Left;
		}
		else if (getX() + getW() + padding > Game.V_W) {
			vx = 0;
			hitWall = Direction.Right;
		}
		else hitWall = Direction.None;
		
		if (shootTime != -1 && hasSunk) {
			shootTimer++;
			if (shootTimer >= shootTime) {
				shootTimer = 0;
				onShoot();
			}
		}
		
		super.update();
	}
	
	protected void setSidewaySpeed(float speed) {
		if (MathUtils.randomBoolean()) vx = speed;
		else vx = -speed;
	}
	
	protected void moveAwayFromHitWall(float speed) {
		if (hitWall == Direction.Left) vx = speed;
		else if (hitWall == Direction.Right) vx = -speed;
	}
}
