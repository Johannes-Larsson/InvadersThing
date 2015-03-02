package com.larsson.johannes.quickThing;

import com.badlogic.gdx.Gdx;
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
	tripleShooter;
	
	public static void loadAll() {
		FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("C64_Pro_Mono_v1.0-STYLE.ttf"));
		smallFont = g.generateFont(30);
		
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
	}
}
