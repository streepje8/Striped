package com.streep.engine.buildincomponents.renderers;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.streep.engine.GUI.GlWindow;
import com.streep.engine.buildincomponents.lights.Light;
import com.streep.engine.core.rendering.GlRenderer;
import com.streep.engine.core.rendering.RendererGL;
import com.streep.engine.core.rendering.GLRenderer.RuntimeMesh;
import com.streep.engine.core.rendering.GLRenderer.VMemory;
import com.streep.engine.elements.Material;
import com.streep.engine.elements.Mesh;
import com.streep.engine.util.ShaderUtils;

public class MeshRenderer extends GlRenderer {

	private static final long serialVersionUID = 1L;
	public Mesh mesh;
	private RuntimeMesh runtimeMesh;
	public Material material;
	
	@Override
	public void onRender(Camera c, List<Light> lightList, RendererGL renderer) {
		this.material.start();
		GL30.glBindVertexArray(runtimeMesh.getVao());
		for(int i = 0; i < this.material.attributes.size(); i++) {
			GL20.glEnableVertexAttribArray(i);
		}
		ShaderUtils.loadProperties(c, this.material, lightList, this.gameObject,((GlWindow) renderer.getWindow().getWindow()));
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
		
	}

}
