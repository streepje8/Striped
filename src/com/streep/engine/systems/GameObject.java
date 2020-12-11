package com.streep.engine.systems;

import java.io.Serializable;
import java.util.ArrayList;

import com.streep.engine.core.Component;
import com.streep.engine.subclasses.Sprite;

public class GameObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public int x;
	public int y;
	public String name = "GameObject(no name set)";
	
	private ArrayList<Component> components = new ArrayList<Component>();
	public Sprite sprite;
	
	public GameObject() {
		this.x = 0;
		this.y = 0;
	}
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void Destory() {
		for(Component c : components) {
			c.destroy();
		}
		components = new ArrayList<Component>();
	}
	
	
	public boolean addComponent(Component c) {
		if(this.hasComponent(c.getClass())) {
			return false;
		} else {
			this.components.add(c);
			c.setGameObject(this);
			return true;
		}
	}
	
	public <T> T getComponent(Class<T> componenttype) {
		if(this.hasComponent(componenttype)) {
			for(Component c : this.components) {
				if(componenttype.isInstance(c)) {
					return componenttype.cast(c);
				}
			}
		}
		return null;
	}
	
	public <T> boolean hasComponent(Class<T> componenttype) {
		for(Component c : this.components) {
			if(componenttype.isInstance(c)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Component> getComponents() {
		return this.components;
	}
}
