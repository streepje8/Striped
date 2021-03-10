package com.streep.engine.core;

import java.io.Serializable;

import com.streep.engine.handlers.Input;
import com.streep.engine.systems.GameObject;

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
	
	public void onGameEnd() {}
	
	public void destroy() {}
}
