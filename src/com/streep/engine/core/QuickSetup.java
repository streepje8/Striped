package com.streep.engine.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.streep.engine.systems.Component;
import com.streep.engine.systems.GameObject;
import com.streep.engine.systems.LevelManager;
import com.streep.engine.systems.SpriteRenderer;
import com.streep.engine.systems.Time;

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
	
	public static void startWindow(JFrame window, WindowCode c) {
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
		    	Time.DeltaTime = delta;
		    	BufferedImage buffer = createBuffer(window);
		    	Graphics g = buffer.getGraphics();
		    	for(GameObject gameo : LevelManager.currentLevel.objects) {
					for(Component comp : gameo.getComponents()) {
						comp.update();
						if(comp instanceof SpriteRenderer) {
							SpriteRenderer sp = (SpriteRenderer) comp;
							sp.renderSprite(gameo.x, gameo.y, g);
						}
					}
				}
		    	c.update(buffer);
		    	swapBuffer(window, buffer);
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
