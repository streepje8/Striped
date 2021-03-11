package com.streep.demogame;

import com.streep.engine.GUI.Window;
import com.streep.engine.buildincomponents.controllers.CameraDebugController;
import com.streep.engine.buildincomponents.lights.DirectionalLight;
import com.streep.engine.buildincomponents.lights.PointLight;
import com.streep.engine.buildincomponents.renderers.Camera;
import com.streep.engine.buildincomponents.renderers.MeshRenderer;
import com.streep.engine.core.Game;
import com.streep.engine.core.Setup;
import com.streep.engine.core.WindowCode;
import com.streep.engine.core.rendering.RendererGL;
import com.streep.engine.core.rendering.GLRenderer.MaterialProperty;
import com.streep.engine.core.rendering.GLRenderer.MaterialProperty.MaterialPropertyType;
import com.streep.engine.elements.AssetImporter;
import com.streep.engine.elements.Material;
import com.streep.engine.elements.Mesh;
import com.streep.engine.elements.Texture;
import com.streep.engine.systems.GameObject;
import com.streep.engine.systems.Level;
import com.streep.engine.systems.LevelManager;
import com.streep.engine.util.Vector3;

public class Main extends Game {

	public static void main(String[] args) {
		//Create the game view
		Window window = new Window(800,400,"DemoGame", new RendererGL());
		GameObject cam = new GameObject();
		cam.addComponent(new Camera());
		cam.addComponent(new CameraDebugController());
		
		GameObject dirLight = new GameObject(0,0,0);
		dirLight.addComponent(new DirectionalLight());
		
		Mesh m = AssetImporter.importOBJ("C:\\Users\\Wessel\\Desktop\\blendermonk.obj");
		GameObject Triangle = new GameObject();
		Material material = new Material("./Resources/DefaultAssets/defaultVertex.shader", "./Resources/DefaultAssets/defaultFragment.shader");
		material.properties.add(new MaterialProperty("textureSampler", MaterialPropertyType.sampler2D, new Texture("./Resources/DefaultAssets/demoTexture.png"))); //C:\\Users\\Wessel\\Desktop\\stallTexture.png
		MeshRenderer meshrend = new MeshRenderer();
		meshrend.mesh = m;
		meshrend.material = material;
		Triangle.addComponent(meshrend);
		PointLight camlight = new PointLight();
		camlight.intensity = 3f;
		cam.addComponent(camlight);
		
		//Resource playerspriteResource = new Resource("Sprite-0001.png");
		//Sprite playersprite = new Sprite(playerspriteResource, 32, 32, 1, 5, 0, 0, 0, 0);
		
		//Create and open the level
		Level l = new Level("LevelOne");
		
		l.addObject(Triangle);
		l.addObject(cam);
		//l.addObject(dirLight);
		
		Triangle.position = new Vector3(0,0,-8);
		
		LevelManager.gotoLevel(l);
		
		//Start the game loop
		Setup.startWindow(window, new WindowCode() {
			
			@Override
			public void onStart() {
				
			}
			
			@Override
			public void update() {
				Triangle.rotation.y = 180;
				dirLight.rotation.x += 1;
				dirLight.rotation.y += 1;
				dirLight.rotation.z += 1;
			}
			
		});
	}

}
