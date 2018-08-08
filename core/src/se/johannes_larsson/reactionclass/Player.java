package se.johannes_larsson.reactionclass;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Player extends GameObject {
	
	final float friction = .82f, acceleration = 2.5f;
	
	final int controlAreaW = 150, controlAreaH = 500;
	final int rightX = Game.V_W - controlAreaW, rightY = 0;
	final int leftX = 0, leftY = 0;
	final int shootX = leftX + controlAreaW, shootY = 0, shootW = Game.V_W - 2 * controlAreaW, shootH = controlAreaH;
	final int shoot2X = 0, shoot2Y = controlAreaH, shoot2W = Game.V_W, shoot2H = Game.V_H / 3;
	final int invTime = 100;

	private PlayerInputHelper pih;
	
	private int shootSpeed, shootCounter, invTimer;
	
	public int rockets;
	
	ArrayList<Bullet> myBullets;
	
	public Player() {
		super(new Animation(Assets.player, 96, 96, 1, 16, 16, 0), Game.V_W / 2, 60);
		myBullets = new ArrayList<Bullet>();
		animation.sprite.setOriginCenter();
		shootSpeed = 30;
		rockets = 0;
		pih = new PlayerInputHelper();
		//lives = 10;
	}
	
	public void update() {
		
		if (lives <= 0) Game.setScene(Scenes.gameOver);
		
		if (shootCounter > 0) shootCounter--;
		
		if (pih.moveLeft()) {
			vx -= acceleration;
		}
		if (pih.moveRight()) {
			vx += acceleration;
		}
		
		vx *= friction;
		
		if (getX() < 0 && vx < 0) vx = 0;
		if (getX() + getW() > Game.V_W && vx > 0) vx = 0;
		
		if (shootCounter == 0) {
			if (pih.shootRegular()) {
				Bullet b = new Bullet(getCenterX(), getCenterY(), 10, (float)(Math.PI / 2) + animation.sprite.getRotation() * MathUtils.degRad, 1);
				Scenes.game.toAdd.add(b);
				myBullets.add(b);
				shootCounter = shootSpeed;
			}
			else if (pih.shootRockets() && rockets > 0) {
				//add rocket
				Scenes.game.toAdd.add(new Rocket(getCenterX(), getCenterY()));
				shootCounter = shootSpeed;
				rockets--;
			}
		}
		
		if (invTimer > 0) {
			invTimer--;
			animation.sprite.setAlpha((invTimer / 20 % 2 == 0 ? .3f : .5f));
		}
		else animation.sprite.setAlpha(1);
		
		
		animation.sprite.setRotation(-vx);
		
		super.update();
	}
	
	public void onCollide(GameObject g) {
		if (g.getType().contains("bullet")) {
			if (!myBullets.contains(g) && !g.dead) {
				g.dead = true;
				if (invTimer == 0) {
					lives -= Integer.valueOf(g.getType().split(":")[1]);
					invTimer = invTime;
				}
			}
		}
		else if (g.getType() == "enemy") {
			Scenes.game.toRemove.add(g);
			if (invTimer == 0) {
				lives -= 1;
				invTimer = invTime;
			}
		}
		else if (g.getType() == "powerup" && !g.dead) {
			rockets += 1;
			System.out.println("adding rocket");
			g.dead = true;
		}
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}
}
