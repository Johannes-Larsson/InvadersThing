package com.larsson.johannes.quickThing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScene extends Scene {
	
	Button newGame, continueGame;
	
	public MenuScene() {
		super.initialize();
		final int buttonW = 400, buttonH = 120, offset = 60;
		newGame = new Button("new game", Game.V_W / 2 - buttonW / 2, Game.V_H / 2 - buttonH / 2 + offset, buttonW, buttonH);
		continueGame = new Button("continue game", Game.V_W / 2 - buttonW / 2, Game.V_H / 2 - buttonH / 2 - offset, buttonW, buttonH);
	}
	
	public void update() {
		super.update();
		
		boolean canContinue = Scenes.game.getLevel() > 0;
		continueGame.color = (!canContinue ? Color.LIGHT_GRAY : Color.WHITE);
		
		if (newGame.isClicked()) {
			Game.preferences.clear();
			Game.preferences.flush();
			Scenes.game = new GameScene();
			Game.setScene(Scenes.intro);
		}
		else if (continueGame.isClicked() && canContinue) {
			Scenes.game = new GameScene();
			Game.setScene(Scenes.game);
		}
	}
	
	public void draw(SpriteBatch batch) {
		newGame.draw(batch);
		continueGame.draw(batch);
	}
}
