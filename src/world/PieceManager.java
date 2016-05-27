package world;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import input.MousePicker;
import models.ResourceModels;
import render.Renderer;
import shaders.StaticShader;

public class PieceManager {
    
    private List<GamePiece> pieces;
    private List<Entity> entities;
    
    private boolean isMouseGrabbing = false;
    
    public PieceManager() {
        pieces = new ArrayList<GamePiece>();
        entities = new ArrayList<Entity>();
    }
    
    public void addPiece(GamePiece piece) {
    	pieces.add(piece);
    	entities.add(new Entity(ResourceModels.getModel(ResourceModels.ROAD_MODEL), piece.getPosition(), piece.getPitch(), piece.getYaw(), piece.getRoll(), 1));
    }
    
    public void update(MousePicker mousePicker, Camera camera) {
    	entities.clear();
    	for (GamePiece p : pieces) {
    		p.update();
    		entities.add(new Entity(ResourceModels.getModel(p.getId()), p.getPosition(), p.getPitch(), p.getYaw(), p.getRoll(), 1));
    		
    		Vector3f ray = mousePicker.getRay();
    		Vector3f cameraPos = camera.position.negate(null);
    		float t = -cameraPos.y/ray.y;
    		Vector3f objPos = p.position;
    		Vector3f hitPos = Vector3f.add(cameraPos, new Vector3f(ray.x * t, ray.y * t, ray.z * t), null);
    		float hitRadius = 1.0f;

    		if (p.isGrabbed()) {
    			p.setPos(new Vector3f(-hitPos.x, -hitPos.y, -hitPos.z));
    		}
    		
    		if (Mouse.isButtonDown(0)) {
    			if (Math.abs(hitPos.x + objPos.x) <= hitRadius && Math.abs(hitPos.z + objPos.z) <= hitRadius) {
	    			if (!isMouseGrabbing) {
	    				p.setGrab(true);
	    				isMouseGrabbing = true;
	    			}
    			} else {
    				p.setGrab(false);
    			}
    		} else {
    			isMouseGrabbing = false;
    			p.setGrab(false);
    		}
    		
    	}
    }

	public void render(Renderer renderer, StaticShader shader, Camera camera) {
		for (Entity e : entities) renderer.render(e, shader, camera);
	}
}