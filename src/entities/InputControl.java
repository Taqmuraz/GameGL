package entities;

import org.lwjgl.input.Mouse;

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
	
	
	public static void inputUpdate ()
	{
		updateMouse();
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
}






