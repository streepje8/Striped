package com.streep.engine.GUI;

import com.streep.engine.core.rendering.RendererBase;

public class Window {
	
	private GlWindow window;
	public RendererBase renderer;
	
	public Window(int width, int height,String title ,RendererBase renderer) {
		this.renderer = renderer;
		this.window = new GlWindow(width, height, title);
	}

	public GlWindow getWindow() {
		return window;
	}
	
}
