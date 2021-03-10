package com.streep.engine.util;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class SMath {

	public static int sign(float f) {
		if(f < 0) {
			return -1;
		} else {
			if(f > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	public static Matrix4f createTransformationMatrix(Vector3 translation, Vector3 rotation, Vector3 scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation.toVector3f(), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.y), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.z), new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.scale(scale.toVector3f(), matrix, matrix);
		return matrix;
	}
	
}
