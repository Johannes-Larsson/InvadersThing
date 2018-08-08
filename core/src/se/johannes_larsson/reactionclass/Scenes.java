package se.johannes_larsson.reactionclass;

public class Scenes {
	public static GameScene game;
	public static MenuScene menu;
	public static GameOverScene gameOver;
	public static IntroScene intro;
	public static SettingsScene settings;
	
	public static void initAll() {
		game = new GameScene();
		menu = new MenuScene();
		intro = new IntroScene();
		gameOver = new GameOverScene();
		settings = new SettingsScene();
	}
}
