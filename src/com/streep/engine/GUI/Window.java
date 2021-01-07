package com.streep.engine.GUI;

import com.streep.engine.core.rendering.RendererBase;

public class Window {
	
	public static enum windowBackend {
		JFrame,
		OpenGL
	}
	
	private BasisWindow window;
	private windowBackend mode = null;
	public RendererBase renderer;
	
	public Window(int width, int height,String title ,windowBackend backend, RendererBase renderer) {
		this.mode = backend;
		this.renderer = renderer;
		if(backend == windowBackend.JFrame) {
			this.window = new JWindow(width, height, title);
		}
		if(backend == windowBackend.OpenGL) {
			this.window = new GlWindow(width, height, title);
		}
	}

	public BasisWindow getWindow() {
		return window;
	}

	public windowBackend getBackend() {
		return this.mode;
	}
	
}
