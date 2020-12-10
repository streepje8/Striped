package com.streep.engine.systems;

import java.io.Serializable;

import com.streep.engine.core.Game;
import com.streep.engine.handlers.Input;

public abstract class Component implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public GameObject gameObject;
	
	public abstract void start();
	public abstract void update();
	
	public Input Input = Game.Input;
	
	public GameObject getGameObject() {
		return gameObject;
	}
	public void setGameObject(GameObject newGameObject) {
		this.gameObject = newGameObject;
	}
	
	public void destroy() {}
}
