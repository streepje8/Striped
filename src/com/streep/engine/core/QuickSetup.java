package com.streep.engine.core;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import javax.swing.JFrame;

import com.streep.engine.GUI.GlWindow;
import com.streep.engine.GUI.JWindow;
import com.streep.engine.GUI.Window;
import com.streep.engine.GUI.Window.windowMode;
import com.streep.engine.buildincomponents.renderers.SpriteRenderer;
import com.streep.engine.core.rendering.JRenderer;
import com.streep.engine.core.rendering.RendererManager;
import com.streep.engine.systems.GameObject;
import com.streep.engine.systems.LevelManager;
import com.streep.engine.util.Time;

public class QuickSetup {

	public static JFrame window(String title, int width, int height) {
		JFrame f = new JFrame(title);
		f.setSize(width, height);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.addKeyListener(Game.Input);
		f.addMouseListener(Game.Input);
		return f;
	}
	
	private static long lastTime = System.nanoTime();
	private static final double ticks = 60D;
	private static double ns = 1000000000 / ticks;    
	private static double delta = 0;
	public static boolean EndLoop = false;
	public static Color clearColor = Color.BLACK;
	private static int skipticks = 30;
	
	public static void startWindow(Window window, WindowCode c) {
		c.onStart();
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
		    		if(window.getType() == windowMode.Win2D) {
		    			JWindow jwin = (JWindow) window.getWindow();
		    			JFrame frame = jwin.getFrame();
		    			BufferedImage buffer = createBuffer(frame);
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
				    	for(GameObject gameo : LevelManager.currentLevel.objects) {
				    		if(skipticks < 0) {
					    		for(Component comp : gameo.getComponents()) {
										comp.update();
								}
				    		} else {
								skipticks--;
							}
				    	}
				    	for(JRenderer jr : RendererManager.jRenderers) {
				    		jr.onRender(buffer, g);
				    	}
				    	c.update(buffer);
			    		swapBuffer(frame, buffer);
		    		}
		    		if(window.getType() == windowMode.Win3D) {
			    		GlWindow glwin = (GlWindow) window.getWindow();
			    		glwin.update();
			    		if(LevelManager.currentLevel.background != null) {
			    			//update this to opengl code
				    		//g.drawImage(LevelManager.currentLevel.background,0,0,buffer.getWidth(),buffer.getHeight(),null);
				    	}
				    	for(GameObject gameo : LevelManager.currentLevel.objects) {
				    		if(skipticks < 0) {
				    			boolean dorem = false; //should not be in update but aint got time to make it better
					    		for(Component comp : gameo.getComponents()) {
										comp.update();
										if(comp instanceof SpriteRenderer) {
											System.err.println("SpriteRenderer has no 3d support!");
											dorem = true;
										}
								}
					    		if(dorem) {
					    			gameo.removeComponent(SpriteRenderer.class);
					    		}
				    		} else {
								skipticks--;
							}
				    	}
				    	c.update(null);
				    	if(glfwWindowShouldClose(glwin.getWindow())) {
			    			EndLoop = true;
			    		}
				    	glwin.swapBuffers();
			    	}
				}
		    	delta--;
		    }
		}
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
