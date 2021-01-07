package com.streep.engine.core.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.streep.engine.core.Component;

public abstract class JRenderer extends Component {
	
	private static final long serialVersionUID = 1L;
	
	public void register() {
		if(!RendererManager.jRenderers.contains(this)) {
			RendererManager.jRenderers.add(this);
		}
	}
	
	public void deRegister() {
		if(RendererManager.jRenderers.contains(this)) {
			RendererManager.jRenderers.remove(this);
		}
	}

	public abstract void onRender(BufferedImage buffer, Graphics g);
	
}
