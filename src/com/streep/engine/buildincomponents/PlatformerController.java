package com.streep.engine.buildincomponents;

import com.streep.engine.systems.Component;
import com.streep.engine.systems.Time;

public class PlatformerController extends Component {

	public float speed = 2f;
	public float jumpStrength = 6f;
	public PhysicsComponent physicsComponent = null;
	
	@Override
	public void start() {
		if(gameObject.hasComponent(PhysicsComponent.class)) {
			physicsComponent = (PhysicsComponent) gameObject.getComponent(PhysicsComponent.class);
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
