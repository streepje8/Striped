package com.streep.engine.GUI;

import javax.swing.JFrame;

import com.streep.engine.core.Game;

public class JWindow extends BasisWindow {

	private JFrame frame = new JFrame();
	
	public JWindow(int width, int height, String title) {
		JFrame f = new JFrame(title);
		f.setSize(width, height);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.addKeyListener(Game.Input);
		f.addMouseListener(Game.Input);
		frame = f;
	}

	public JFrame getFrame() {
		return this.frame;
	}
	
}
