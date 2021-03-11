package com.streep.engine.core.rendering.GLRenderer;

public class MaterialAttribute {

	public String name = "";
	public static enum MaterialAttributeType {
		Posistion,
		UV,
		Normal
	}
	public MaterialAttributeType type;
	
	public MaterialAttribute(String name, MaterialAttributeType type) {
		this.name = name;
		this.type = type;
	}
	
}
