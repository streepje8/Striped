package com.streep.engine.core.rendering;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.glfw.GLFW;

import com.streep.engine.GUI.GlWindow;
import com.streep.engine.GUI.Window;
import com.streep.engine.core.Setup;

public class RendererGL extends RendererBase {
	
	private Window window;
	
	@Override
	public void onRenderImage(Window window) {
		GlWindow glwin = window.getWindow();
		glwin.update();
		this.window = window;
    	if(glfwWindowShouldClose(glwin.getWindow())) {
			Setup.EndLoop = true;
		}
    	for(GlRenderer glr : RendererManager.glRenderers) {
    		glr.onRender(this);
    	}
    	glwin.swapBuffers();
    	GLFW.glfwPollEvents();
	}

	public Window getWindow() {
		return this.window;
	}

	
	
}
