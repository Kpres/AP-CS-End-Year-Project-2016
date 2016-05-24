package engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import entities.Camera;
import models.RawModel;
import models.ResourceModels;
import models.TexturedModel;
import render.OBJLoader;
import render.Renderer;
import render.Window;
import shaders.StaticShader;
import render.Loader;
import textures.ModelTexture;
import world.Brick;
import world.Desert;
import world.Resource;
import world.Rock;
import world.Sheep;
import world.Wheat;
import world.Wood;
import world.World;

public class GameEngine {
	
	StaticShader shader;
	Renderer renderer; 
	Loader loader;
	
	Camera camera;
	World world;
	
	public GameEngine()
	{
		Window.createDisplay();
		shader = new StaticShader();
		renderer = new Renderer(shader);
		loader = new Loader();
		
		//Cameras
		camera = new Camera();
		
		RawModel woodHex = OBJLoader.loadObjModel("hex", loader);
		RawModel wheatHex = OBJLoader.loadObjModel("hex", loader);
		RawModel brickHex = OBJLoader.loadObjModel("hex", loader);
		RawModel sheepHex = OBJLoader.loadObjModel("hex", loader);
		RawModel rockHex = OBJLoader.loadObjModel("hex", loader);
		RawModel desertHex = OBJLoader.loadObjModel("hex", loader);
		
		TexturedModel[] temp = {
				new TexturedModel (woodHex, new ModelTexture(loader.loadTexture("Wood"))),
				new TexturedModel (wheatHex, new ModelTexture(loader.loadTexture("Wheat"))),
				new TexturedModel (brickHex, new ModelTexture(loader.loadTexture("Brick"))),
				new TexturedModel (sheepHex, new ModelTexture(loader.loadTexture("Sheep"))),
				new TexturedModel (rockHex, new ModelTexture(loader.loadTexture("Rock"))),
				new TexturedModel (desertHex, new ModelTexture(loader.loadTexture("Desert")))
			};
		ResourceModels.createModels(loader);
		
		List<Resource> resourceList = new ArrayList<Resource>();
		for (int i = 0; i < 4; i++) {
			resourceList.add(new Wood());
			resourceList.add(new Wheat());
			resourceList.add(new Sheep());
			if (i < 3) {
				resourceList.add(new Rock());
				resourceList.add(new Brick());
			}
		}
		resourceList.add(new Desert());
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
			
			world.render(renderer, shader);
			
			Window.updateDisplay();
		}
		
		shader.stop();
	}

	private void init(){
	}
}
