package com.streep.engine.core.rendering.GLRenderer;

public class RuntimeMesh {

	private int vao;
	private int vertices;
	
	public RuntimeMesh(int vao, int vertexCount) {
		this.vao = vao;
		this.vertices = vertexCount;
	}

	public int getVao() {
		return vao;
	}

	public int getVertices() {
		return vertices;
	}

}
