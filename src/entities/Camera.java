package entities;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import rendererEngine.DisplayManager;

public class Camera extends Transformable {
	
	private static Camera MAIN_CAMERA;
	
	public static Camera getMAIN_CAMERA() {
		return MAIN_CAMERA;
	}

	public static void setMAIN_CAMERA(Camera mAIN_CAMERA) {
		MAIN_CAMERA = mAIN_CAMERA;
	}

	public Camera ()
	{
		super (new Vector3f(0f,0f,0f), new Vector3f(0f,0f,0f), 1f);
		setMAIN_CAMERA(this);
	}
	
	public void move ()
	{
		Vector3f delta = new Vector3f();
		float move_Speed = 0.02f;
		
		Vector3f deltaRot = new Vector3f();
		float rotate_speed = 0.1f;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			move_Speed *= 20f;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			delta.z -= move_Speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			delta.z += move_Speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			delta.x -= move_Speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			delta.x += move_Speed;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			delta.y -= move_Speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E))
		{
			delta.y += move_Speed;
		}
		
		deltaRot = new Vector3f (-Mouse.getDY() * rotate_speed, Mouse.getDX() * rotate_speed, 0f);
		Mouse.setCursorPosition(DisplayManager.WIDTH / 2, DisplayManager.HEIGHT / 2);
		Mouse.poll();
		
		increaseRotation(deltaRot);
		translatePosition(delta);
	}

	public float getPitch() {
		return getRotX();
	}
	public float getYaw() {
		return getRotY();
	}
	public float getRoll() {
		return getRotZ();
	}
}
