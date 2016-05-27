package world;

import org.lwjgl.util.vector.Vector3f;

import engine.Updatable;
import entities.Entity;

/**
 * Abstract class GamePiece - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class GamePiece implements Updatable
{
	private final int id;
	
	private boolean grabbed = false;
	
    protected Vector3f position;
    protected float rx;
    protected float ry;
    protected float rz;
    
    public GamePiece(int id) {
    	this.id = id;
        position = new Vector3f(0, 0, 0);
    }
    
    public boolean isGrabbed() {
    	return grabbed;
    }
    
    public void setGrab(boolean grabbed) {
    	this.grabbed = grabbed;
    }
    
    public int getId() {
    	return id;
    }
    
    public void setPos(Vector3f position) {
        this.position = position;
    }

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return rx;
	}

	public void setPitch(float angle) {
		this.rx = angle;
	}

	public float getYaw() {
		return ry;
	}

	public void setYaw(float angle) {
		this.ry = angle;
	}

	public float getRoll() {
		return rz;
	}

	public void setRoll(float angle) {
		this.rz = angle;
	}
}
