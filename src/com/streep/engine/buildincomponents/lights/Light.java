package com.streep.engine.buildincomponents.lights;

import java.awt.Color;

import com.streep.engine.core.Component;

public abstract class Light extends Component {

	private static final long serialVersionUID = 1L;
	
	public float intensity = 1.0f;
	public Color color = new Color(255,255,255);
	
	
	public float getIntensity() {
		return intensity;
	}
	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

}
