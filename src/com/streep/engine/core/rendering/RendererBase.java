package com.streep.engine.core.rendering;

import java.util.List;

import com.streep.engine.GUI.Window;
import com.streep.engine.buildincomponents.lights.Light;
import com.streep.engine.buildincomponents.renderers.Camera;

public abstract class RendererBase {

	public void preUpdate(Window window) {
		
	}
	
	public abstract void PreRender(Window window);
	public abstract void RenderImage(Camera c, List<Light> lightList, Window window);
	public abstract void PostRender(Window window);
}
