package com.streep.demogame;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.streep.engine.buildincomponents.CircleCollider;
import com.streep.engine.buildincomponents.PhysicsComponent;
import com.streep.engine.buildincomponents.PlatformerController;
import com.streep.engine.buildincomponents.SquareCollider;
import com.streep.engine.core.Game;
import com.streep.engine.core.QuickSetup;
import com.streep.engine.core.WindowCode;
import com.streep.engine.subclasses.Resource;
import com.streep.engine.subclasses.Sprite;
import com.streep.engine.systems.GameObject;
import com.streep.engine.systems.Level;
import com.streep.engine.systems.LevelManager;
import com.streep.engine.systems.SpriteRenderer;

public class Main extends Game {

	public static void main(String[] args) {
		JFrame window = QuickSetup.window("DemoGame", 800, 400);
		Resource playerspriteResource = new Resource("Sprite-0001.png");
		Sprite playersprite = new Sprite(playerspriteResource, 32, 32, 1, 5, 0, 0, 0, 0);
		GameObject player = new GameObject(400,200);
		player.addComponent(new CircleCollider());
		player.addComponent(new PlatformerController());
		((CircleCollider) (player.getComponent(CircleCollider.class))).radius = 32f;
		player.addComponent(new PhysicsComponent());
		player.addComponent(new SpriteRenderer());
		SpriteRenderer sp = ((SpriteRenderer) player.getComponent(SpriteRenderer.class));
		sp.sprite = playersprite;
		sp.speed = 0.5f;
		GameObject ground = new GameObject(0,300);
		ground.addComponent(new SquareCollider());
		((SquareCollider) (ground.getComponent(SquareCollider.class))).width = 800f;
		((SquareCollider) (ground.getComponent(SquareCollider.class))).height = 100f;
		Level l = new Level("LevelOne");
		LevelManager.gotoLevel(l);
		l.addObject(player);
		l.addObject(ground);
		QuickSetup.startWindow(window, new WindowCode() {
			
			@Override
			public void onStart() {
				
			}
			
			@Override
			public void update(BufferedImage buffer) {
				
			}
			
		});
	}

}
