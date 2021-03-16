package com.streep.engine.util;

import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjglx.BufferUtils;

import com.streep.engine.GUI.GlWindow;
import com.streep.engine.buildincomponents.lights.DirectionalLight;
import com.streep.engine.buildincomponents.lights.Light;
import com.streep.engine.buildincomponents.lights.PointLight;
import com.streep.engine.buildincomponents.renderers.Camera;
import com.streep.engine.buildincomponents.renderers.gui.GuiShaderManager;
import com.streep.engine.buildincomponents.renderers.gui.fontRendering.FontShader;
import com.streep.engine.core.rendering.GLRenderer.MaterialProperty;
import com.streep.engine.elements.Material;
import com.streep.engine.elements.Texture;
import com.streep.engine.systems.GameObject;

public class ShaderUtils {

	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public static void loadProperties(Camera c, Material mat, List<Light> lightList, GameObject forObject, GlWindow Window) {
		int textureINT = 0;
		for(MaterialProperty prop : mat.properties) {
			switch(prop.type) {
				case Float:
					GL20.glUniform1f(mat.getUniformLocation(prop.name), (float) prop.value);
					break;
				case Integer:
					GL20.glUniform1i(mat.getUniformLocation(prop.name), (int) prop.value);
					break;
				case sampler1D:
					GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureINT);
					GL11.glBindTexture(GL11.GL_TEXTURE_1D, ((Texture) prop.value).ID);
					textureINT++;
					break;
				case sampler2D:
					GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureINT);
					GL11.glBindTexture(GL11.GL_TEXTURE_2D,((Texture) prop.value).ID);
					textureINT++;
					break;
				case Vector2:
					GL20.glUniform2f(mat.getUniformLocation(prop.name),((Vector2) prop.value).x,((Vector2) prop.value).y);
					break;
				case Vector3:
					GL20.glUniform3f(mat.getUniformLocation(prop.name),((Vector3) prop.value).x,((Vector3) prop.value).y,((Vector3) prop.value).z);
					break;
				case Boolean:
					GL20.glUniform1i(mat.getUniformLocation(prop.name), (int) prop.value);
					break;
				case Matrix:
					((Matrix4f) prop.value).store(matrixBuffer);
					matrixBuffer.flip();
					GL20.glUniformMatrix4fv(mat.getUniformLocation(prop.name), false, matrixBuffer);
					matrixBuffer = BufferUtils.createFloatBuffer(16);
					break;
				case LightData:
					/*
						uniform int pointLightCount;
						uniform int directionalLightCount;
						
						uniform vec3 pointLightPositions[128];
						uniform vec3 pointLightColors[128];
						uniform float pointLightIntensities[128];
						uniform vec3 directionalLightDirections[128];
						uniform vec3 directionalLightColors[128];
						uniform float directionalLightIntensities[128];
					*/
					int pointLightPosIndex = 0;
					int pointLightColIndex = 0;
					int pointLightIntensIndex = 0;
					int directionalLightDirIndex = 0;
					int directionalLightColIndex = 0;
					int directionalLightIntensIndex = 0;
					int pointLightCount = 0;
					int directionalLightCount = 0;
					for(Light light : lightList) {
						if(light instanceof PointLight) {
							pointLightCount++;
							Vector3 position = light.gameObject.position;
							GL20.glUniform3f(mat.getUniformLocation("pointLightPositions[" + pointLightPosIndex + "]"),
									position.x, position.y, position.z);
							pointLightPosIndex++;
							GL20.glUniform3f(mat.getUniformLocation("pointLightColors[" + pointLightColIndex + "]"),
									light.color.getRed() / 255f,light.color.getGreen() / 255f,light.color.getBlue() / 255f);
							pointLightColIndex++;
							GL20.glUniform1f(mat.getUniformLocation("pointLightIntensities[" + pointLightIntensIndex + "]"),
									light.intensity);
							pointLightIntensIndex++;
						}
						if(light instanceof DirectionalLight) {
							directionalLightCount++;
							Vector3 forward = light.gameObject.forward();
							GL20.glUniform3f(mat.getUniformLocation("directionalLightDirections[" + directionalLightDirIndex + "]"),
									forward.x,forward.y,forward.z);
							directionalLightDirIndex++;
							Vector3 color = Vector3.fromColor(light.color);
							GL20.glUniform3f(mat.getUniformLocation("directionalLightColors[" + directionalLightColIndex + "]"),
									color.x,color.y,color.z);
							directionalLightColIndex++;
							GL20.glUniform1f(mat.getUniformLocation("directionalLightIntensities[" + directionalLightIntensIndex + "]"),
									light.intensity);
							directionalLightIntensIndex++;
						}
					}
					GL20.glUniform1i(mat.getUniformLocation("pointLightCount"), pointLightCount);
					GL20.glUniform1i(mat.getUniformLocation("directionalLightCount"), directionalLightCount);
					break;
				case MatrixData:
					//Generate matrix data
					Matrix4f transform = SMath.createTransformationMatrix(forObject.position,forObject.rotation, forObject.scale);
					Matrix4f projection = c.getProjectionMatrix(Window.getSize());
					Matrix4f view = c.getViewMatrix();
					
					//Give it to the shader
					transform.store(matrixBuffer);
					matrixBuffer.flip();
					GL20.glUniformMatrix4fv(mat.getUniformLocation("transformationMatrix"), false, matrixBuffer);
					matrixBuffer = BufferUtils.createFloatBuffer(16);
					projection.store(matrixBuffer);
					matrixBuffer.flip();
					GL20.glUniformMatrix4fv(mat.getUniformLocation("projectionMatrix"), false, matrixBuffer);
					matrixBuffer = BufferUtils.createFloatBuffer(16);
					view.store(matrixBuffer);
					matrixBuffer.flip();
					GL20.glUniformMatrix4fv(mat.getUniformLocation("viewMatrix"), false, matrixBuffer);
					matrixBuffer = BufferUtils.createFloatBuffer(16);
					break;
			}
		}
	}

	public static void loadPropertiesGui(GuiShaderManager gsm, Matrix4f transform, List<Light> lightList) {
		int textureINT = 1;
		for(MaterialProperty prop : gsm.properties) {
			switch(prop.type) {
				case Float:
					GL20.glUniform1f(gsm.getUniformLocation(prop.name), (float) prop.value);
					break;
				case Integer:
					GL20.glUniform1i(gsm.getUniformLocation(prop.name), (int) prop.value);
					break;
				case sampler1D:
					GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureINT);
					GL11.glBindTexture(GL11.GL_TEXTURE_1D, ((Texture) prop.value).ID);
					textureINT++;
					break;
				case sampler2D:
					GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureINT);
					GL11.glBindTexture(GL11.GL_TEXTURE_2D,((Texture) prop.value).ID);
					textureINT++;
					break;
				case Vector2:
					GL20.glUniform2f(gsm.getUniformLocation(prop.name),((Vector2) prop.value).x,((Vector2) prop.value).y);
					break;
				case Vector3:
					GL20.glUniform3f(gsm.getUniformLocation(prop.name),((Vector3) prop.value).x,((Vector3) prop.value).y,((Vector3) prop.value).z);
					break;
				case Boolean:
					GL20.glUniform1i(gsm.getUniformLocation(prop.name), (int) prop.value);
					break;
				case Matrix:
					((Matrix4f) prop.value).store(matrixBuffer);
					matrixBuffer.flip();
					GL20.glUniformMatrix4fv(gsm.getUniformLocation(prop.name), false, matrixBuffer);
					matrixBuffer = BufferUtils.createFloatBuffer(16);
					break;
				case LightData:
					/*
						uniform int pointLightCount;
						uniform int directionalLightCount;
						
						uniform vec3 pointLightPositions[128];
						uniform vec3 pointLightColors[128];
						uniform float pointLightIntensities[128];
						uniform vec3 directionalLightDirections[128];
						uniform vec3 directionalLightColors[128];
						uniform float directionalLightIntensities[128];
					*/
					int pointLightPosIndex = 0;
					int pointLightColIndex = 0;
					int pointLightIntensIndex = 0;
					int directionalLightDirIndex = 0;
					int directionalLightColIndex = 0;
					int directionalLightIntensIndex = 0;
					int pointLightCount = 0;
					int directionalLightCount = 0;
					for(Light light : lightList) {
						if(light instanceof PointLight) {
							pointLightCount++;
							Vector3 position = light.gameObject.position;
							GL20.glUniform3f(gsm.getUniformLocation("pointLightPositions[" + pointLightPosIndex + "]"),
									position.x, position.y, position.z);
							pointLightPosIndex++;
							GL20.glUniform3f(gsm.getUniformLocation("pointLightColors[" + pointLightColIndex + "]"),
									light.color.getRed() / 255f,light.color.getGreen() / 255f,light.color.getBlue() / 255f);
							pointLightColIndex++;
							GL20.glUniform1f(gsm.getUniformLocation("pointLightIntensities[" + pointLightIntensIndex + "]"),
									light.intensity);
							pointLightIntensIndex++;
						}
						if(light instanceof DirectionalLight) {
							directionalLightCount++;
							Vector3 forward = light.gameObject.forward();
							GL20.glUniform3f(gsm.getUniformLocation("directionalLightDirections[" + directionalLightDirIndex + "]"),
									forward.x,forward.y,forward.z);
							directionalLightDirIndex++;
							Vector3 color = Vector3.fromColor(light.color);
							GL20.glUniform3f(gsm.getUniformLocation("directionalLightColors[" + directionalLightColIndex + "]"),
									color.x,color.y,color.z);
							directionalLightColIndex++;
							GL20.glUniform1f(gsm.getUniformLocation("directionalLightIntensities[" + directionalLightIntensIndex + "]"),
									light.intensity);
							directionalLightIntensIndex++;
						}
					}
					GL20.glUniform1i(gsm.getUniformLocation("pointLightCount"), pointLightCount);
					GL20.glUniform1i(gsm.getUniformLocation("directionalLightCount"), directionalLightCount);
					break;
				case MatrixData:
					transform.store(matrixBuffer);
					matrixBuffer.flip();
					GL20.glUniformMatrix4fv(gsm.getUniformLocation("transformationMatrix"), false, matrixBuffer);
					matrixBuffer = BufferUtils.createFloatBuffer(16);
					break;
			}
		}
	}

	public static void loadFontShader(FontShader shader) {
		int textureINT = 1;
		for(MaterialProperty prop : shader.properties) {
			switch(prop.type) {
				case Float:
					GL20.glUniform1f(shader.getUniformLocation(prop.name), (float) prop.value);
					break;
				case Integer:
					GL20.glUniform1i(shader.getUniformLocation(prop.name), (int) prop.value);
					break;
				case sampler1D:
					GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureINT);
					GL11.glBindTexture(GL11.GL_TEXTURE_1D, ((Texture) prop.value).ID);
					textureINT++;
					break;
				case sampler2D:
					GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureINT);
					GL11.glBindTexture(GL11.GL_TEXTURE_2D,((Texture) prop.value).ID);
					textureINT++;
					break;
				case Vector2:
					GL20.glUniform2f(shader.getUniformLocation(prop.name),((Vector2) prop.value).x,((Vector2) prop.value).y);
					break;
				case Vector3:
					GL20.glUniform3f(shader.getUniformLocation(prop.name),((Vector3) prop.value).x,((Vector3) prop.value).y,((Vector3) prop.value).z);
					break;
				case Boolean:
					GL20.glUniform1i(shader.getUniformLocation(prop.name), (int) prop.value);
					break;
				case Matrix:
					((Matrix4f) prop.value).store(matrixBuffer);
					matrixBuffer.flip();
					GL20.glUniformMatrix4fv(shader.getUniformLocation(prop.name), false, matrixBuffer);
					matrixBuffer = BufferUtils.createFloatBuffer(16);
					break;
				case LightData:
					System.err.println("Fonts do not support lighting!");
					System.exit(-1);
					break;
				case MatrixData:
					System.err.println("MatrixData is not allowed for fonts!");
					System.exit(-1);
					break;
			}
		}
	}
	
}
