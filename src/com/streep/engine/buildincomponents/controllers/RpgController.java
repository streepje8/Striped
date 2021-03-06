package com.streep.engine.buildincomponents.controllers;

import com.streep.engine.buildincomponents.physics.PhysicsComponent;
import com.streep.engine.core.Component;
import com.streep.engine.util.Time;

public class RpgController extends Component {

	private static final long serialVersionUID = 1L;
	
	public float speed = 2f;
	private PhysicsComponent physicsComponent = null;
	
	@Override
	public void start() {
		if(gameObject.hasComponent(PhysicsComponent.class)) {
			setPhysicsComponent(gameObject.getComponent(PhysicsComponent.class));
			physicsComponent.gravity = 0f;
		} else {
			System.out.println("[Warning] No physics component attached to " + gameObject.name + " but required by " + getClass().getName());
		}
	}

	@Override
	public void update() {
		if(Input.getKey('w')) {
			physicsComponent.vVelocity = (float) (-speed * Time.DeltaTime);
		} else {
			if(Input.getKey('s')) {
				physicsComponent.vVelocity = (float) (speed * Time.DeltaTime);
			} else {
				physicsComponent.vVelocity = 0f;
			}
		}
		if(Input.getKey('d')) {
			physicsComponent.hVelocity = (float) (speed * Time.DeltaTime);
		} else {
			if(Input.getKey('a')) {
				physicsComponent.hVelocity = (float) (-speed * Time.DeltaTime);
			 } else {
				 physicsComponent.hVelocity = 0f;
			 }
		}
	}

	public void setPhysicsComponent(PhysicsComponent physicsComponent) {
		this.physicsComponent = physicsComponent;
	}
}
