package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScene extends Scene {
	
	Button newGame, continueGame, settings, about, quit;
	
	public MenuScene() {
		super.initialize();
		final float distFromCenter = 30;
		final int buttonW = 400, buttonH = 120, offset = 60;
		newGame = new Button("new game", Game.V_W / 2, Game.V_H / 2 + distFromCenter);
		continueGame = new Button("continue game", Game.V_W / 2, Game.V_H / 2 - distFromCenter);
		settings = new Button("settings", Game.V_W / 2, Game.V_H / 2 - distFromCenter * 3);
		quit = new Button("quit", Game.V_W / 2, Game.V_H / 2 - distFromCenter * 5);
	}
	
	public void update() {
		super.update();
		
		boolean canContinue = Scenes.game.getLevel() > 0;
		continueGame.color = (!canContinue ? Color.LIGHT_GRAY : Color.WHITE);
		
		if (newGame.isClicked()) {
			GameScene.clearGame();
			Scenes.game = new GameScene();
			Game.setScene(Scenes.intro);
		}
		else if (continueGame.isClicked() && canContinue) {
			Scenes.game = new GameScene();
			Game.setScene(Scenes.game);
		}
		else if (settings.isClicked()) {
			Game.setScene(Scenes.settings);
		}
		else if (quit.isClicked()) {
			Gdx.app.exit();
		}
	}
	
	public void draw(SpriteBatch batch) {
		newGame.draw(batch);
		continueGame.draw(batch);
		settings.draw(batch);
		quit.draw(batch);
	}
}
