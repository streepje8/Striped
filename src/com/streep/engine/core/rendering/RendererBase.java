package com.streep.engine.core.rendering;

import com.streep.engine.GUI.Window;
import com.streep.engine.buildincomponents.renderers.Camera;

public abstract class RendererBase {

	public void preUpdate(Window window) {
		
	}
	
	public abstract void PreRender(Window window);
	public abstract void RenderImage(Camera c, Window window);
	public abstract void PostRender(Window window);
}
