package com.streep.engine.util;

import java.awt.Color;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Vector3 {
	
	public float x;
	public float y;
	public float z;
	
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

	public Vector3 multiply(float val) {
		Vector3 result = Vector3.zero();
		result.setX(this.x * val);
		result.setY(this.y * val);
		result.setZ(this.z * val);
		return result;
	}

	public Vector3 add(Vector3 amount) {
		Vector3 result = Vector3.zero();
		result.setX(this.x + amount.x);
		result.setY(this.y + amount.y);
		result.setZ(this.z + amount.z);
		return result;
	}

	public Vector3 divide(float number) {
		Vector3 result = Vector3.zero();
		result.setX(this.x / number);
		result.setY(this.y / number);
		result.setZ(this.z / number);
		return result;
	}

	public Vector3 multiply(Vector3 b) {
		Vector3 result = Vector3.zero();
		result.setX(this.x * b.getX());
		result.setY(this.y * b.getY());
		result.setZ(this.z * b.getZ());
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

	public Vector3 subtract(Vector3 b) {
		Vector3 result = Vector3.zero();
		result.setX(this.x - b.getX());
		result.setY(this.y - b.getY());
		result.setZ(this.z - b.getZ());
		return result;
	}

	public Vector3f toVector3f() {
		return new Vector3f(this.x, this.y, this.z);
	}

	public static Vector3 one() {
		return new Vector3(1,1,1);
	}
	
	public static Vector3 zero() {
		return new Vector3();
	}

	public Vector3 mulMatrix(Matrix4f matrix) {
		Vector3 result = new Vector3(0,0,0);
		result.x = matrix.m00 * this.x + matrix.m01 * this.y + matrix.m02 * this.z + matrix.m03;
		result.y = matrix.m10 * this.x + matrix.m11 * this.y + matrix.m12 * this.z + matrix.m13;
		result.z = matrix.m20 * this.x + matrix.m21 * this.y + matrix.m22 * this.z + matrix.m23;
		return result;
	}
	
}
