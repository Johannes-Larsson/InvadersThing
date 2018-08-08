package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class PlayerInputHelper {

	private int areaWidth;

	public PlayerInputHelper() {
		areaWidth = (int)((Game.preferences.getInteger(SettingsScene.controlWidthString, 25) / 100f) * Game.V_W);
		System.out.println("control area width: " + areaWidth);
	}
	
	public boolean moveLeft() {
		return Input.areaIsClicked(0, 0, areaWidth, Game.V_H) || Gdx.input.isKeyPressed(Keys.LEFT);
	}
	
	public boolean moveRight() {
		return Input.areaIsClicked(areaWidth, 0, areaWidth, Game.V_H) || Gdx.input.isKeyPressed(Keys.RIGHT);
	}
	
	public boolean shootRegular() {
		return Input.areaIsClicked(Game.V_W - areaWidth, 0, areaWidth, Game.V_H) || Gdx.input.isKeyPressed(Keys.X);
	}
	
	public boolean shootRockets() {
		return Input.areaIsClicked(Game.V_W - areaWidth * 2, 0, areaWidth, Game.V_H) || Gdx.input.isKeyPressed(Keys.C);
	}
}
