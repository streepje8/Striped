package com.streep.demogame;

import java.awt.image.BufferedImage;

import com.streep.engine.GUI.Window;
import com.streep.engine.GUI.Window.windowMode;
import com.streep.engine.buildincomponents.colliders.CircleCollider;
import com.streep.engine.buildincomponents.colliders.SquareCollider;
import com.streep.engine.buildincomponents.controllers.PlatformerController;
import com.streep.engine.buildincomponents.physics.PhysicsComponent;
import com.streep.engine.buildincomponents.renderers.SpriteRenderer;
import com.streep.engine.core.Game;
import com.streep.engine.core.QuickSetup;
import com.streep.engine.core.WindowCode;
import com.streep.engine.subclasses.Resource;
import com.streep.engine.subclasses.Sprite;
import com.streep.engine.systems.GameObject;
import com.streep.engine.systems.Level;
import com.streep.engine.systems.LevelManager;

public class Main extends Game {

	public static void main(String[] args) {
		//Create the game view
		Window window = new Window(800,400,"DemoGame", windowMode.Win2D);
		
		//make the player object
		Resource playerspriteResource = new Resource("Sprite-0001.png");
		Sprite playersprite = new Sprite(playerspriteResource, 32, 32, 1, 5, 0, 0, 0, 0);
		GameObject player = new GameObject(400,200);
		player.name = "Player";
		
		//add components
		player.addComponent(new CircleCollider());
		player.addComponent(new PlatformerController());
		player.addComponent(new PhysicsComponent());
		player.addComponent(new SpriteRenderer());
		
		
		//configure components
		player.getComponent(CircleCollider.class).radius = 32f;
		SpriteRenderer sp = player.getComponent(SpriteRenderer.class);
		sp.sprite = playersprite;
		sp.speed = 0.5f;
		
		//make the ground object
		GameObject ground = new GameObject(0,300);
		ground.name = "Ground";
		ground.addComponent(new SquareCollider());
		ground.getComponent(SquareCollider.class).width = 800f;
		ground.getComponent(SquareCollider.class).height = 100f;
		
		//Create and open the level
		Level l = new Level("LevelOne");
		LevelManager.gotoLevel(l);
		
		//Add the objects
		l.addObject(player);
		l.addObject(ground);
		
		//Start the game loop
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
