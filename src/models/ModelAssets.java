package models;

import render.Loader;
import render.OBJLoader;
import textures.ModelTexture;

public class ModelAssets {
	
	private static TexturedModel[] resourceModels;
	
	public static final int WOOD_MODEL = 0;
	public static final int WHEAT_MODEL = 1;
	public static final int BRICK_MODEL = 2;
	public static final int SHEEP_MODEL = 3;
	public static final int ROCK_MODEL = 4;
	public static final int DESERT_MODEL = 5;
	public static final int ROAD_MODEL = 6;
	public static final int VILLAGE_MODEL = 7;
	public static final int CITY_MODEL = 8;
	
	public static void createModels(Loader loader) {
		RawModel woodHex = OBJLoader.loadObjModel("wood", loader);
		RawModel wheatHex = OBJLoader.loadObjModel("wheat", loader);
		RawModel brickHex = OBJLoader.loadObjModel("clay", loader);
		RawModel sheepHex = OBJLoader.loadObjModel("sheep", loader);
		RawModel rockHex = OBJLoader.loadObjModel("mountain", loader);
		RawModel desertHex = OBJLoader.loadObjModel("desert", loader);
		RawModel road = OBJLoader.loadObjModel("road", loader);
		RawModel village = OBJLoader.loadObjModel("village", loader);
		RawModel city = OBJLoader.loadObjModel("city", loader);
		
		TexturedModel[] models = {
				new TexturedModel (woodHex, new ModelTexture(loader.loadTexture("Wood"))),
				new TexturedModel (wheatHex, new ModelTexture(loader.loadTexture("Wheat"))),
				new TexturedModel (brickHex, new ModelTexture(loader.loadTexture("Brick"))),
				new TexturedModel (sheepHex, new ModelTexture(loader.loadTexture("Sheep"))),
				new TexturedModel (rockHex, new ModelTexture(loader.loadTexture("Rock"))),
				new TexturedModel (desertHex, new ModelTexture(loader.loadTexture("Desert"))),
				new TexturedModel (road, new ModelTexture(loader.loadTexture("piece"))),
				new TexturedModel (village, new ModelTexture(loader.loadTexture("piece"))),
				new TexturedModel (city, new ModelTexture(loader.loadTexture("piece")))
			};
		
		resourceModels = models;
	}
	
	public static TexturedModel getModel(int modelType) {
		return resourceModels[modelType];
	}
}
	
	
	
