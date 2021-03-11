package com.streep.engine.elements;

import java.io.Serializable;

public class Mesh implements Serializable {

	private static final long serialVersionUID = 1L;

	private float[] positions; 
	private int[] indices;
	private float[] uvs;
	private float[] normals;
	
	public Mesh(float[] positions, int[] indices) {
		this.positions = positions;
		this.indices = indices;
		this.uvs = new float[0];
		this.normals = new float[0];
	}
	
	public Mesh(float[] positions, int[] indices, float[] Uvs) {
		this.positions = positions;
		this.indices = indices;
		this.uvs = Uvs;
		this.normals = new float[0];
	}
	
	public Mesh(float[] positions, int[] indices, float[] Uvs, float[] Normals) {
		this.positions = positions;
		this.indices = indices;
		this.uvs = Uvs;
		this.normals = Normals;
	}

	public float[] getPositions() {
		return positions;
	}

	public int[] getIndices() {
		return indices;
	}

	public float[] getUvs() {
		return uvs;
	}

	public float[] getNormals() {
		return normals;
	}
	
}
