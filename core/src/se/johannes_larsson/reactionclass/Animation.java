package se.johannes_larsson.reactionclass;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animation {
	public Sprite sprite;
	
	public int frameW;
	public int frameH;
	public int frames;
	public int animSpeed;
	public int row;
	
	private int frame;
	private int animCounter;
	
	public Animation(Texture texture, float sizeX, float sizeY, int frames, int frameW, int frameH, int animSpeed) {
		this.sprite = new Sprite(texture);
		sprite.setSize(sizeX, sizeY);
		sprite.setRegionWidth(frameW);
		sprite.setRegionHeight(frameH);
		this.frames = frames;
		this.frameW = frameW;
		this.frameH = frameH;
		this.animSpeed = animSpeed;
		row = 0;
		animCounter = 0;
		setFrame(0);
	}
	
	public void setFrame(int frame) {
		if(frame >= frames) {
			frame = 0;
		}
		this.frame = frame;
		sprite.setRegionX(frame * frameW);
		sprite.setRegionWidth(frameW);
	}
	
	public int getFrame() { return frame; }
	
	public boolean isAtEnd() {
		return frame == frames - 1&& animCounter >= animSpeed - animCounter - 1;
	}
	
	public void draw(SpriteBatch batch) {
		if(animSpeed > 0) {
			animCounter++;
			if(animCounter >= animSpeed - 1) {
				animCounter = 0;
				setFrame(frame + 1);
			}
		}
		sprite.draw(batch);
	}
}
