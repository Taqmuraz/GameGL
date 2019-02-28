package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera extends Transformable {
	
	public Camera ()
	{
		super (new Vector3f(0f,0f,0f), new Vector3f(0f,0f,0f), 1f);
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
			delta.z += move_Speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			delta.z -= move_Speed;
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
		
		if (Mouse.isButtonDown(0))
		{
			deltaRot = new Vector3f (-Mouse.getDY() * rotate_speed, Mouse.getDX() * rotate_speed, 0f);
		}
		
		increaseRotation(deltaRot);
		increasePosition(delta);
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
