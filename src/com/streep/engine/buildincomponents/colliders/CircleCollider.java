package com.streep.engine.buildincomponents.colliders;

import com.streep.engine.core.Game;
import com.streep.engine.util.Vector2;

public class CircleCollider extends Collider {

	private static final long serialVersionUID = 1L;
	
	public float radius = 1f;
	
	@Override
	public void start() {
		Game.colliderManager.registerCollider(this);
	}

	@Override
	public void update() {}
	
	public Vector2 getLocation() {
		return new Vector2(gameObject.x, gameObject.y);
	}
	
	public boolean meeting(float x, float y, CircleCollider other) {
		if(new Vector2(x,y).distance(other.getLocation()) < (this.radius + other.radius)) {
			return true;
		}
		return false;
	}
	
	public boolean meeting(float x, float y, SquareCollider other) {
		float testX = x;
		float testY = y;
		Vector2 otherloc = other.getLocation();
		if (x < otherloc.x) { testX = otherloc.x; } else if (x > otherloc.x+other.width) { testX = otherloc.x+other.width; }
		if (y < otherloc.y) { testY = otherloc.y; } else if (y > otherloc.y+other.height) { testY = otherloc.y+other.height; }
		float distX = x-testX;
		float distY = y-testY;
		float distance = (float) Math.sqrt((distX*distX) + (distY*distY));
		if (distance <= radius) {
		  return true;
		}
		return false;
	}
	
	@Override
	public void destroy() {
		Game.colliderManager.unRegisterCollider(this);
	}

	@Override
	public boolean meeting(float x, float y, Collider other) {
		if(other instanceof CircleCollider) {
			return meeting(x,y,(CircleCollider) other);
		}
		if(other instanceof SquareCollider) {
			return meeting(x,y,(SquareCollider) other);
		}
		System.err.println("[Error] Unsupported collision check: Cicle collider with " + other.getClass().getName());
		return false;
	}

}
