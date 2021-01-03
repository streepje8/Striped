package com.streep.engine.GUI;

public class Window {
	
	public static enum windowMode {
		Win2D,
		Win3D
	}
	
	private BasisWindow window;
	private windowMode mode = null;
	
	public Window(int width, int height,String title ,windowMode w) {
		this.mode = w;
		if(w == windowMode.Win2D) {
			this.window = new JWindow(width, height, title);
		}
		if(w == windowMode.Win3D) {
			this.window = new GlWindow(width, height, title);
		}
	}

	public BasisWindow getWindow() {
		return window;
	}

	public windowMode getType() {
		return this.mode;
	}
	
}
