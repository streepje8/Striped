package com.streep.engine.elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL20;

import com.streep.engine.core.rendering.GLRenderer.MaterialAttribute;
import com.streep.engine.core.rendering.GLRenderer.MaterialAttribute.MaterialAttributeType;
import com.streep.engine.core.rendering.GLRenderer.MaterialProperty;
import com.streep.engine.core.rendering.GLRenderer.MaterialProperty.MaterialPropertyType;
import com.streep.engine.core.rendering.GLRenderer.VMemory;

public class Material implements Serializable {

	private static final long serialVersionUID = 1L;
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	private String vertexFile = "";
	private String fragmentFile = "";
	public HashMap<Integer, MaterialAttribute> attributes = new HashMap<Integer, MaterialAttribute>();
	public List<MaterialProperty> properties = new ArrayList<MaterialProperty>();
	
	public static enum ShaderType {
		vertex,
		fragment
	}
	
	public Material() {
		this.vertexFile = "./Resources/DefaultAssets/defaultVertex.shader";
		this.fragmentFile = "./Resources/DefaultAssets/defaultFragment.shader";
		attributes.put(0, new MaterialAttribute("position",MaterialAttributeType.Posistion));
		attributes.put(1, new MaterialAttribute("uv",MaterialAttributeType.UV));
		attributes.put(2, new MaterialAttribute("normal",MaterialAttributeType.Normal));
		properties.add(new MaterialProperty("matrixData", MaterialPropertyType.MatrixData));
		properties.add(new MaterialProperty("lightData", MaterialPropertyType.LightData));
	}
	
	
	public Material(String vertexFile, String fragmentFile) {
		this.vertexFile = vertexFile;
		this.fragmentFile = fragmentFile;
		attributes.put(0, new MaterialAttribute("position",MaterialAttributeType.Posistion));
		attributes.put(1, new MaterialAttribute("uv",MaterialAttributeType.UV));
		attributes.put(2, new MaterialAttribute("normal",MaterialAttributeType.Normal));
		properties.add(new MaterialProperty("matrixData", MaterialPropertyType.MatrixData));
		properties.add(new MaterialProperty("lightData", MaterialPropertyType.LightData));
	}
	
	public void loadToMemory() {
		this.vertexShaderID = loadShader(this.vertexFile, ShaderType.vertex);
		this.fragmentShaderID = loadShader(this.fragmentFile, ShaderType.fragment);
		this.programID = GL20.glCreateProgram();
		GL20.glAttachShader(this.programID, this.vertexShaderID);
		GL20.glAttachShader(this.programID, this.fragmentShaderID);
		bindAttributes();
		for(MaterialProperty prop : this.properties) {
			switch(prop.type) {
				case sampler1D:
					VMemory.saveTexture((Texture) prop.value);
					break;
				case sampler2D:
					VMemory.saveTexture((Texture) prop.value);
					break;
				default:
					//no init needed
					break;
			}
		}
		GL20.glLinkProgram(this.programID);
		GL20.glValidateProgram(this.programID);
	}
	
	protected void bindAttributes() {
		for(int key : this.attributes.keySet()) {
			this.bindAttribute(key, this.attributes.get(key).name);
		}
	}
	
	public int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(this.programID, uniformName);
	}
	
	public void start() {
		GL20.glUseProgram(this.programID);
	}
	
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	public void deleteFromMemory() {
		this.stop();
		GL20.glDetachShader(this.programID, this.vertexShaderID);
		GL20.glDetachShader(this.programID, this.fragmentShaderID);
		GL20.glDeleteShader(this.vertexShaderID);
		GL20.glDeleteShader(this.fragmentShaderID);
		GL20.glDeleteProgram(this.programID);
	}
	
	protected void bindAttribute(int attribute, String variableName) {
		GL20.glBindAttribLocation(this.programID, attribute, variableName);
	}
	
	private static int loadShader(String file, ShaderType type) {
		String sourcecode = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				sourcecode += line + "\n";
			}
			reader.close();
		} catch(IOException e)
		{
			System.err.println("Could not read shader file!");
			e.printStackTrace();
			System.exit(-1);
		}
		int glType = 0;
		switch(type) {
			case vertex:
				glType = GL20.GL_VERTEX_SHADER;
				break;
			case fragment:
				glType = GL20.GL_FRAGMENT_SHADER;
				break;
		}
		int shaderID = GL20.glCreateShader(glType);
		GL20.glShaderSource(shaderID, sourcecode);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL20.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader!");
			System.exit(-1);
		}
		return shaderID;
	}
}
