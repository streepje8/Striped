package com.streep.engine.buildincomponents;

import com.streep.engine.core.Component;

public abstract class Collider extends Component {

	private static final long serialVersionUID = 1L;
	
	public abstract boolean meeting(float x, float y, Collider other);
	
}
