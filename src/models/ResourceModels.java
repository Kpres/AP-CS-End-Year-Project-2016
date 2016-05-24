package models;

import render.Loader;
import render.OBJLoader;
import world.Resource;
import textures.ModelTexture;

public class ResourceModels {
	
	private static TexturedModel[] resourceModels;
	
	public static final int WOOD_MODEL = 0;
	public static final int WHEAT_MODEL = 1;
	public static final int BRICK_MODEL = 2;
	public static final int SHEEP_MODEL = 3;
	public static final int ROCK_MODEL = 4;
	public static final int DESERT_MODEL = 5;
	
	public static void createModels(Loader loader) {
		RawModel woodHex = OBJLoader.loadObjModel("hex", loader);
		RawModel wheatHex = OBJLoader.loadObjModel("hex", loader);
		RawModel brickHex = OBJLoader.loadObjModel("hex", loader);
		RawModel sheepHex = OBJLoader.loadObjModel("hex", loader);
		RawModel rockHex = OBJLoader.loadObjModel("hex", loader);
		RawModel desertHex = OBJLoader.loadObjModel("hex", loader);
		
		TexturedModel[] models = {
				new TexturedModel (woodHex, new ModelTexture(loader.loadTexture("Wood"))),
				new TexturedModel (wheatHex, new ModelTexture(loader.loadTexture("Wheat"))),
				new TexturedModel (brickHex, new ModelTexture(loader.loadTexture("Brick"))),
				new TexturedModel (sheepHex, new ModelTexture(loader.loadTexture("Sheep"))),
				new TexturedModel (rockHex, new ModelTexture(loader.loadTexture("Rock"))),
				new TexturedModel (desertHex, new ModelTexture(loader.loadTexture("Desert")))
			};
		resourceModels = models;
	}
	
	public static TexturedModel getModel(int modelType) {
		return resourceModels[modelType];
	}
}
	
	
	
