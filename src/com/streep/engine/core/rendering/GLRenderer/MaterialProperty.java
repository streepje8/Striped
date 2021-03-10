package com.streep.engine.core.rendering.GLRenderer;

import java.io.Serializable;

public class MaterialProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum MaterialPropertyType {
		sampler1D,
		sampler2D,
		Integer,
		Float,
		Vector3,
		Vector2,
		Boolean,
		Matrix
	}
	
	public MaterialPropertyType type;
	public String name;
	public Object value;
	
	public MaterialProperty(String name, MaterialPropertyType type) {
		this.name = name;
		this.type = type;
	}
	
	public MaterialProperty(String name, MaterialPropertyType type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}
}
