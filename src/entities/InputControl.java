package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import rendererEngine.DisplayManager;

public class InputControl {
	
	static
	{
		ButtonStateControl[] bcs = new ButtonStateControl [3];
		
		for (int i = 0; i < bcs.length; i++)
		{
			bcs[i] = new ButtonStateControl ();
		}
		
		mouse = bcs;
	}
	
	
	private static ButtonStateControl[] mouse;
	private static Vector3f MOVE_INPUT = new Vector3f();
	private static Vector3f CAMERA_INPUT = new Vector3f();
	
	
	public static Vector3f getMOVE_INPUT() {
		return MOVE_INPUT;
	}
	public static Vector3f getCAMERA_INPUT() {
		return CAMERA_INPUT;
	}
	public static void inputUpdate ()
	{
		updateMouse();
		updateControls();
	}
	private static void updateMouse ()
	{
		for (int i = 0; i < mouse.length; i++)
		{
			mouse[i].update(Mouse.isButtonDown(i));
		}
	}
	public static int getMouseButtonState (int button)
	{
		return mouse[button].getCurrentState();
	}
	private static void updateControls ()
	{
		Vector3f delta = new Vector3f();
		float move_Speed = 1f;
		
		Vector3f deltaRot = new Vector3f();
		float rotate_speed = 1f;
		
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
		
		MOVE_INPUT = delta;
		CAMERA_INPUT = deltaRot;
	}
}






