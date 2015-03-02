package com.larsson.johannes.quickThing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

public class Button {
	private float x, y, w, h;
	private String text;
	
	public Color color;
	
	public Button(String text, float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
		Assets.smallFont.draw(batch, text, x + w / 2 - b.width / 2, y + h / 2 - b.height / 2);
		Assets.smallFont.setColor(o);
	}
}
