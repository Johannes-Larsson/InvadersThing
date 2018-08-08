package se.johannes_larsson.reactionclass.enemies;

import com.badlogic.gdx.math.MathUtils;
import se.johannes_larsson.reactionclass.Animation;
import se.johannes_larsson.reactionclass.Assets;
import se.johannes_larsson.reactionclass.Enemy;
import se.johannes_larsson.reactionclass.Game;


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
