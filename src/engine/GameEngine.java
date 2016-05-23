package engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import render.Loader;
import render.OBJLoader;
import render.Renderer;
import render.Window;
import shaders.StaticShader;
import world.Brick;
import world.Robber;
import world.Stone;
import world.Wheat;
import textures.ModelTexture;
import world.Resource;
import world.Sheep;
import world.Wood;
import world.World;

public class GameEngine {
	
	Loader loader;
	StaticShader shader;
	Renderer renderer; 
	
	
	
	Camera camera;
	World world;
	Entity[] entities;
	
	public GameEngine()
	{
		Window.createDisplay();
		loader = new Loader();
		shader = new StaticShader();
		renderer = new Renderer(shader);
		//MODELS
		RawModel woodHex = OBJLoader.loadObjModel("hex", loader);
		RawModel wheatHex = OBJLoader.loadObjModel("hex", loader);
		RawModel brickHex = OBJLoader.loadObjModel("hex", loader);
		RawModel sheepHex = OBJLoader.loadObjModel("hex", loader);
		RawModel rockHex = OBJLoader.loadObjModel("hex", loader);
		RawModel desertHex = OBJLoader.loadObjModel("hex", loader);
		
		
		
		
		//Textured Models declared above
		TexturedModel[] staticModels = {
		new TexturedModel (woodHex, new ModelTexture(loader.loadTexture("Wood"))),
		new TexturedModel (wheatHex, new ModelTexture(loader.loadTexture("Wheat"))),
		new TexturedModel (brickHex, new ModelTexture(loader.loadTexture("Brick"))),
		new TexturedModel (sheepHex, new ModelTexture(loader.loadTexture("Sheep"))),
		new TexturedModel (rockHex, new ModelTexture(loader.loadTexture("Rock"))),
		new TexturedModel (desertHex, new ModelTexture(loader.loadTexture("Desert")))
		};
		
		World.setPositions();
		
		entities = new Entity[19];
		for (int i = 0; i < entities.length; i++) {
			Vector2f pos = World.getPosition(i);
			if (pos.x == 0 && pos.y == 0) System.out.println(i);
			entities[i] = new Entity(staticModels[0], new Vector3f(pos.x, 0, pos.y), 0, 0, 0, 1);
		}
		
		//Cameras
		camera = new Camera();
		
		List<Resource> resourceList = new ArrayList<Resource>();
		for (int i = 0; i < 4; i++) {
			resourceList.add(new Wood());
			resourceList.add(new Wheat());
			resourceList.add(new Sheep());
			if (i < 3) {
				resourceList.add(new Stone());
				resourceList.add(new Brick());
			}
		}
		resourceList.add(new Robber());
		world = new World(resourceList);
		
	}
	public void start()
	{
		init();
		gameLoop();
		shutdown();
	}
	
	private void shutdown() {
		shader.cleanUp();
		loader.cleanUp();
		Window.closeDisplay();
		
	}

	private void gameLoop() {
		
		camera.position.y = 5.5f;
		camera.position.z = 6.0f;
		shader.start();
		
		while(!Display.isCloseRequested()){	
			
			renderer.prepare();

			camera.move();
			shader.loadViewMatrix(camera);	
			
			for (int i = 0; i < entities.length; i++) {
				renderer.render(entities[i], shader);
			}
			
			Window.updateDisplay();
		}
		
		shader.stop();
	}

	private void init(){
	}
}
