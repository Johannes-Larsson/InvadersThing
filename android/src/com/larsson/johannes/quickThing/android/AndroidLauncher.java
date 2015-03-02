package com.larsson.johannes.quickThing.android;

import android.os.Bundle;
import android.widget.TextView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.larsson.johannes.quickThing.Game;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		try {
			initialize(new Game(), config);
		}
		catch (Exception e) {
			TextView v = new TextView(getContext());
			for (StackTraceElement s : e.getStackTrace()) v.setText(v.getText() + s.toString());
		}
	}
}
