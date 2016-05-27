package engine;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import models.ResourceModels;
import render.Loader;
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
	Loader loader;
	
	World world;
	
	public GameEngine()
	{
		Window.createDisplay();
		shader = new StaticShader();
		renderer = new Renderer();
		loader = new Loader();

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
		world = new World(this, resourceList);
		
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
		
		
		while(!Display.isCloseRequested()){	
			world.update();
			
			renderer.prepare();
			
			world.render(renderer, shader);
			
			Window.updateDisplay();
		}
	}

	private void init(){
	}
	
	public Loader getLoader() {
		return loader;
	}
	
	public Renderer getRenderer() {
		return renderer;
	}
}
