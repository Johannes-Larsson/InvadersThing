package se.johannes_larsson.reactionclass;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import se.johannes_larsson.reactionclass.enemies.AimingEnemy;
import se.johannes_larsson.reactionclass.enemies.DumbEnemy;
import se.johannes_larsson.reactionclass.enemies.ShootingEnemy;
import se.johannes_larsson.reactionclass.enemies.Sinker;
import se.johannes_larsson.reactionclass.enemies.SmarterShooter;
import se.johannes_larsson.reactionclass.enemies.TankyEnemy;
import se.johannes_larsson.reactionclass.enemies.TestEnemy;
import se.johannes_larsson.reactionclass.enemies.TripleShooter;

public class EnemyFactory {
	static final int noOfEnemyClasses = 8;
	
	public static Enemy randomEnemy(int min, int max) {
		
		if (max > noOfEnemyClasses - 1) max = noOfEnemyClasses - 1;
		
		return getEnemy(MathUtils.random(min, max));
	}
	
	public static Enemy randomEnemy() {
		return randomEnemy(0, noOfEnemyClasses);
	}
	
	public static ArrayList<Enemy> getWave(int level) {
		ArrayList<Enemy> e = new ArrayList<Enemy>();
		
		int max = 1 + level / 2;
		if (max > noOfEnemyClasses - 1) max = noOfEnemyClasses - 1;
		int min = max - 3;
		if (min < 0) min = 0;
		
		int no = 10; // max enemies per level
		if (no > level) no = (int)(level * .7) + 1;
		
		for (int i = 0; i < no; i++) e.add(randomEnemy(min, max));
		
		return e;
	}
	
	public static Enemy getEnemy(int i) {
		switch (i)
		{
		default: return new DumbEnemy();
		case 1: return new ShootingEnemy();
		case 2: return new TankyEnemy();
		case 3: return new Sinker();
		case 4: return new SmarterShooter();
		case 5: return new TestEnemy();
		case 6: return new TripleShooter();
		case 7: return new AimingEnemy();
		}
	}
}
