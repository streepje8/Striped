package com.streep.engine.buildincomponents.renderers;

import com.streep.engine.subclasses.Sprite;

public class SpriteRenderer {

	private static final long serialVersionUID = 1L;
	
	public int frame = 0;
	public float speed = 1;
	public float xscale = 1;
	public float yscale = 1;
	private int counter = 0;
	public Sprite sprite = null;
	
	/*
	@Override
	public void start() {
		register();
		this.frame = 0;
	}
	
	@Override
	public void destroy() {
		deRegister();
	}

	@Override
	public void update() {
		if(counter >= (60 * (1/(60*this.speed)))) {
			counter = 0;
			frame++;
			if(frame > (sprite.framecount - 1)) {
				frame = 0;
			}
		} else {
			counter++;
		}
	}

	@Override
	public void onRender(BufferedImage buffer, Graphics g) {
		g.drawImage(this.sprite.getFrame(frame), Math.round(gameObject.x), Math.round(gameObject.y), Math.round(this.sprite.getFrame(frame).getWidth() * xscale), Math.round(this.sprite.getFrame(frame).getHeight() * yscale), null);
	}
	*/
	
	
}
