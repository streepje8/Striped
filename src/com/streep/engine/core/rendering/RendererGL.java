package com.streep.engine.core.rendering;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.util.List;

import com.streep.engine.GUI.GlWindow;
import com.streep.engine.GUI.Window;
import com.streep.engine.buildincomponents.lights.Light;
import com.streep.engine.buildincomponents.renderers.Camera;
import com.streep.engine.core.Setup;

public class RendererGL extends RendererBase {
	
	private Window window;
	
	@Override
	public void RenderImage(Camera c, List<Light> lightList, Window window) {
		this.window = window;
    	for(GlRenderer glr : RendererManager.glRenderers) {
    		glr.onRender(c, lightList, this);
    	}
	}

	public Window getWindow() {
		return this.window;
	}

	@Override
	public void PreRender(Window window) {
		GlWindow glwin = (GlWindow) window.getWindow();
		glwin.clear();
		if(glfwWindowShouldClose(((GlWindow) window.getWindow()).getWindow())) {
			Setup.EndLoop = true;
		}
	}

	@Override
	public void PostRender(Window window) {
		GlWindow glwin = (GlWindow) window.getWindow();
		glwin.swapBuffers();
	}

	
	
}
