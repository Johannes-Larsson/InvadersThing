package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FloatingText extends GameObject {
	
	String text;
	int lifeTime;
	int maxLife;
	
	public FloatingText(String text, float x, float y, int maxLife) {
		super(new Animation(Assets.bullet, x, y, 0, 0, 0, 0), x, y);
		this.text = text;
		this.maxLife = lifeTime = maxLife;
	}
	
	public void update() {
		lifeTime--;
		if (lifeTime <= 0) Game.getScene().toRemove.add(this);
		move(0, -2);
	}
	
	public void draw(SpriteBatch batch) {
		Color oldColor = Assets.smallFont.getColor();
		Assets.smallFont.setColor(new Color(Scenes.game.getPrimaryColor().r, Scenes.game.getPrimaryColor().g, Scenes.game.getPrimaryColor().b, (float)lifeTime / maxLife));
		Assets.smallFont.draw(batch, text, getX(), getY());
		Assets.smallFont.setColor(oldColor);
	}
	
	public void drawShadow(SpriteBatch batch) {
		//dont draw a shadow :^)))
	}
}
