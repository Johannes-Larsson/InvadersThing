package se.johannes_larsson.reactionclass.enemies;

import se.johannes_larsson.reactionclass.Animation;
import se.johannes_larsson.reactionclass.Assets;
import se.johannes_larsson.reactionclass.Bullet;
import se.johannes_larsson.reactionclass.Enemy;
import se.johannes_larsson.reactionclass.Scenes;

public class TripleShooter extends Enemy {

	private int shootTimer;
	
	public TripleShooter() {
		super(new Animation(Assets.tripleShooter, 84, 84, 2, 14, 14, 0));
		lives = 2;
		killScore = 25;
		sinkSpeed = .15f;
		shootTime = 115;
		setSidewaySpeed(.8f);
	}
	
	public void update() {
		animation.setFrame(lives - 1);	
		moveAwayFromHitWall(.8f);
		super.update();
	}
	
	public void onShoot() {
		for (int i = -1; i < 2; i++) {
			Bullet b = new Bullet(getCenterX(), getY(), 7, (float)Math.PI / -2 + i * .2f, 1);
			Scenes.game.toAdd.add(b);
			myBullets.add(b);
		}
	}
}
