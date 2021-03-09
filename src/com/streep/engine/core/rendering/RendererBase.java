package com.streep.engine.core.rendering;

import com.streep.engine.GUI.Window;

public abstract class RendererBase {

	public void preUpdate(Window window) {
		
	}
	
	public abstract void onRenderImage(Window window);
	
}
