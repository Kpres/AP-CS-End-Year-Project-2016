package world;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.GameEngine;
import entities.Camera;
import entities.City;
import entities.Entity;
import entities.Resource;
import entities.Road;
import input.MousePicker;
import models.ModelAssets;
import render.Renderer;
import shaders.StaticShader;

public class World {
    
	public final static int rings = 2;
    public final static double SIDE_LENGTH = 2;
    public final static double RADIUS = SIDE_LENGTH / 2 * Math.sqrt(3);
    private static final double[] xPositions = new double[numResources(rings)];
    private static final double[] yPositions = new double[numResources(rings)];
    
    Camera camera;
    MousePicker mousePicker;
    
    List<Resource> resources;
    List<Entity> entities;
    PieceManager pieceManager;
    
    public World(GameEngine gameEngine) {
    	setPositions();
    	
    	camera = new Camera();
    	camera.position.y = 5.5f;
		camera.position.z = 6.0f;
    	mousePicker = new MousePicker(camera, gameEngine.getRenderer().getProjectionMatrix());
    	
    	resources = new ArrayList<Resource>();
    	entities = new ArrayList<Entity>();
        pieceManager = new PieceManager();
        
        shuffleResources();
        createPieces();
    }
    
    public World(GameEngine gameEngine, List<Resource> resources) {
    	setPositions();
    	
    	camera = new Camera();
    	camera.position.y = 5.5f;
		camera.position.z = 6.0f;
    	mousePicker = new MousePicker(camera, gameEngine.getRenderer().getProjectionMatrix());
    	
        this.resources = resources;
        entities = new ArrayList<Entity>();
        pieceManager = new PieceManager();
        
        shuffleResources();
        createPieces();
    }
    
    private static void setPositions() {
    	double x = 0;
    	double y = -rings * 2 * RADIUS;
        
    	int i = 0;
    	for (int currentRing = rings; currentRing >= 0; currentRing--) {
    		if (currentRing == 0) {
    			xPositions[i] = 0;
    			yPositions[i] = 0;
    		} else {
    			int resLeft = currentRing * 6;

    			while (resLeft > 0) {
	                xPositions[i] = x;
	                yPositions[i] = y;
	                resLeft--;
	                i++;
	                
	                if (currentRing * 6 - resLeft <= currentRing) {
	                    x += 1.5 * SIDE_LENGTH;
	                    y += RADIUS;
	                } else if (currentRing * 6 - resLeft <= 2 * currentRing) {
	                    y += 2 * RADIUS;
	                } else if (currentRing * 6 - resLeft <= 3 * currentRing) {
	                    x -= 1.5 * SIDE_LENGTH;
	                    y += RADIUS;
	                } else if (currentRing * 6 - resLeft <= 4 * currentRing) {
	                	x -= 1.5 * SIDE_LENGTH;
	                	y -= RADIUS;
	                } else if (currentRing * 6 - resLeft <= 5 * currentRing) {
	                    y -= 2 * RADIUS;
	                } else if (currentRing * 6 - resLeft < 6 * currentRing) {
	                	x += 1.5 * SIDE_LENGTH;
	                	y -= RADIUS;
	                } else {
	                	x += 1.5 * SIDE_LENGTH;
	                	y += RADIUS;
	                }
	                
	                if (Math.abs(y) < 0.0001) y = 0;	// Handle bit loss;
    			}
            }
        }
    }
    
    public static Vector2f getPosition(int resourceIndex) {
    	Vector2f pos = new Vector2f();
    	pos.x = (float) xPositions[resourceIndex];
    	pos.y = (float) yPositions[resourceIndex];
    	return pos;
    }
     
    public void shuffleResources() {
    	for (int i = 0; i < resources.size(); i++) {
    		int random = (int) (Math.random() * (resources.size() - i)) + i;
    		resources.add(i, resources.remove(random));
    	}
    	
    	entities.clear();
    	for (int i = 0; i < resources.size(); i++) {
    		Vector2f pos = World.getPosition(i);
			entities.add(new Entity(ModelAssets.getModel(resources.get(i).getId()), new Vector3f(pos.x, 0, pos.y), 0, 0, 0, 1));
    	}
    }
    
    private void createPieces() {
    	float x = 8;
    	float z = -8;
    	for (int i = 0; i < 10; i++) {
    		Road road = new Road();
    		Settlement settlement = new Settlement();
    		City city = new City();
    		
    		road.setPos(new Vector3f(x, 0, z));
    		z--;
    		settlement.setPos(new Vector3f(x, 0, z));
    		z--;
    		city.setPos(new Vector3f(x, 0, z));
    		z--;
    		x++;
    		
	    	pieceManager.addPiece(road);
	    	pieceManager.addPiece(settlement);
	    	pieceManager.addPiece(city);
    	}
    }

    public static int numResources(int rings) {
        int sum = 1;
         
        for (int i = 1; i <= rings; i++) {
            sum += 6 * i;
        }
         
        return sum;
    }
    
    public void addResource(Resource resource) {
        resources.add(resource);
    }
    
    public Resource getResource(int index) {
        return resources.get(index);
    }
    
    public Resource removeResource(int index) {
        return resources.remove(index);
    }
    
    public void update() {
    	camera.move();
    	
    	mousePicker.update();
		Display.setTitle(mousePicker.getRay() + "");
		
		pieceManager.update(mousePicker, camera);
    }
    
    public void render(Renderer renderer, StaticShader shader) {
    	for (Entity e : entities) renderer.render(e, shader, camera);
    	pieceManager.render(renderer, shader, camera);
    }
}