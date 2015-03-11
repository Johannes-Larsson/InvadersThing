package com.larsson.johannes.quickThing;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScene extends Scene {
	
	static final int MAX_COMBO = 200; 
	public static final float PARALAX = -.05f;
	
	public Player player;
	public int score;
	
	private int comboLevel;
	private int comboCounter;	
	private int spawnCounter;
	private int cloudCounter;
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
	
	public void onEnemyKill() {
		comboLevel++;
		comboCounter = MAX_COMBO;
	}

	public void update() {
		spawnCounter -= 1;
		
		if (comboCounter > 0) {
			comboCounter--;
			if (comboCounter == 0) {
				comboLevel = 0;
			}
		}
		
		
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
		
		final int clouds = 12, rate = 60;
		if (cloudCounter <= (rate + 1) * clouds) cloudCounter++;
		if (cloudCounter % rate == 0) toAdd.add(new Cloud(clouds * rate));
		
		super.update();
	}
	
	private int enemiesAlive() {
		int i = 0;
		for (GameObject e : objects) if (e.getType() == "enemy") i++;
		return i;
	}
	
	public void draw(SpriteBatch batch) {
		for (GameObject g : objects) if (g.getType() == "cloud") g.drawShadow(batch);
		for (GameObject g : objects) if (g.getType() == "cloud") g.draw(batch);
		for (GameObject g : objects) if (g.getType() != "cloud") g.drawShadow(batch);
		for (GameObject g : objects) if (g.getType() != "cloud") g.draw(batch);
		drawGUI(batch);
		//super.draw(batch);
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
		
		final float height = 55;
		drawImgWithText(batch, Assets.powerup, Game.V_W - padding - 100, height, ": " + player.rockets);
		drawImgWithText(batch, Assets.player, padding + 100, height, ": " + player.lives);
		
		if (comboLevel > 0) {
			final float MAX_W = 600;
			String s = "combo: " + comboLevel + "x";
			TextBounds b = Assets.smallFont.getBounds(s);
			final float extraPadding = 10;
			final float H = b.height + extraPadding;
			final float W = (MAX_W * (comboCounter / (float)MAX_COMBO)) / 2;
			final float y = padding - extraPadding / 2;
			final float x = Game.V_W / 2 - W / 2;
			batch.draw(Assets.bullet, x, y, W, H);
			
			Assets.smallFont.setColor(Color.BLUE);
			Assets.smallFont.draw(batch, s, Game.V_W / 2 - Assets.smallFont.getBounds(s).width / 2, padding + Assets.smallFont.getBounds(s).height);
			Assets.smallFont.setColor(Color.WHITE);
		}
	}
	
	private void drawImgWithText(SpriteBatch batch, Texture img, float x, float y, String text) {
		float w =  Assets.smallFont.getBounds(text).width + 35 + 25;
		batch.draw(img, x - w / 2, y, 25, 25);
		Assets.smallFont.draw(batch, text, x + 35 - w / 2, y + Assets.smallFont.getBounds(text).height);
	}
}
