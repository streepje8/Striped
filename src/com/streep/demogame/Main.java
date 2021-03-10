package com.streep.demogame;

import com.streep.engine.GUI.Window;
import com.streep.engine.buildincomponents.renderers.MeshRenderer;
import com.streep.engine.core.Game;
import com.streep.engine.core.Setup;
import com.streep.engine.core.WindowCode;
import com.streep.engine.core.rendering.RendererGL;
import com.streep.engine.core.rendering.GLRenderer.MaterialProperty;
import com.streep.engine.core.rendering.GLRenderer.MaterialProperty.MaterialPropertyType;
import com.streep.engine.elements.Material;
import com.streep.engine.elements.Mesh;
import com.streep.engine.elements.Texture;
import com.streep.engine.systems.GameObject;
import com.streep.engine.systems.Level;
import com.streep.engine.systems.LevelManager;

public class Main extends Game {

	public static void main(String[] args) {
		//Create the game view
		Window window = new Window(800,400,"DemoGame", new RendererGL());
		float[] vertices = {
				-0.5f, 0.5f, 0f,//v0
				-0.5f, -0.5f, 0f,//v1
				0.5f, -0.5f, 0f,//v2
				0.5f, 0.5f, 0f,//v3
		};
		int[] indices = {
				0,1,3, //top left triangle (v0, v1, v3)
				3,1,2 //bottom right triangle (v3, v1, v2)
		};	
		Mesh m = new Mesh(vertices,indices);
		GameObject Triangle = new GameObject();
		Material material = new Material("./Resources/DefaultAssets/defaultVertex.shader", "./Resources/DefaultAssets/defaultFragment.shader");
		material.properties.add(new MaterialProperty("textureSampler", MaterialPropertyType.sampler2D, new Texture("./Resources/DefaultAssets/demoTexture.png")));
		MeshRenderer meshrend = new MeshRenderer();
		meshrend.mesh = m;
		meshrend.material = material;
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
