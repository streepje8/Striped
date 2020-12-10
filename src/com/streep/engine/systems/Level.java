package com.streep.engine.systems;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import com.streep.engine.subclasses.Resource;

public class Level implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private String name;
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public int width = 800;
	public int height = 400;
	public BufferedImage background;
	
	public void setBackground(Resource backdrop) {
		background = backdrop.getImage();
	}
	
	public Level(String name) {
		this.name = name;
	}
	
	public Level(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}
	
	public void addObject(GameObject o) {
		this.objects.add(o);
	}
	
	public void destroy(GameObject o) {
		this.objects.remove(o);
	}
	
	public String toString() {
		return "[Level] " + this.name;
	}

}
