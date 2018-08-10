package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Game extends ApplicationAdapter {
	public static final int V_W = 1300, V_H = 700;
	public static Preferences preferences;
	
	private static Scene scene;
	
	public static void setScene(Scene s) {
		scene = s;
		s.onResume();
	}
	
	public static Scene getScene() { return scene; }
	
	public static int screenShake;
	
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
		
		final float shake = .3f;
		if (screenShake > 0) {
			screenShake--;
			camera.position.set(new Vector2(MathUtils.random(-shake * screenShake, shake * screenShake) + V_W / 2, MathUtils.random(-shake * screenShake, shake * screenShake) + V_H / 2), 0);
		}
		else camera.position.set(new Vector3(V_W / 2, V_H / 2, 0));
		camera.update();
	}

	public void render () {
		update();
		
		if (Scenes.game != null && Scenes.game.getSecondaryColor() != null) {
			Color c =  Scenes.game.getSecondaryColor();
			Gdx.gl.glClearColor(c.r, c.g, c.b, 1);
		} else {
			Gdx.gl.glClearColor(.4f, .5f, 1f, 1);
		}
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		scene.draw(batch);
		
		batch.end();
	}


}
