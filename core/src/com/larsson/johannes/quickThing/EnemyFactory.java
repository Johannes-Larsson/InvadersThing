package com.larsson.johannes.quickThing;

import com.badlogic.gdx.math.MathUtils;
import com.larsson.johannes.quickThing.enemies.DumbEnemy;
import com.larsson.johannes.quickThing.enemies.ShootingEnemy;
import com.larsson.johannes.quickThing.enemies.SmarterShooter;
import com.larsson.johannes.quickThing.enemies.TankyEnemy;
import com.larsson.johannes.quickThing.enemies.TestEnemy;

public class EnemyFactory {
	static final int noOfEnemyClasses = 5;
	
	public static Enemy randomEnemy(int max) {
		
		if (max > noOfEnemyClasses - 1) max = noOfEnemyClasses - 1;
		
		switch (MathUtils.random(max))
		{
		case 0: return new DumbEnemy();
		case 1: return new TestEnemy();
		case 2: return new ShootingEnemy();
		case 4: return new TankyEnemy();
		case 3: return new SmarterShooter();
		default: return new DumbEnemy();
		}
	}
	
	public static Enemy randomEnemy() {
		return randomEnemy(noOfEnemyClasses);
	}
}
