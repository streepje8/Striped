package com.streep.engine.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class Input implements MouseListener,KeyListener {
	
	private boolean mousedownL = false;
	private boolean mousedownR = false;
	private HashMap<Character, Boolean> keydownBC = new HashMap<Character, Boolean>();
	private HashMap<Integer, Boolean> keydownCB = new HashMap<Integer, Boolean>();
	
	public static int vk_enter = KeyEvent.VK_ENTER;
	public static int vk_SHIFT = KeyEvent.VK_SHIFT;
	public static int vk_space = KeyEvent.VK_SPACE;
	public static int vk_control = KeyEvent.VK_CONTROL;
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public boolean getMouseButton(int btn) {
		if(btn == 0) {
			return this.mousedownL;
		} else if(btn == 1) {
			return this.mousedownR;
		} else {
			return false;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			this.mousedownL = true;
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			this.mousedownR = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			this.mousedownL = false;
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			this.mousedownR = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		if(this.keydownBC.containsKey(c)) {
			this.keydownBC.remove(c);
		}
		this.keydownBC.put(c, true);
		int k = e.getKeyCode();
		if(this.keydownCB.containsKey(k)) {
			this.keydownCB.remove(k);
		}
		this.keydownCB.put(k, true);
	}

	public boolean getKeyDown(char c) {
		if(this.keydownBC.containsKey(c)) {
			return this.keydownBC.get(c);
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean getKeyDown(int keycode) {
		if(this.keydownBC.containsKey(keycode)) {
			return this.keydownBC.get(keycode);
		} else {
			return false;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		char c = e.getKeyChar();
		if(this.keydownBC.containsKey(c)) {
			this.keydownBC.remove(c);
		}
		this.keydownBC.put(c, false);
		int k = e.getKeyCode();
		if(this.keydownCB.containsKey(k)) {
			this.keydownCB.remove(k);
		}
		this.keydownCB.put(k, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if(this.keydownBC.containsKey(c)) {
			this.keydownBC.remove(c);
		}
		this.keydownBC.put(c, true);
		int k = e.getKeyCode();
		if(this.keydownCB.containsKey(k)) {
			this.keydownCB.remove(k);
		}
		this.keydownCB.put(k, true);
	}

	
	
}
