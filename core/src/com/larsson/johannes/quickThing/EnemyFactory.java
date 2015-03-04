package com.larsson.johannes.quickThing;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.larsson.johannes.quickThing.enemies.DumbEnemy;
import com.larsson.johannes.quickThing.enemies.ShootingEnemy;
import com.larsson.johannes.quickThing.enemies.Sinker;
import com.larsson.johannes.quickThing.enemies.SmarterShooter;
import com.larsson.johannes.quickThing.enemies.TankyEnemy;
import com.larsson.johannes.quickThing.enemies.TestEnemy;
import com.larsson.johannes.quickThing.enemies.TripleShooter;

public class EnemyFactory {
	static final int noOfEnemyClasses = 7;
	
	public static Enemy randomEnemy(int min, int max) {
		
		if (max > noOfEnemyClasses - 1) max = noOfEnemyClasses - 1;
		
		return getEnemy(MathUtils.random(min, max));
	}
	
	public static Enemy randomEnemy() {
		return randomEnemy(0, noOfEnemyClasses);
	}
	
	public static ArrayList<Enemy> getWave(int level) {
		ArrayList<Enemy> e = new ArrayList<Enemy>();
		
		int max = 1 + level / 3;
		if (max > noOfEnemyClasses - 1) max = noOfEnemyClasses - 1;
		int min = max - 3;
		if (min < 0) min = 0;
		
		int no = 5;
		if (no > level) no = level + 1;
		
		for (int i = 0; i < no; i++) e.add(randomEnemy(min, max));
		
		return e;
	}
	
	public static Enemy getEnemy(int i) {
		switch (i)
		{
		default: return new DumbEnemy();
		case 0: return new DumbEnemy();
		case 1: return new ShootingEnemy();
		case 2: return new TankyEnemy();
		case 3: return new Sinker();
		case 4: return new SmarterShooter();
		case 5: return new TestEnemy();
		case 6: return new TripleShooter();
		}
	}
}
