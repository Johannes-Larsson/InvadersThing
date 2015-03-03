package com.larsson.johannes.quickThing;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScene extends Scene {
	
	public Player player;
	public int score;
	
	private int spawnCounter;
	private int level;
	private ArrayList<Enemy> wave;
	
	public GameScene() {
		this.initialize();
	}
	
	public void initialize() {
		super.initialize();
		player = new Player();
		objects.add(player);
		score = 0;
		wave = new ArrayList<Enemy>();
		loadGame();	
	}
	
	public void loadGame() {
		level = load("level", -1);
		player.lives = load("hp", 10);
		score = load("score", 0);
		player.rockets = load("rockets", 0);
	}
	
	public void saveGame() {
		Game.preferences.putInteger("level", getLevel());
		Game.preferences.putInteger("hp", player.lives);
		Game.preferences.putInteger("rockets", player.rockets);
		Game.preferences.putInteger("score", score);
		Game.preferences.flush();
	}
	
	private int load(String key, int def) {
		if (Game.preferences.contains(key)) return Game.preferences.getInteger(key);
		else return def;
	}
	
	private ArrayList<Enemy> getWave() {
		return EnemyFactory.getWave(level);
	}
	
	//TODO: combos
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void update() {
		spawnCounter -= 1;
		
		if (spawnCounter <= 0) {
			if (wave.size() > 0) {
				//spawn enemy from wave
				toAdd.add(wave.get(0));
				wave.remove(0);
				spawnCounter = 45;
			}
			else if (enemiesAlive() == 0) {
				wave = getWave();
				saveGame(); 
				setLevel(getLevel() + 1);
				spawnCounter = 45;
			}
		}

		super.update();
	}
	
	private int enemiesAlive() {
		int i = 0;
		for (GameObject e : objects) if (e.getType() == "enemy") i++;
		return i;
	}
	
	public void draw(SpriteBatch batch) {
		for (GameObject g : objects) g.drawShadow(batch);
		drawGUI(batch);
		super.draw(batch);
	}
	
	private void drawGUI(SpriteBatch batch) {
		final int padding = 20;
		String scoreS = "score: " + score;
		String levelS = "level: " + getLevel();
		TextBounds scoreB = Assets.smallFont.getBounds(scoreS);
		TextBounds levelB = Assets.smallFont.getBounds(levelS);
		Assets.smallFont.draw(batch, scoreS, padding, padding + scoreB.height);
		Assets.smallFont.draw(batch, levelS, Game.V_W - (padding + levelB.width), padding + levelB.height);
		System.out.println(player == null);
		drawImgWithText(batch, Assets.powerup, (Game.V_W / 3) * 1.2f, padding, ": " + player.rockets);
		drawImgWithText(batch, Assets.player, (Game.V_W / 3) * 2 * .8f, padding, ": " + player.lives);
		//batch.draw(Assets.powerup, Game.V_W / 3, padding, 25, 25);
		//Assets.smallFont.draw(batch, ": " + player.rockets, Game.V_W / 3 + 35, padding + scoreB.height);
		
		//drawManyCentered(batch, Assets.player, 25, padding, 10, player.lives);
		//drawManyCentered(batch, Assets.powerup, 25, Game.V_H - 35, 10, player.rockets);
	}
	
	private void drawImgWithText(SpriteBatch batch, Texture img, float x, float y, String text) {
		float w =  Assets.smallFont.getBounds(text).width + 35 + 25;
		batch.draw(img, x - w / 2, y, 25, 25);
		Assets.smallFont.draw(batch, text, x + 35 - w / 2, y + Assets.smallFont.getBounds(text).height);
	}
}
