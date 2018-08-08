package se.johannes_larsson.reactionclass.enemies;

import com.badlogic.gdx.math.MathUtils;
import se.johannes_larsson.reactionclass.Animation;
import se.johannes_larsson.reactionclass.Assets;
import se.johannes_larsson.reactionclass.Bullet;
import se.johannes_larsson.reactionclass.Enemy;
import se.johannes_larsson.reactionclass.Game;
import se.johannes_larsson.reactionclass.Player;
import se.johannes_larsson.reactionclass.Scenes;

public class SmarterShooter extends Enemy {	
	
	public SmarterShooter() {
		super(new Animation(Assets.smarterShooter, 90, 90, 1, 14, 14, 0));
		lives = 1;
		killScore = 20;
		sinkSpeed = .07f;
		shootTime = 80;
	}
	
	public void update() {
		final float speed = 1f;
		Player p = Scenes.game.player;
		
		if (p.getCenterX() > getCenterX()) {
			vx = speed;
		}
		else if (p.getCenterX() < getCenterX()) {
			vx = -speed;
		}
		
		moveAwayFromHitWall(0);
		
		super.update();
	}
	
	public void onShoot() {
		Bullet b = new Bullet(getCenterX(), getCenterY(), 7, -MathUtils.PI / 2, 1);
		myBullets.add(b);
		Game.getScene().toAdd.add(b);
	}
}
