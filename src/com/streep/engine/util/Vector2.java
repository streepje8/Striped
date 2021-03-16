package com.streep.engine.util;

import org.lwjgl.util.vector.Vector2f;

public class Vector2 {

	public float x = 0;
	public float y = 0;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "[" + this.x + "," + this.y + "]";
	}
	
	public Vector2 normalize() {
		return new Vector2(this.x / magnitude(), this.y / magnitude());
	}
	
	public float magnitude() {
		return (float) Math.sqrt(this.x + this.y);
	}
	
	public float distance(Vector2 other) {
		return (float) Math.sqrt(Math.pow(other.x - this.x,2) + Math.pow(other.y - this.y,2));
	}

	public Vector2f toVector2f() {
		return new Vector2f(this.x, this.y);
	}
	
}
