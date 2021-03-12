package com.streep.engine.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.streep.engine.GUI.Window;
import com.streep.engine.buildincomponents.lights.Light;
import com.streep.engine.buildincomponents.renderers.Camera;
import com.streep.engine.core.rendering.GLRenderer.VMemory;
import com.streep.engine.systems.GameObject;
import com.streep.engine.systems.LevelManager;
import com.streep.engine.util.Time;

public class Setup {
	
	private static long lastTime = System.nanoTime();
	private static final double ticks = 60D;
	private static double ns = 1000000000 / ticks;    
	private static double delta = 0;
	public static boolean EndLoop = false;
	public static Color clearColor = Color.BLACK;
	private static int skipticks = 30;
	
	public static void startWindow(Window window, WindowCode c) {
		c.onStart();
		VMemory.init();
		Game.Input.setMainWindow(window);
		for(GameObject gameo : LevelManager.currentLevel.objects) {
			for(Component comp : gameo.getComponents()) {
				comp.start();
			}
		}
		while(!EndLoop) {
			long now = System.nanoTime();
		    delta += (now - lastTime) / ns;
		    lastTime = now;
		    if(delta >= 1){
		    	if(!(delta >= 5)) { //prevent the game from going to light speed after a lag spike
		    		Time.DeltaTime = delta;
		    		window.renderer.PreRender(window);
		    		window.renderer.preUpdate(window);
		    		List<Camera> renderList = new ArrayList<Camera>();
		    		List<Light> lightList = new ArrayList<Light>();
		    		for(GameObject gameo : LevelManager.currentLevel.objects) {
			    		if(skipticks < 0) {
				    		for(Component comp : gameo.getComponents()) {
									comp.update();
									if(comp instanceof Camera) {
										Camera cam = (Camera) comp;
										renderList.add(cam);
									}
									if(comp instanceof Light) {
										Light l = (Light) comp;
										lightList.add(l);
									}
							}
			    		} else {
							skipticks--;
						}
			    	}
		    		for(Camera cam : renderList) {
		    			window.renderer.RenderImage(cam, lightList, window);
		    		}
		    		c.update();
		    		window.renderer.PostRender(window);
				}
		    	delta--;
		    }
		}
		for(GameObject gameo : LevelManager.currentLevel.objects) {
    		for(Component comp : gameo.getComponents()) {
					comp.onGameEnd();
			}
    	}
		VMemory.cleanUp();
	}
	
	public static BufferedImage createBuffer(JFrame window) {
		BufferedImage buffer = new BufferedImage(window.getContentPane().getWidth(), window.getContentPane().getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = buffer.getGraphics();
		g.setColor(clearColor);
		g.fillRect(0,0,buffer.getWidth(), buffer.getHeight());
		return buffer;
	}
	
	public static void swapBuffer(JFrame window, BufferedImage buffer) {
		window.getContentPane().getGraphics().drawImage(buffer, 0, 0,window.getContentPane().getWidth(),window.getContentPane().getHeight(), null);
	}
	
}
