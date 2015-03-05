package com.larsson.johannes.quickThing;

import com.badlogic.gdx.math.MathUtils;

public class Cloud extends GameObject {

	float originalX;
	
	public Cloud(int clouds) {
		super(new Animation(Assets.cloud, 80, 40, 1, 20, 10, 0), MathUtils.random(Game.V_W + 100) - 200, Game.V_H + 50);
		vy =  (Game.V_H + 40) / -(float)clouds;
		originalX = getX();
		System.out.println(String.valueOf(vy));
		shadowDepth = .05f;
		animation.sprite.setAlpha(.8f);
		paralax = GameScene.paralax * .7f;
	}
	
	public String getType() { return "cloud"; }
	
	public void update() {
		if (getY() < -40) {
			setX(MathUtils.random(Game.V_W + 100) - 200);
			setY(Game.V_H + 40);
		}
		
		super.update();
	}
}
