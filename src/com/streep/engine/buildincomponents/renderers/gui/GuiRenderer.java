package com.streep.engine.buildincomponents.renderers.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import com.streep.engine.buildincomponents.lights.Light;
import com.streep.engine.buildincomponents.renderers.Camera;
import com.streep.engine.buildincomponents.renderers.gui.fontRendering.TextMaster;
import com.streep.engine.core.rendering.GlRenderer;
import com.streep.engine.core.rendering.RendererGL;
import com.streep.engine.core.rendering.GLRenderer.VMemory;
import com.streep.engine.util.SMath;
import com.streep.engine.util.ShaderUtils;

public class GuiRenderer extends GlRenderer {

	private static final long serialVersionUID = 1L;

	private ArrayList<GuiElement> elements = new ArrayList<GuiElement>();
	public GuiShaderManager shaderManager = new GuiShaderManager();
	
	@Override
	public void onRender(Camera c, List<Light> lightList, RendererGL rendererGL) {
		if(c.GUI_ENABLED) {
			GL30.glBindVertexArray(VMemory.quad.getVao());
			GL20.glEnableVertexAttribArray(0);
			shaderManager.start();
			for(GuiElement element : this.elements) {
				if(element instanceof GuiTexture) {
					GuiTexture texture = (GuiTexture) element;
					GL13.glActiveTexture(GL13.GL_TEXTURE0);
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.texture.ID);
					Matrix4f matrix = SMath.createTransformationMatrix(texture.position, texture.rotation, texture.scale);
					ShaderUtils.loadPropertiesGui(this.shaderManager, matrix, lightList);
					GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, VMemory.quad.getVertices());
				}
			}
			GL20.glDisableVertexAttribArray(0);
			GL30.glBindVertexArray(0);
			shaderManager.stop();
			TextMaster.render();
		}
	}
	
	public void addElement(GuiElement element) {
		this.elements.add(element);
	}
	
	public ArrayList<GuiElement> getAllElements() {
		return this.elements;
	}

	@Override
	public void start() {
		register();
		for(GuiElement element : this.elements) {
			if(element instanceof GuiTexture) {
				GuiTexture texture = (GuiTexture) element;
				texture.texture.load();
			}
		}
		this.shaderManager.loadToMemory();
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void onGameEnd() {
		this.shaderManager.deleteFromMemory();
	}

}
