package se.johannes_larsson.reactionclass;

import java.util.ArrayList;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScene extends Scene {
	
	static final int COMBO_DECAY_PAUSE = 50;
	public static final float PARALAX = -.05f;
	
	public Player player;
	public int score;
	
	private float combo;
	private int comboDecayPause;
	private int comboDecayAcceleration;
	private int spawnCounter;
	private int cloudCounter;
	private int level;
	private ArrayList<Enemy> wave;
	private GlyphLayout layout;

	private Button resume;
	private Button menu;
	private Button quit;
	
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

		layout = new GlyphLayout();


		final float distFromCenter = 30;
		resume = new Button("resume", Game.V_W / 2, Game.V_H / 2 + distFromCenter);
		menu = new Button("menu", Game.V_W / 2, Game.V_H / 2 - distFromCenter);
		quit = new Button("quit", Game.V_W / 2, Game.V_H /2 - distFromCenter * 3);
	}

	public void onResume() {
		Gdx.input.setCatchBackKey(true);
	}
	
	public void loadGame() {
		level = load("level", -1);
		player.lives = load("hp", 10);
		score = load("score", 0);
		player.rockets = load("rockets", 0);
		combo = 0;
		comboDecayPause = 0;
		comboDecayAcceleration = 0;
		cloudCounter = 0;
		for (GameObject g : objects) if (g.getType() == "cloud") toRemove.add(g);
	}

	public  static void clearGame() {

		Game.preferences.remove("level");
		Game.preferences.remove("hp");
		Game.preferences.remove("rockets");
		Game.preferences.remove("score");
		Game.preferences.flush();
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

	public boolean paused;

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void onEnemyKill() {
		combo += 1;
		comboDecayPause += COMBO_DECAY_PAUSE * (comboDecayPause > 0 ? .5f : 1);
	}

	public void update() {
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			paused = true;
			System.out.println("PAUSED");
		}

		if (resume.isClicked()) {
			paused = false;
			return;
		}

		if (menu.isClicked()) {
			Game.setScene(Scenes.menu);
		}

		if (quit.isClicked()) {
			Game.preferences.flush();
			Gdx.app.exit();
		}

		if (paused) {
			System.out.println("PAUSED");
			return;
		}

		spawnCounter -= 1;
		
		if (comboDecayPause > 0) {
			comboDecayPause--;
			comboDecayAcceleration = 0;
		}
		else {
			combo *= (enemiesAlive() > 1 ? .995f : .9999f) - comboDecayAcceleration / 1000000f;
			comboDecayAcceleration++;
			comboDecayAcceleration *= .99f;
			combo -= .005f;
			if (combo < 0) {
				combo = 0;
				comboDecayAcceleration = 0;
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
				toAdd.add(new LevelIndicator(getLevel()));
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
	
	public float getCombo() {
		return combo;
	}
	
	private void drawGUI(SpriteBatch batch) {
		final int padding = 20;

		Assets.smallFont.setColor(Color.WHITE);

		if (paused) {
			menu.draw(batch);
			resume.draw(batch);
			quit.draw(batch);
			Assets.smallFont.setColor(Color.GRAY);
		}
		
		String scoreS = "score: " + score;
		String levelS = "level: " + getLevel();

		layout.setText(Assets.smallFont, scoreS);
		Assets.smallFont.draw(batch, scoreS, padding, padding + layout.height);

		layout.setText(Assets.smallFont, levelS);
		Assets.smallFont.draw(batch, levelS, Game.V_W - (padding + layout.width), padding + layout.height);

		System.out.println(player == null);
		
		final float height = 55;
		drawImgWithText(batch, Assets.powerup, Game.V_W - padding - 100, height, ": " + player.rockets);
		drawImgWithText(batch, Assets.player, padding + 100, height, ": " + player.lives);
		
		String comboS = String.valueOf(combo);
		String s = "combo: " + 
		comboS.substring(0, 3 + (int)(combo / 10)) + "x";

		layout.setText(Assets.smallFont, s);
		Assets.smallFont.draw(batch, s, Game.V_W / 2 - layout.width / 2, padding + layout.height);
	}
	
	private void drawImgWithText(SpriteBatch batch, Texture img, float x, float y, String text) {
		layout.setText(Assets.smallFont, text);
		float w =  layout.width + 35 + 25;
		batch.setColor(paused ? Color.GRAY : Color.WHITE);
		batch.draw(img, x - w / 2, y, 25, 25);
		Assets.smallFont.draw(batch, text, x + 35 - w / 2, y + layout.height);
	}
}
