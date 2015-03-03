package com.larsson.johannes.quickThing;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IntroScene extends Scene {
	
	final String[] messages = new String[] {
			"the earth is being attacked!",
			"you're the only pilot left who can save it!",
			"the two lower thirds of the screen\nare split into the following controls: ",
			"go left | shoot rocket | go right",
			"the upper third shoots regular bullets",
			"good luck!"
	};
	
	int messageIndex;
	int charIndex;
	
	final int progressionSpeed = 3;
	int progressionCounter;
	
	boolean done;
	
	Button skip;
	
	int doneDelay;
	final int delay = 30;
	
	public IntroScene() {
		super.initialize();
		doneDelay = delay;
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
				charIndex = 0;
				messageIndex++;
				doneDelay = delay;
				if (messageIndex >= messages.length) Game.setScene(Scenes.game);
			}
		}
		
	}
	
	public void draw(SpriteBatch batch) {
		String s = //charIndex + " / " + messages[messageIndex].length();//
				messages[messageIndex].substring(0, charIndex);
		Assets.smallFont.drawMultiLine(batch, s, Game.V_W / 2 - Assets.smallFont.getMultiLineBounds(s).width / 2, Game.V_H / 2 + Assets.smallFont.getMultiLineBounds(s).height / 2);
	}
	
	
}
