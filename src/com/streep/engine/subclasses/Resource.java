package com.streep.engine.subclasses;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {

	private String filename;
	
	public Resource(String filename) {
		this.filename = filename;
	}
	
	
	public BufferedImage getImage() {
		try {
			return ImageIO.read(new File(".\\Resources\\" + this.filename));
		} catch (IOException e) {
			System.err.println("[Engine] Image not found!");
			return null;
		}
	}

}
