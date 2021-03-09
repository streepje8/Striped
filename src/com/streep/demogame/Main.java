package com.streep.demogame;

import com.streep.engine.GUI.Window;
import com.streep.engine.buildincomponents.renderers.MeshRenderer;
import com.streep.engine.core.Game;
import com.streep.engine.core.Setup;
import com.streep.engine.core.WindowCode;
import com.streep.engine.core.rendering.RendererGL;
import com.streep.engine.elements.Mesh;
import com.streep.engine.systems.GameObject;
import com.streep.engine.systems.Level;
import com.streep.engine.systems.LevelManager;

public class Main extends Game {

	public static void main(String[] args) {
		//Create the game view
		Window window = new Window(800,400,"DemoGame", new RendererGL());
		float[] positions = {
			    // VO
			    -0.5f,  0.5f,  0.5f,
			    // V1
			    -0.5f, -0.5f,  0.5f,
			    // V2
			    0.5f, -0.5f,  0.5f,
			    // V3
			     0.5f,  0.5f,  0.5f,
			    // V4
			    -0.5f,  0.5f, -0.5f,
			    // V5
			     0.5f,  0.5f, -0.5f,
			    // V6
			    -0.5f, -0.5f, -0.5f,
			    // V7
			     0.5f, -0.5f, -0.5f,
			};
		int[] indices = {
			    // Front face
			    0, 1, 3, 3, 1, 2,
			    // Top Face
			    4, 0, 3, 5, 4, 3,
			    // Right face
			    3, 2, 7, 5, 3, 7,
			    // Left face
			    6, 1, 0, 6, 0, 4,
			    // Bottom face
			    2, 1, 6, 2, 6, 7,
			    // Back face
			    7, 6, 4, 7, 4, 5,
			};
		Mesh m = new Mesh(positions,indices);
		GameObject Triangle = new GameObject();
		MeshRenderer meshrend = new MeshRenderer();
		meshrend.mesh = m;
		Triangle.addComponent(meshrend);
		
		//Resource playerspriteResource = new Resource("Sprite-0001.png");
		//Sprite playersprite = new Sprite(playerspriteResource, 32, 32, 1, 5, 0, 0, 0, 0);
		
		//Create and open the level
		Level l = new Level("LevelOne");
		
		l.addObject(Triangle);
		
		LevelManager.gotoLevel(l);
		
		//Start the game loop
		Setup.startWindow(window, new WindowCode() {
			
			@Override
			public void onStart() {
				
			}
			
			@Override
			public void update() {
				Triangle.rotation.x += 1;
				Triangle.rotation.y += 1;
				Triangle.rotation.z += 1;
			}
			
		});
	}

}
