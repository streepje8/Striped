package com.streep.engine.buildincomponents;

import com.streep.engine.core.Component;
import com.streep.engine.util.Time;

public class PlatformerController extends Component {

	private static final long serialVersionUID = 1L;
	
	public float speed = 2f;
	public float jumpStrength = 6f;
	private PhysicsComponent physicsComponent = null;
	
	@Override
	public void start() {
		if(gameObject.hasComponent(PhysicsComponent.class)) {
			physicsComponent = (PhysicsComponent) gameObject.getComponent(PhysicsComponent.class);
		} else {
			System.out.println("[Warning] No physics component attached to " + gameObject.name + " but required by " + getClass().getName());
		}
	}

	@Override
	public void update() {
		if(Input.getKeyDown('w')) {
			if(physicsComponent.isGrounded()) {
				physicsComponent.vVelocity = (float) (-(jumpStrength) * Time.DeltaTime);
			}
		}
		if(Input.getKeyDown('a')) {
			physicsComponent.hVelocity = (float) (-speed * Time.DeltaTime);
		} else {
			if(Input.getKeyDown('d')) {
				physicsComponent.hVelocity = (float) (speed * Time.DeltaTime);
			} else {
				physicsComponent.hVelocity = 0f;
			}
		}
	}
}
