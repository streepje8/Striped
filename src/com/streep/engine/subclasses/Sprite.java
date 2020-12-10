package com.streep.engine.subclasses;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {

	private ArrayList<BufferedImage> images;
	public int framecount = 0;
	
	public Sprite(Resource r) {
		BufferedImage img = r.getImage();
		this.images = new ArrayList<BufferedImage>();
		this.images.add(img);
		this.framecount = this.images.size();
	}
	
	public Sprite(Resource r, int framewidth, int frameheight, int rows, int framesperrow,int xoffset,int yoffset,int startx,int starty) {
		BufferedImage img = r.getImage();
		int x = startx;
		int y = starty;
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		for(int row = 0; row < rows; row++) {
				for(int fr = 0; fr < framesperrow; fr++) {
					images.add(img.getSubimage(x, y, framewidth, frameheight));
					x += framewidth;
					x += xoffset;
				}
				y += frameheight;
				y += yoffset;
		}
		this.images = images;
		this.framecount = images.size();
	}
	
	public BufferedImage getFrame(int index) {
		if(index > (images.size() - 1)) {
			System.err.println("[Engine Error] Sprite Frame index out of bounds!");
			System.exit(0);
		}
		return images.get(index);
	}
	
}
