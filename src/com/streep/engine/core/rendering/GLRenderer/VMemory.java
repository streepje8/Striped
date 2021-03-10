package com.streep.engine.core.rendering.GLRenderer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.streep.engine.elements.Material;
import com.streep.engine.elements.Mesh;
import com.streep.engine.elements.Texture;

public class VMemory {

	private static List<Integer> vaos = new ArrayList<Integer>();
	private static List<Integer> vbos = new ArrayList<Integer>();
	public static HashMap<Integer, Texture> textures = new HashMap<Integer, Texture>();
	
	public static void saveTexture(Texture texture) {
		texture.load();
		textures.put(texture.ID, texture);
	}
	
	public static void cleanUp() {
		for(int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for(int key : textures.keySet()) {
			GL15.glDeleteTextures(key);
		}
	}
	
	public static List<Integer> getVaos() {
		return vaos;
	}

	public static List<Integer> getVbos() {
		return vbos;
	}

	public static int createVAO() {
		int vao = GL30.glGenVertexArrays();
		vaos.add(vao);
		return vao;
	}
	
	public static RuntimeMesh storeMesh(Mesh m, Material material) {
		int vao = createVAO();
		GL30.glBindVertexArray(vao);
		for(int key : material.attributes.keySet()) {
			switch(material.attributes.get(key).type) {
				case Posistion:
					storeData(key,3,m.getPositions());
					break;
				case UV:
					storeData(key,2,m.getUvs());
					break;
			}
		}		
		bindIndices(m.getIndices());
		GL30.glBindVertexArray(0);
		return new RuntimeMesh(vao,m.getIndices().length);
	}
	
	public static void storeData(int attribute, int dimensions, float[] data) {
		int vbo = GL15.glGenBuffers(); //Creates a VBO ID
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo); //Loads the current VBO to store the data
		FloatBuffer buffer = BufferManager.createFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attribute, dimensions, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); //Unloads the current VBO when done.
	}
	
	public static void bindIndices(int[] data) {
		int vbo = GL15.glGenBuffers();
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
		IntBuffer buffer = BufferManager.createIntBuffer(data);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
}
