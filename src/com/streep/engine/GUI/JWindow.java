package com.streep.engine.GUI;

import javax.swing.JFrame;

import com.streep.engine.core.QuickSetup;

public class JWindow extends BasisWindow {

	private JFrame frame = new JFrame();
	
	public JWindow(int width, int height, String title) {
		JFrame window = QuickSetup.window(title, width, height);
		frame = window;
	}

	public JFrame getFrame() {
		return this.frame;
	}
	
}
