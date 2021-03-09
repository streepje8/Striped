package com.streep.engine.buildincomponents.colliders;

import com.streep.engine.core.Game;
import com.streep.engine.util.Vector2;

public class SquareCollider extends Collider {

	private static final long serialVersionUID = 1L;
	
	public float width = 1f;
	public float height = 1f;
	
	@Override
	public void start() {
		Game.colliderManager.registerCollider(this);
	}

	@Override
	public void update() {}

	public Vector2 getLocation() {
		return new Vector2(gameObject.position.x, gameObject.position.y);
	}
	
	public boolean meeting(float x, float y, SquareCollider other) {
		Vector2 rc2 = other.getLocation();
		return (x < rc2.x + other.width &&
				   x + this.width > rc2.x &&
				   y < rc2.y + other.height &&
				   y + this.height > rc2.y);
	}
	
	public boolean meeting(float x, float y, CircleCollider other) {
		Vector2 otherloc = other.getLocation();
		float testX = otherloc.x;
		float testY = otherloc.y;
		
		if (otherloc.x < x) { testX = x; } else if (otherloc.x > x+this.width) { testX = x+this.width; }
		if (otherloc.y < y) { testY = y; } else if (otherloc.y > y+this.height) { testY = y+this.height; }
		float distX = otherloc.x-testX;
		float distY = otherloc.y-testY;
		float distance = (float) Math.sqrt((distX*distX) + (distY*distY));
		if (distance <= other.radius) {
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
		System.err.println("[Error] Unsupported collision check: Square collider with " + other.getClass().getName());
		return false;
	}
	
}
