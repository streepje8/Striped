package com.streep.engine.elements;

import java.io.Serializable;

public class Mesh implements Serializable {

	private static final long serialVersionUID = 1L;

	private float[] positions; 
	private int[] indices;
	private float[] Uvs;
	
	public Mesh(float[] positions, int[] indices) {
		this.positions = positions;
		this.indices = indices;
		this.Uvs = new float[] {
			    0,0,
			    0,1,
			    1,1,
			    1,0
		};
	}
	
	public Mesh(float[] positions, int[] indices, float[] Uvs) {
		this.positions = positions;
		this.indices = indices;
		this.Uvs = Uvs;
	}

	public float[] getPositions() {
		return positions;
	}

	public int[] getIndices() {
		return indices;
	}

	public float[] getUvs() {
		return Uvs;
	}
	
}
