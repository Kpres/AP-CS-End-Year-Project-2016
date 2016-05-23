package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	public final float speed = 0.05f;
	public final float angSpeed = 0.5f;
	
	public Vector3f position = new Vector3f(0,0,0);
	
	private float pitch = -5;
	private float yaw;
	private float roll;
	

	public Camera() {}
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z += -speed * Math.cos(Math.toRadians(yaw));
			position.x += speed * Math.sin(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x += speed * Math.cos(Math.toRadians(yaw));
			position.z += speed * Math.sin(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x += -speed * Math.cos(Math.toRadians(yaw));
			position.z += -speed * Math.sin(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z += speed * Math.cos(Math.toRadians(yaw));
			position.x += -speed * Math.sin(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			position.y += speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
			position.y += -speed;
		}

		setPitch();
		setYaw();
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


	public void setPitch() {
		float radius = (float) Math.sqrt(position.x * position.x + position.z * position.z);
		this.pitch = (float) Math.toDegrees(Math.atan(position.y / radius));
	}


	public float getYaw() {
		return yaw;
	}


	public void setYaw() {
		if (position.z >= 0) {
			this.yaw = (float) -Math.toDegrees(Math.atan(position.x / position.z));
		} else {
			this.yaw = (float) Math.toDegrees(Math.atan(position.x / -position.z)) - 180;
		}
	}


	public float getRoll() {
		return roll;
	}


	public void setRoll(float roll) {
		this.roll = roll;
	}
	
}
