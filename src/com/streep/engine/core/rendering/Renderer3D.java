package com.streep.engine.core.rendering;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import com.streep.engine.GUI.GlWindow;
import com.streep.engine.GUI.Window;
import com.streep.engine.GUI.Window.windowBackend;
import com.streep.engine.core.Setup;
import com.streep.engine.systems.LevelManager;

public class Renderer3D extends RendererBase {

	@Override
	public void onRenderImage(Window window) {
		if(window.getBackend() == windowBackend.OpenGL) {
    		GlWindow glwin = (GlWindow) window.getWindow();
    		glwin.update();
    		if(LevelManager.currentLevel.background != null) {
    			//update this to opengl code
	    		//g.drawImage(LevelManager.currentLevel.background,0,0,buffer.getWidth(),buffer.getHeight(),null);
	    	}
	    	if(glfwWindowShouldClose(glwin.getWindow())) {
    			Setup.EndLoop = true;
    		}
	    	while(RendererManager.jRenderers.size() > 0) {
	    		JRenderer jr = RendererManager.jRenderers.get(0);
	    		jr.gameObject.removeComponent(jr);
	    		System.err.println("[Warning/WARN] " + jr.getClass().getName() + " does not support 2DRenderer.");
	    	}
	    	for(GlRenderer glr : RendererManager.glRenderers) {
	    		glr.onRender();
	    	}
	    	
	    	glwin.swapBuffers();
    	} else {
			System.err.println("[Fatal/ERROR] Renderer3D does only support the OpenGL backend!");
			System.exit(0);
		}
	}

	
	
}
