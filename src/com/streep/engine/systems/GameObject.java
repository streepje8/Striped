package com.streep.engine.systems;

import java.io.Serializable;
import java.util.ArrayList;

import org.lwjgl.util.vector.Matrix4f;

import com.streep.engine.core.Component;
import com.streep.engine.subclasses.Sprite;
import com.streep.engine.util.SMath;
import com.streep.engine.util.Vector3;

public class GameObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public Vector3 position = Vector3.zero();
	public Vector3 rotation = Vector3.zero();
	public Vector3 scale = Vector3.one();
	public String name = "GameObject(no name set)";
	
	private ArrayList<Component> components = new ArrayList<Component>();
	public Sprite sprite;
	
	public GameObject() {
		this.position = Vector3.zero();
		this.rotation = Vector3.zero();
		this.scale = Vector3.one();
	}
	
	public GameObject(float x, float y, float z) {
		this.position = new Vector3(x,y,z);
		this.rotation = Vector3.zero();
		this.scale = Vector3.one();
	}
	
	public GameObject(Vector3 position) {
		this.position = position;
		this.rotation = Vector3.zero();
		this.scale = Vector3.one();
	}
	
	public Vector3 forward() {
		Vector3 forward = new Vector3(0,0,-1);
		Matrix4f mat = SMath.createTransformationMatrix(new Vector3(0,0,0), this.rotation, new Vector3(1,1,1));
		forward = forward.mulMatrix(mat);
		return forward;
	}
	
	public Vector3 left() {
		Vector3 forward = new Vector3(-1,0,0);
		Matrix4f mat = SMath.createTransformationMatrix(new Vector3(0,0,0), this.rotation, new Vector3(1,1,1));
		forward = forward.mulMatrix(mat);
		return forward;
	}
	
	public Vector3 up() {
		Vector3 forward = new Vector3(0,1,0);
		Matrix4f mat = SMath.createTransformationMatrix(new Vector3(0,0,0), this.rotation, new Vector3(1,1,1));
		forward = forward.mulMatrix(mat);
		return forward;
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
	
	public <T> void removeComponent(Class<T> componenttype) {
		if(this.hasComponent(componenttype)) {
			this.components.remove(this.getComponent(componenttype));
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

	public boolean removeComponent(Component c) {
		if(this.components.contains(c)) {
			c.destroy();
			this.components.remove(c);
			return true;
		}
		return false;
	}
	
	public Vector3 getRotation() {
		return rotation;
	}

	public void setRotation(Vector3 rotation) {
		this.rotation = rotation;
	}

	public Vector3 getScale() {
		return scale;
	}

	public void setScale(Vector3 scale) {
		this.scale = scale;
	}

}
