package com.streep.engine.buildincomponents.renderers;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjglx.BufferUtils;

import com.streep.engine.core.rendering.GlRenderer;
import com.streep.engine.core.rendering.RendererGL;
import com.streep.engine.core.rendering.GLRenderer.MaterialProperty;
import com.streep.engine.core.rendering.GLRenderer.RuntimeMesh;
import com.streep.engine.core.rendering.GLRenderer.VMemory;
import com.streep.engine.elements.Material;
import com.streep.engine.elements.Mesh;
import com.streep.engine.elements.Texture;
import com.streep.engine.util.SMath;
import com.streep.engine.util.Vector2;
import com.streep.engine.util.Vector3;

public class MeshRenderer extends GlRenderer {

	private static final long serialVersionUID = 1L;
	public Mesh mesh;
	private RuntimeMesh runtimeMesh;
	public Material material;
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	@Override
	public void onRender(RendererGL renderer) {
		this.material.start();
		GL30.glBindVertexArray(runtimeMesh.getVao());
		for(int i = 0; i < this.material.attributes.size(); i++) {
			GL20.glEnableVertexAttribArray(i);
		}
		int textureINT = 0;
		for(MaterialProperty prop : this.material.properties) {
			if(prop.name == "transformationMatrix") {
				prop.value = SMath.createTransformationMatrix(this.gameObject.position,this.gameObject.rotation, this.gameObject.scale);
			}
			switch(prop.type) {
				case Float:
					GL20.glUniform1f(this.material.getUniformLocation(prop.name), (float) prop.value);
					break;
				case Integer:
					GL20.glUniform1i(this.material.getUniformLocation(prop.name), (int) prop.value);
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
					GL20.glUniform2f(this.material.getUniformLocation(prop.name),((Vector2) prop.value).x,((Vector2) prop.value).y);
					break;
				case Vector3:
					GL20.glUniform3f(this.material.getUniformLocation(prop.name),((Vector3) prop.value).x,((Vector3) prop.value).y,((Vector3) prop.value).z);
					break;
				case Boolean:
					GL20.glUniform1i(this.material.getUniformLocation(prop.name), (int) prop.value);
					break;
				case Matrix:
					((Matrix4f) prop.value).store(matrixBuffer);
					matrixBuffer.flip();
					GL20.glUniformMatrix4fv(this.material.getUniformLocation(prop.name), false, matrixBuffer);
					matrixBuffer = BufferUtils.createFloatBuffer(16);
					break;
			}
		}
		GL11.glDrawElements(GL11.GL_TRIANGLES, runtimeMesh.getVertices(), GL11.GL_UNSIGNED_INT, 0);
		for(int i = 0; i < this.material.attributes.size(); i++) {
			GL20.glDisableVertexAttribArray(i);
		}
		GL30.glBindVertexArray(0);
		this.material.stop();
	}

	@Override
	public void start() {
		register();
		this.runtimeMesh = VMemory.storeMesh(this.mesh, this.material);
		this.material.loadToMemory();
	}

	@Override
	public void onGameEnd() {
		this.material.deleteFromMemory();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
