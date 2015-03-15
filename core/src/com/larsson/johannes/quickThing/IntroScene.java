package com.larsson.johannes.quickThing;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IntroScene extends Scene {
	
	final String[] messages = new String[] {
			"the earth is being attacked!",
			"you're the only pilot left who can save it!",
			"shoot them to fend them off",
			"but pay attention!\nsome drop rockets you can shoot",
			"be sure to pick them up!",
			"the two lower thirds of the screen\nare split into the following controls: ",
			"go left | shoot | go right",
			"the upper third shoots rockets",
			"good luck!"
	};
	
	int messageIndex;
	int charIndex;
	
	final int progressionSpeed = 3;
	int progressionCounter;
	
	boolean done;
	
	Button skip;
	
	final int delay = 60;
	
	public IntroScene() {
		super.initialize();
		skip = new Button("skip", 65, 25);
	}
	
	public void update () {
		progressionCounter++;
		if (progressionCounter >= progressionSpeed) {
			progressionCounter = 0;
			charIndex++;
			if (charIndex == messages[messageIndex].length()) {
				progressionCounter = -100;
				return;
			}
			else if (charIndex > messages[messageIndex].length()) {
				nextMessage();
			}
		}
		
		if (Input.wasJustPressed()) {
			if (isComplete()) nextMessage();
			else completeMessage();
		}
		
		if (skip.isClicked()) Game.setScene(Scenes.game);
	}
	
	private boolean isComplete() {
		return charIndex == messages[messageIndex].length();
	}
	
	private void completeMessage() {
		charIndex = messages[messageIndex].length();
		progressionCounter = -delay;
	}
	
	private void nextMessage() {
		charIndex = 0;
		messageIndex++;
		progressionCounter = 0;
		if (messageIndex >= messages.length) Game.setScene(Scenes.game);
	}
	
	public void draw(SpriteBatch batch) {
		String s = //charIndex + " / " + messages[messageIndex].length();//
				messages[messageIndex].substring(0, charIndex);
		Assets.smallFont.drawMultiLine(batch, s, Game.V_W / 2 - Assets.smallFont.getMultiLineBounds(s).width / 2, Game.V_H / 2 + Assets.smallFont.getMultiLineBounds(s).height / 2);
		skip.draw(batch);
	}
}
