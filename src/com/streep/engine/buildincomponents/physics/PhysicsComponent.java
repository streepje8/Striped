package com.streep.engine.buildincomponents.physics;

import com.streep.engine.buildincomponents.colliders.Collider;
import com.streep.engine.core.Component;
import com.streep.engine.core.Game;
import com.streep.engine.util.SMath;
import com.streep.engine.util.Time;

public class PhysicsComponent extends Component {
	
	private static final long serialVersionUID = 1L;

	public float hVelocity = 0f;
	public float vVelocity = 0f;
	public float gravity = 9.81f;
	public Collider collider = null;
	
	@Override
	public void start() {
		if(gameObject.hasComponent(Collider.class)) {
			this.collider = gameObject.getComponent(Collider.class);
		} else {
			System.out.println("[Warning] No collider attached to gameobject " + gameObject.name + " but physicscomponent requires it!");
		}
	}
	
	public void setCollider(Collider c) {
		this.collider = c;
	}

	@Override
	public void update() {
		vVelocity += (float) (((gravity) / 60f) * Time.DeltaTime);
		if(collider != null) {
			for(Collider c : Game.colliderManager.getColliders()) {
				if(c != collider) {
					if (collider.meeting(gameObject.position.x + hVelocity, gameObject.position.y, c)) { 
					    while (!collider.meeting(gameObject.position.x + SMath.sign(hVelocity), gameObject.position.y, c)) {
					    	gameObject.position.x += SMath.sign(hVelocity); 
					    }
					    hVelocity = 0;
					}
				}
			}
			for(Collider c : Game.colliderManager.getColliders()) {
				if(c != collider) {
					if (collider.meeting(gameObject.position.x, gameObject.position.y + vVelocity, c)) { 
					    while (!collider.meeting(gameObject.position.x, gameObject.position.y + SMath.sign(vVelocity), c)) { 
					    	gameObject.position.y += SMath.sign(vVelocity);
					    } 
					    vVelocity = 0;
					}
				}
			}
		}
		gameObject.position.x += hVelocity;
		gameObject.position.y += vVelocity;
	}
	
	public boolean isGrounded() {
		for(Collider c : Game.colliderManager.getColliders()) {
			if(c != collider) {
				if (collider.meeting(gameObject.position.x, gameObject.position.y + 1f, c)) { 
					return true;
				}
			}
		}
		return false;
	}

}
