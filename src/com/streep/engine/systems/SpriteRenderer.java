package com.streep.engine.systems;

import java.awt.Graphics;

import com.streep.engine.subclasses.Sprite;

public class SpriteRenderer extends Component {

	public int frame = 0;
	public float speed = 1;
	public float xscale = 1;
	public float yscale = 1;
	private int counter = 0;
	public Sprite sprite = null;
	
	
	@Override
	public void start() {
		this.frame = 0;
	}

	@Override
	public void update() {
		if(counter >= (60 * (1-speed))) {
			counter = 0;
			frame++;
			if(frame > (sprite.framecount - 1)) {
				frame = 0;
			}
		} else {
			counter++;
		}
	}
	
	public void renderSprite(int x, int y, Graphics g) {
		g.drawImage(this.sprite.getFrame(frame), x, y, Math.round(this.sprite.getFrame(frame).getWidth() * xscale), Math.round(this.sprite.getFrame(frame).getHeight() * yscale), null);
	}

	
	
}
