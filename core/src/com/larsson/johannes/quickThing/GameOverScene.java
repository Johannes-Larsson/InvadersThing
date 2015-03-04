package com.larsson.johannes.quickThing;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScene extends Scene {
	
	private Button retry, restart;
	
	public GameOverScene() {
		final int buttonW = 500, buttonH = 120, offset = 60;
		retry = new Button("retry level", Game.V_W / 2 - buttonW / 2, Game.V_H / 2 - buttonH / 2 + offset, buttonW, buttonH);
		restart = new Button("restart game", Game.V_W / 2 - buttonW / 2, Game.V_H / 2 - buttonH / 2 - offset, buttonW, buttonH);
		super.initialize();
	}
	
	public void update() {
		super.update();
		
		if (restart.isClicked()) {
			Game.preferences.clear();
			Game.preferences.flush();
			Scenes.game.initialize();
			Game.setScene(Scenes.game);
		}
		else if (retry.isClicked() && false) {
			Scenes.game.initialize();
			Game.setScene(Scenes.game);
		}
	}
	
	public void draw(SpriteBatch batch) {
		String s = "game over";
		Assets.smallFont.draw(batch, s, Game.V_W / 2 - Assets.smallFont.getBounds(s).width / 2, Game.V_H - 150);
		
		//retry.draw(batch);
		restart.draw(batch);
		
		super.draw(batch);
	}
}
