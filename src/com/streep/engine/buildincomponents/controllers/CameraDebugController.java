package com.streep.engine.buildincomponents.controllers;

import org.lwjgl.glfw.GLFW;

import com.streep.engine.core.Component;

public class CameraDebugController extends Component {

	private static final long serialVersionUID = 1L;
	public float speed = 0.02f;
	public float rotationSpeed = 1;
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if(Input.getKey(GLFW.GLFW_KEY_W)) {
			this.gameObject.position = this.gameObject.position.add(this.gameObject.forward().multiply(speed));
		}
		if(Input.getKey(GLFW.GLFW_KEY_A)) {
			this.gameObject.position = this.gameObject.position.add(this.gameObject.left().multiply(speed));
		}
		if(Input.getKey(GLFW.GLFW_KEY_S)) {
			this.gameObject.position = this.gameObject.position.add(this.gameObject.forward().multiply(-speed));
		}
		if(Input.getKey(GLFW.GLFW_KEY_D)) {
			this.gameObject.position = this.gameObject.position.add(this.gameObject.left().multiply(-speed));
		}
		if(Input.getKey(GLFW.GLFW_KEY_SPACE)) {
			this.gameObject.position = this.gameObject.position.add(this.gameObject.up().multiply(speed));
		}
		if(Input.getKey(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			this.gameObject.position = this.gameObject.position.add(this.gameObject.up().multiply(-speed));
		}
		
		if(Input.getKey(GLFW.GLFW_KEY_UP)) {
			this.gameObject.rotation.x -= rotationSpeed;
		}
		if(Input.getKey(GLFW.GLFW_KEY_LEFT)) {
			this.gameObject.rotation.y -= rotationSpeed;
		}
		if(Input.getKey(GLFW.GLFW_KEY_DOWN)) {
			this.gameObject.rotation.x += rotationSpeed;
		}
		if(Input.getKey(GLFW.GLFW_KEY_RIGHT)) {
			this.gameObject.rotation.y += rotationSpeed;
		}
	}

}
