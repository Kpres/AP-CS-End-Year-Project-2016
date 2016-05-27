package input;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import entities.Camera;
import toolbox.Maths;

public class MousePicker {
	
	private Vector3f ray;
	
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;
	private Camera camera;
	
	public MousePicker(Camera camera, Matrix4f projection) {
		this.camera = camera;
		this.projectionMatrix = projection;
		this.viewMatrix = Maths.createViewMatrix(camera);
	}
	
	public Vector3f getRay() {
		return ray;
	}
	
	public void update() {
		viewMatrix = Maths.createViewMatrix(camera);
		ray = calculateRay();
	}
	
	public Vector3f calculateRay()  {
		float x = (2.0f * Mouse.getX()) / Display.getWidth() - 1.0f;
		float y = (2.0f * Mouse.getY()) / Display.getHeight() - 1.0f;
		Vector4f clipRay = new Vector4f(x, y, -1.0f, 1.0f);
		
		Matrix4f inverseProjection = Matrix4f.invert(projectionMatrix, null);
		Vector4f eyeRay = Matrix4f.transform(inverseProjection, clipRay, null); 
		eyeRay.z = -1.0f;
		eyeRay.w = 0.0f;
		
		Matrix4f inverseView = Matrix4f.invert(viewMatrix, null);
		Vector4f worldRay = Matrix4f.transform(inverseView, eyeRay, null);
		Vector3f normalized = new Vector3f(worldRay.x, worldRay.y, worldRay.z);
		normalized.normalise(null);
		return normalized;
	}
	
	public static boolean checkHit(Vector3f objectPos, float radius, Vector3f rayNormal, Vector3f cameraPos) {
		Vector3f dif = Vector3f.sub(cameraPos, objectPos, null);
    	float b = Vector3f.dot(rayNormal, dif);
    	float test = rayNormal.x * dif.x + rayNormal.y * dif.y + rayNormal.z * dif.z;
    	float c = Vector3f.dot(dif, dif) - radius * radius;
    	
    	System.out.println(test + "\t" + b + "\t" + c + "\t" + (-b * b + c));
    	if (- b * b + c >= 0)
    		return true;
    	else
    		return false;
	}
}
