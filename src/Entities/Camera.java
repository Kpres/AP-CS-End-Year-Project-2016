package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	public Vector3f position = new Vector3f(0,0,0);
	private float pitch = -5;
	private float yaw;
	private float roll;
	

	public Camera() {}
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z+= -0.02f * Math.cos(Math.toRadians(yaw));
			position.x += 0.02f * Math.sin(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x+=0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x+=-0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			position.y += 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
			position.y += -0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			setPitch(getPitch() - .5f);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			setPitch(getPitch() + .5f);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			setYaw(getYaw() - .5f);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			setYaw(getYaw() + .5f);
		}
	}


	public Vector3f getPosition() {
		return position;
	}


	public void setPosition(Vector3f position) {
		this.position = position;
	}


	public float getPitch() {
		return pitch;
	}


	public void setPitch(float pitch) {
		this.pitch = pitch;
	}


	public float getYaw() {
		return yaw;
	}


	public void setYaw(float yaw) {
		this.yaw = yaw;
	}


	public float getRoll() {
		return roll;
	}


	public void setRoll(float roll) {
		this.roll = roll;
	}
	
}
