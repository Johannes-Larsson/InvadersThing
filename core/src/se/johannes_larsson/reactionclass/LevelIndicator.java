package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LevelIndicator extends GameObject {

    private int level;
    private final float SPEED = 1e-4f, BASESPEED = 1;
    private GlyphLayout layout;

    public LevelIndicator(int level) {
        super(new Animation(Assets.bullet, 0, 0, 0, 0, 0, 0), -100, Game.V_H / 2);
        this.level = level;
        layout = new GlyphLayout(Assets.smallFont, "level " + level);
    }

    private float speed(float x) {
        return SPEED * (x - Game.V_W / 2) * (x - Game.V_W / 2) + BASESPEED;
    }

    public void update() {
        if (getX() > Game.V_W + 200) Game.getScene().toRemove.add(this);
        move(speed(getX()), 0);
    }

    public void draw(SpriteBatch batch) {
        Assets.smallFont.setColor(Scenes.game.paused ? Color.GRAY : Color.WHITE);
        Assets.smallFont.draw(batch, "level " + level, getX()- layout.width / 2, getY());
    }

    public void drawShadow(SpriteBatch batch) {
        //dont draw a shadow :^)))
    }
}
