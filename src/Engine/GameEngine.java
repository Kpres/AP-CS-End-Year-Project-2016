package Engine;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Render.Window;
import Render.Loader;
import Render.OBJLoader;
import Render.Renderer;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Models.RawModel;
import Models.TexturedModel;

public class GameEngine {
	
	Loader loader;
	StaticShader shader;
	Renderer renderer; 
	
	
	
	Camera camera;
	Entity entity1, entity2, entity3, entity4, entity5, entity6,entity7,entity8,entity9,entity10,entity11,entity12, entity13, entity14,
	entity15, entity16, entity17, entity18, entity19;
	
	TexturedModel staticModel1, staticModel2, staticModel3, staticModel4, staticModel5, staticModel6;
	
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
		staticModel1 = new TexturedModel (woodHex, new ModelTexture(loader.loadTexture("Wood")));
		staticModel2 = new TexturedModel (wheatHex, new ModelTexture(loader.loadTexture("Wheat")));
		staticModel3 = new TexturedModel (brickHex, new ModelTexture(loader.loadTexture("Brick")));
		staticModel4 = new TexturedModel (sheepHex, new ModelTexture(loader.loadTexture("Sheep")));
		staticModel5 = new TexturedModel (rockHex, new ModelTexture(loader.loadTexture("Rock")));
		staticModel6 = new TexturedModel (desertHex, new ModelTexture(loader.loadTexture("Desert")));
		
		
		//Entities declared above
		    entity1 = new Entity(staticModel1, new Vector3f(0,-1,-5),0,0,0,1); //wood
		  entity2 = new Entity(staticModel2, new Vector3f(3,-1,-3.27f),0,0,0,1); //wheat
		  entity3 = new Entity(staticModel3, new Vector3f(-3,-1,-3.27f),0,0,0,1); //brick
		  entity4 = new Entity(staticModel4, new Vector3f(0,-1,-8.46f),0,0,0,1); //sheep
		    entity5 = new Entity(staticModel5, new Vector3f(3,-1,-6.73f),0,0,0,1); //rock
		entity6 = new Entity(staticModel6, new Vector3f(-3,-1,-6.73f),0,0,0,1); //desert
		 
		entity7 = new Entity(staticModel1, new Vector3f(0,-1,-1.54f),0,0,0,1); //wood
		  entity8 = new Entity(staticModel2, new Vector3f(6,-1,-5),0,0,0,1); //wheat
		  entity9 = new Entity(staticModel3, new Vector3f(-3,-1,-10.2f),0,0,0,1); //brick
		  entity10 = new Entity(staticModel4, new Vector3f(6,-1,-8.46f),0,0,0,1); //sheep
		    entity11 = new Entity(staticModel5, new Vector3f(3,-1,-10.19f),0,0,0,1); //rock
		entity12 = new Entity(staticModel6, new Vector3f(0,-1,-11.92f),0,0,0,1); //desert
		
		entity13 = new Entity(staticModel1, new Vector3f(-6,-1,-8.46f),0,0,0,1); //wood
		  entity14 = new Entity(staticModel2, new Vector3f(-6,-1,-5),0,0,0,1); //wheat
		  entity15 = new Entity(staticModel3, new Vector3f(6,-1,-1.53f),0,0,0,1); //brick 
		  entity16 = new Entity(staticModel4, new Vector3f(-6,-1,-1.53f),0,0,0,1); //sheep
		    entity17 = new Entity(staticModel5, new Vector3f(3,-1,0.19f),0,0,0,1); //rock
		entity18 = new Entity(staticModel5, new Vector3f(-3,-1,0.19f),0,0,0,1); //rock
		entity19 = new Entity(staticModel2, new Vector3f(0,-1,1.92f),0,0,0,1); //wheat
		
		//Camera
		camera = new Camera();
		
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
		
			camera.position.y = 2.5f;
			camera.position.z = 6.0f;
			camera.setPitch(-4);
		while(!Display.isCloseRequested()){	
			
			camera.move();
			renderer.prepare();
	
			shader.start();
			shader.loadViewMatrix(camera);
			
			//camera.position.x +=0.0005f;
			//camera.setYaw(camera.getYaw() - .003f );
			
			renderer.render(entity1,shader);
			renderer.render(entity2,shader);
			renderer.render(entity3,shader);
			renderer.render(entity4,shader);
			renderer.render(entity5,shader);
			renderer.render(entity6,shader);
			renderer.render(entity7,shader);
			renderer.render(entity8,shader);
			renderer.render(entity9,shader);
			renderer.render(entity10, shader);
			renderer.render(entity11, shader);
			renderer.render(entity12, shader);
			renderer.render(entity13, shader);
			renderer.render(entity14, shader);
			renderer.render(entity15,shader);
			renderer.render(entity16, shader);
			renderer.render(entity17, shader);
			renderer.render(entity18, shader);
			renderer.render(entity19, shader);
			shader.stop();
			Window.updateDisplay();
		}
		
	}

	private void init(){
	}
}
