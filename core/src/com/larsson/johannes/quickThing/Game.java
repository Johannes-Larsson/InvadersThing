package com.larsson.johannes.quickThing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	public static final int V_W = 1300, V_H = 700;
	public static Preferences preferences;
	
	private static Scene scene;
	
	public static void setScene(Scene s) {
		scene = s;
		s.onResume();
	}
	
	public static Scene getScene() { return scene; }
	
	SpriteBatch batch;
	OrthographicCamera camera;
	
	public void create () {
		Assets.loadAll();
		preferences = Gdx.app.getPreferences("JLShmup");
		preferences.clear();

		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, V_W, V_H);
		Input.Initialize();
		Scenes.initAll();
		scene = Scenes.menu;
	}
	
	public void update() {
		Input.update();
		scene.update();
	}

	public void render () {
		update();
		
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		scene.draw(batch);
		
		batch.end();
	}
}
