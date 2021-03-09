package com.streep.engine.systems;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private String name;
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Level(String name) {
		this.name = name;
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
