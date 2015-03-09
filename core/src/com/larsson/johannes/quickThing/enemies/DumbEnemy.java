package com.larsson.johannes.quickThing.enemies;

import com.badlogic.gdx.math.MathUtils;
import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Enemy;
import com.larsson.johannes.quickThing.Game;


public class DumbEnemy extends Enemy {

	int directionChangeCounter;
	
	public DumbEnemy() {
		super(new Animation(Assets.dumbEnemy, 64, 64, 1, 12, 12, 0));
		lives = 1;
		killScore = 10;
		sinkSpeed = .4f;
		setSidewaySpeed(.6f);
	}

	public void update() {
		if (directionChangeCounter < 300) {
			directionChangeCounter++;
		}
		else {
			directionChangeCounter = 0;
			if (MathUtils.randomBoolean()) {
				vx = 1.5f;
			}
			else {
				vx = -1.5f;
			}
		}
		
		super.update();
	}
}
