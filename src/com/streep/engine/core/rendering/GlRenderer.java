package com.streep.engine.core.rendering;

import java.util.List;

import com.streep.engine.buildincomponents.lights.Light;
import com.streep.engine.buildincomponents.renderers.Camera;
import com.streep.engine.core.Component;

public abstract class GlRenderer extends Component {
	
	private static final long serialVersionUID = 1L;
	
	public void register() {
		if(!RendererManager.glRenderers.contains(this)) {
			RendererManager.glRenderers.add(this);
		}
	}
	
	public void deRegister() {
		if(RendererManager.glRenderers.contains(this)) {
			RendererManager.glRenderers.remove(this);
		}
	}

	public abstract void onRender(Camera c, List<Light> lightList, RendererGL rendererGL);
	
}
