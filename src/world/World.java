package world;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import shaders.StaticShader;
import entities.Entity;
import models.ResourceModels;
import render.Renderer;

public class World {
    
	public final static int rings = 2;
    public final static double SIDE_LENGTH = 2;
    public final static double RADIUS = SIDE_LENGTH / 2 * Math.sqrt(3);
    private static final double[] xPositions = new double[numResources(rings)];
    private static final double[] yPositions = new double[numResources(rings)];
    
    List<Resource> resources;
    List<Entity> entities;
    PieceManager pieceManager;
    
    public World() {
    	setPositions();
    	
    	resources = new ArrayList<Resource>();
    	entities = new ArrayList<Entity>();
        pieceManager = new PieceManager();
        
        shuffleResources();
    }
    
    public World(List<Resource> resources) {
    	setPositions();
    	
        this.resources = resources;
        entities = new ArrayList<Entity>();
        pieceManager = new PieceManager();
        
        shuffleResources();
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
			entities.add(new Entity(ResourceModels.getModel(resources.get(i).getId()), new Vector3f(pos.x, 0, pos.y), 0, 0, 0, 1));
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
    
    public void render(Renderer renderer, StaticShader shader) {
    	for (Entity e : entities) renderer.render(e, shader);
    }
}