package com.streep.engine.elements;

import java.io.Serializable;

public class Mesh implements Serializable {

	private static final long serialVersionUID = 1L;

	private float[] positions; 
	private int[] indices;
	
	public Mesh(float[] positions, int[] indices) {
		this.positions = positions;
		this.indices = indices;
	}

	public float[] getPositions() {
		return positions;
	}

	public int[] getIndices() {
		return indices;
	}
	
}
