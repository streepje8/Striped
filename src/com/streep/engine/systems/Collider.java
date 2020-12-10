package com.streep.engine.systems;

public abstract class Collider extends Component {

	public abstract boolean meeting(float x, float y, Collider other);
	
}
