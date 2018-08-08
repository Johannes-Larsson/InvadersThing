package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
	final float PADDING = 10;
	
	private float x, y, w, h;
	private String text;
	private GlyphLayout layout;
	
	public Color color;
	
	public Button(String text, float x, float y) {
		layout = new GlyphLayout();
		layout.setText(Assets.smallFont, text);
		this.x = x - layout.width / 2 - PADDING;
		this.y = y - layout.height / 2 - PADDING;
		this.w = layout.width + PADDING * 2;
		this.h = layout.height + PADDING * 2;
		this.text = text;
		color = Color.WHITE;
	}
	
	public boolean isClicked() { 
		return Input.areaWasJustClicked(x, y, w, h);
	}
	
	public void draw(SpriteBatch batch) {
		layout.setText(Assets.smallFont, text);
		Color o = Assets.smallFont.getColor();
		Assets.smallFont.setColor(color);
		Assets.smallFont.draw(batch, text, x + PADDING, y + layout.height + PADDING);
		Assets.smallFont.setColor(o);
		//batch.draw(Assets.pixel, x, y, w, h);
	}
}
