package com.streep.engine.core.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import javax.swing.JFrame;

import com.streep.engine.GUI.JWindow;
import com.streep.engine.GUI.Window;
import com.streep.engine.GUI.Window.windowBackend;
import com.streep.engine.core.Setup;
import com.streep.engine.systems.GameObject;
import com.streep.engine.systems.LevelManager;

public class Renderer2D extends RendererBase {

	@Override
	public void onRenderImage(Window window) {
		if(window.getBackend() == windowBackend.JFrame) {
			JWindow jwin = (JWindow) window.getWindow();
			JFrame frame = jwin.getFrame();
			BufferedImage buffer = Setup.createBuffer(frame);
	    	Graphics g = buffer.getGraphics();
	    	if(LevelManager.currentLevel.background != null) {
	    		g.drawImage(LevelManager.currentLevel.background,0,0,buffer.getWidth(),buffer.getHeight(),null);
	    	}
	    	LevelManager.currentLevel.objects.sort(new Comparator<GameObject>() {
				@Override
				public int compare(GameObject o1, GameObject o2) {
					if(o1.z < o2.z) {
						return 1;
					}
					return 0;
				}
			});
	    	
	    	for(JRenderer jr : RendererManager.jRenderers) {
	    		jr.onRender(buffer, g);
	    	}
	    	while(RendererManager.glRenderers.size() > 0) {
	    		GlRenderer glr = RendererManager.glRenderers.get(0);
	    		glr.gameObject.removeComponent(glr);
	    		System.err.println("[Warning/WARN] " + glr.getClass().getName() + " does not support 2DRenderer.");
	    	}
    		Setup.swapBuffer(frame, buffer);
		} else {
			System.err.println("[Fatal/ERROR] Renderer2D does only support the JFrame backend!");
			System.exit(0);
		}
	}

}
