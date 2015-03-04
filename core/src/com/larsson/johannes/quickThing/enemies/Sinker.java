package com.larsson.johannes.quickThing.enemies;

import com.larsson.johannes.quickThing.Animation;
import com.larsson.johannes.quickThing.Assets;
import com.larsson.johannes.quickThing.Enemy;

public class Sinker extends Enemy {

	public Sinker() {
		super(new Animation(Assets.sinker, 64, 64, 1, 12, 12, 0));
		sinkSpeed = .35f;
		killScore = 15;
		lives = 1;
	}

}
