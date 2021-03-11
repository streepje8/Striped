package com.streep.engine.GUI;

import com.streep.engine.core.rendering.RendererBase;

public class Window {
	
	private BasisWindow window;
	public RendererBase renderer;
	
	public Window(int width, int height,String title ,RendererBase renderer) {
		this.renderer = renderer;
		this.window = new GlWindow(width, height, title);
	}
	
	public Window(int width, int height,String title ,RendererBase renderer, BasisWindow window) {
		this.renderer = renderer;
		this.window = window;
	}

	public BasisWindow getWindow() {
		return window;
	}
	
}
