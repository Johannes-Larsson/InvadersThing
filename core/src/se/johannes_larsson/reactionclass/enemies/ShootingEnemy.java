package se.johannes_larsson.reactionclass.enemies;

import com.badlogic.gdx.math.MathUtils;
import se.johannes_larsson.reactionclass.Animation;
import se.johannes_larsson.reactionclass.Assets;
import se.johannes_larsson.reactionclass.Bullet;
import se.johannes_larsson.reactionclass.Enemy;
import se.johannes_larsson.reactionclass.Game;
import se.johannes_larsson.reactionclass.Scenes;

public class ShootingEnemy extends Enemy {

	//final int shootTime = 220;
	
	private int directionChangeCounter;
	
	public ShootingEnemy() {
		super(new Animation(Assets.shootingEnemy, 84, 84, 1, 14, 14, 0));
		//vy = -3;
		lives = 1;
		killScore = 15;
		sinkSpeed = .1f;
		shootTime = 80;
	}
	
	public void onShoot() {
		Bullet b = new Bullet(getCenterX(), getY(), 7, (float)Math.PI / -2, 1);
		Scenes.game.toAdd.add(b);
		myBullets.add(b);
	}
	
	public void update() {
		if (directionChangeCounter < 300) {
			directionChangeCounter++;
		}
		else {
			directionChangeCounter = 0;
			setSidewaySpeed(1.3f);
		}
		
		moveAwayFromHitWall(1);
		
		super.update();
	}
}
