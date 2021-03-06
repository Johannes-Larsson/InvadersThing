package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {
	
	public static BitmapFont font, smallFont;
	
	public static Texture 
	player,
	bullet,
	avoidingEnemy,
	shootingEnemy,
	dumbEnemy,
	explosion,
	powerup,
	smarterShooter,
	tankyEnemy,
	tripleShooter,
	sinker,
	cloud,
	pixel;

	public static Sound
	blip,
	explosionSound,
	hit,
	pickup,
	rocket,
	shoot;
	
	public static void loadAll() {
		FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("C64_Pro_Mono-STYLE.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
		p.size = 30;
		smallFont = g.generateFont(p);
		
		player = new Texture("player.png");
		bullet = new Texture("bullet.png");
		avoidingEnemy = new Texture("avoidingEnemy.png");
		shootingEnemy = new Texture("shootingEnemy.png");
		dumbEnemy = new Texture("dumbEnemy.png");
		explosion = new Texture("explosion.png");
		powerup = new Texture("powerup.png");
		smarterShooter = new Texture("smarterShooter.png");
		tankyEnemy = new Texture("tankyEnemy.png");
		tripleShooter = new Texture("tripleShooter.png");
		sinker = new Texture("sinker.png");
		cloud = new Texture("cloud.png");
		pixel = new Texture("pixel.png");

		blip = Gdx.audio.newSound(Gdx.files.internal("blip.wav"));
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
		hit = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
		pickup = Gdx.audio.newSound(Gdx.files.internal("pickup.wav"));
		rocket = Gdx.audio.newSound(Gdx.files.internal("rocket.wav"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));
	}
}
