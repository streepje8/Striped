package com.streep.engine.util;

import java.awt.Color;

public class Vector3 {
	
	public float x;
	public float y;
	public float z;
	
	public static Vector3 zero() {
		return new Vector3();
	}
	
	public Vector3 Normalize() {
		float magnitude = (float) Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
		Vector3 result = new Vector3();
		if(magnitude > 0) {
			result.setX(this.x / magnitude);
			result.setY(this.y / magnitude);
			result.setZ(this.z / magnitude);
		}
		return result;
	}
	
	public String toString() {
		return "Vector[" + this.x + "," + this.y + "," + this.z + "]";
	}
	
	public Vector3() {
		this.setX(0);
		this.setY(0);
		this.setZ(0);
	}
	public Vector3(float x, float y, float z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	public Vector3(int x, int y, int z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public static Vector3 multiply(Vector3 dir, float dst) {
		Vector3 result = Vector3.zero();
		result.setX(dir.getX() * dst);
		result.setY(dir.getY() * dst);
		result.setZ(dir.getZ() * dst);
		return result;
	}

	public static Vector3 add(Vector3 location, Vector3 multiply) {
		Vector3 result = Vector3.zero();
		result.setX(location.getX() + multiply.getX());
		result.setY(location.getY() + multiply.getY());
		result.setZ(location.getZ() + multiply.getZ());
		return result;
	}

	public static Vector3 divide(Vector3 v, float number) {
		Vector3 result = Vector3.zero();
		result.setX(v.getX() / number);
		result.setY(v.getY() / number);
		result.setZ(v.getZ() / number);
		return result;
	}

	public static Vector3 multiply(Vector3 a, Vector3 b) {
		Vector3 result = Vector3.zero();
		result.setX(a.getX() * b.getX());
		result.setY(a.getY() * b.getY());
		result.setZ(a.getZ() * b.getZ());
		return result;
	}
	
	public static float dotProduct(Vector3 one, Vector3 two) {
		return (one.getX() * two.getX()) + (one.getY() * two.getY()) + (one.getZ() * two.getZ());
	}
	
	public static Color toColor(Vector3 toColor) {
		try {
			return new Color(Math.round(toColor.getX()), Math.round(toColor.getY()), Math.round(toColor.getZ()));
		} catch(Exception e) {
			if(toColor.getX() > 255) {
				toColor.setX(255);
			}
			if(toColor.getY() > 255) {
				toColor.setY(255);
			}
			if(toColor.getZ() > 255) {
				toColor.setZ(255);
			}
			if(toColor.getX() < 0) {
				toColor.setX(0);
			}
			if(toColor.getY() < 0) {
				toColor.setY(0);
			}
			if(toColor.getZ() < 0) {
				toColor.setZ(0);
			}
			return new Color(Math.round(toColor.getX()), Math.round(toColor.getY()), Math.round(toColor.getZ()));
		}
	}

	public static Vector3 subtract(Vector3 lightPos, Vector3 p) {
		Vector3 result = Vector3.zero();
		result.setX(lightPos.getX() - p.getX());
		result.setY(lightPos.getY() - p.getY());
		result.setZ(lightPos.getZ() - p.getZ());
		return result;
	}
	
}
