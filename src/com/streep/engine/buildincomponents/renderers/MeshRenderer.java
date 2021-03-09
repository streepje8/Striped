package com.streep.engine.buildincomponents.renderers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.streep.engine.GUI.GlWindow;
import com.streep.engine.core.rendering.GlRenderer;
import com.streep.engine.core.rendering.RendererGL;
import com.streep.engine.core.rendering.GLRenderer.RuntimeMesh;
import com.streep.engine.core.rendering.GLRenderer.VMemory;
import com.streep.engine.elements.Mesh;

public class MeshRenderer extends GlRenderer {

	private static final long serialVersionUID = 1L;
	public Mesh mesh;
	private RuntimeMesh runtimeMesh;
	
	@Override
	public void onRender(RendererGL renderer) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GlWindow window = renderer.getWindow().getWindow();
		GL11.glOrtho(0, window.width, window.height, 0, -1, 1);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glTranslatef(this.gameObject.position.x,this.gameObject.position.y,this.gameObject.position.z); 
		GL11.glRotatef(this.gameObject.rotation.x,1.0f,0.0f,0.0f);
		GL11.glRotatef(this.gameObject.rotation.y,0.0f,1.0f,0.0f);
		GL11.glRotatef(this.gameObject.rotation.z,0.0f,0.0f,1.0f);
		GL30.glBindVertexArray(runtimeMesh.getVao());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawElements(GL11.GL_TRIANGLES, runtimeMesh.getVertices(), GL11.GL_UNSIGNED_INT,0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		GL11.glPopMatrix();
		GL11.glEnd();
		   
	}

	@Override
	public void start() {
		register();
		this.runtimeMesh = VMemory.storeMesh(this.mesh);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
