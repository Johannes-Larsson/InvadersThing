package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SettingsScene extends Scene {

	private Button back, up, down;
	private GlyphLayout layout;
	private int controlWidth;

	public static final String controlWidthString = "controlWidth";
	
	public SettingsScene() {
		back = new Button("back", 70, 30);

		final int sep = 100;
		up = new Button("+", Game.V_W / 2 + sep, Game.V_H / 2);
		down = new Button("-", Game.V_W / 2 - sep, Game.V_H / 2);

		super.initialize();
		layout = new GlyphLayout();

		controlWidth = Game.preferences.getInteger(controlWidthString, 25);
	}
	
	public void update() {
		super.update();
		
		if (back.isClicked()) {
			Game.setScene(Scenes.menu);
			Game.preferences.flush();
		}

		if (up.isClicked()) {
			if (controlWidth < 25) controlWidth++;
			Game.preferences.putInteger(controlWidthString, controlWidth);
		}
		if (down.isClicked()) {
			if (controlWidth > 3) controlWidth--;
			Game.preferences.putInteger(controlWidthString, controlWidth);
		}
	}
	
	public void draw(SpriteBatch batch) {
		String s = "control width";
		layout.setText(Assets.smallFont, s);
		Assets.smallFont.draw(batch, s, Game.V_W / 2 - layout.width / 2, Game.V_H / 2 - 50);


		layout.setText(Assets.smallFont, controlWidth + "");
		Assets.smallFont.draw(batch, controlWidth + "", Game.V_W / 2 - layout.width / 2, Game.V_H / 2);
		
		back.draw(batch);
		up.draw(batch);
		down.draw(batch);
		
		super.draw(batch);
	}
}