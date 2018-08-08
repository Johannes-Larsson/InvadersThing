package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScene extends Scene {
	
	private Button retry, restart;
	private GlyphLayout layout;
	
	public GameOverScene() {
		final float distFromCenter = 30;
		retry = new Button("retry level", Game.V_W / 2, Game.V_H / 2 - distFromCenter);
		restart = new Button("restart game", Game.V_W / 2, Game.V_H / 2 + distFromCenter);
		layout = new GlyphLayout();
		super.initialize();
	}
	
	public void update() {
		super.update();
		
		if (restart.isClicked()) {
			Game.preferences.remove("level");
			Game.preferences.remove("hp");
			Game.preferences.remove("rockets");
			Game.preferences.remove("score");
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
		layout.setText(Assets.smallFont, s);
		Assets.smallFont.draw(batch, s, Game.V_W / 2 - layout.width / 2, Game.V_H - 150);
		
		//retry.draw(batch);
		restart.draw(batch);
		
		super.draw(batch);
	}
}
