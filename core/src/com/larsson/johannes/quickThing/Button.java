package com.larsson.johannes.quickThing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

public class Button {
	final float PADDING = 10;
	
	private float x, y, w, h;
	private String text;
	private TextBounds bounds;
	
	public Color color;
	
	public Button(String text, float x, float y) {
		bounds = Assets.smallFont.getBounds(text);
		this.x = x - bounds.width / 2 - PADDING;
		this.y = y - bounds.height / 2 - PADDING;
		this.w = bounds.width + PADDING * 2;
		this.h = bounds.height + PADDING * 2;
		this.text = text;
		color = Color.WHITE;
	}
	
	public boolean isClicked() { 
		return Input.areaIsClicked(x, y, w, h);
	}
	
	public void draw(SpriteBatch batch) {
		TextBounds b = Assets.smallFont.getBounds(text);
		Color o = Assets.smallFont.getColor();
		Assets.smallFont.setColor(color);
		Assets.smallFont.draw(batch, text, x + PADDING, y + bounds.height + PADDING);
		Assets.smallFont.setColor(o);
		//batch.draw(Assets.pixel, x, y, w, h);
	}
}
