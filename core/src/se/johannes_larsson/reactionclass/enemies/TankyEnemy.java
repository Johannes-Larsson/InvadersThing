package se.johannes_larsson.reactionclass.enemies;

import com.badlogic.gdx.math.MathUtils;
import se.johannes_larsson.reactionclass.Animation;
import se.johannes_larsson.reactionclass.Assets;
import se.johannes_larsson.reactionclass.Enemy;
import se.johannes_larsson.reactionclass.GameObject;
import se.johannes_larsson.reactionclass.Scenes;

public class TankyEnemy extends Enemy {
	
	private boolean hasDirection;
	
	public TankyEnemy() {
		super(new Animation(Assets.tankyEnemy, 70, 70, 2, 12, 12, 0));
		lives = 2;
		killScore = 15;
		sinkSpeed = .1f;
		setSidewaySpeed(.6f);
	}
	
	public void update() {
		final float speed = 2.5f;
		if (hasSunk && !hasDirection) {
			vx = (MathUtils.randomBoolean() ? -speed : speed);
			hasDirection = true;
		}
		
		moveAwayFromHitWall(speed);
		
		animation.setFrame(lives - 1);

		super.update();
	}
	
	protected void onBulletCollide() {
		vx = -vx;
	}
}
