package engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import entities.Camera;
import entities.Entity;
import models.ResourceModels;
import render.Renderer;
import render.Window;
import shaders.StaticShader;
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
	
	Camera camera;
	World world;
	
	public GameEngine()
	{
		Window.createDisplay();
		shader = new StaticShader();
		renderer = new Renderer(shader);
		
		//Cameras
		camera = new Camera();
		
		ResourceModels.createModels();
		
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
