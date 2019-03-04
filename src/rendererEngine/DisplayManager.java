package rendererEngine;

import java.awt.Cursor;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	private static final int FPS_CAP = 120;
	
	public static void createDisplay ()
	{
		ContextAttribs attribs = new ContextAttribs (3, 2);
		attribs.withForwardCompatible(true);
		attribs.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("Game GL");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		
		startMouse();
	}
	private static void startMouse ()
	{
		try {
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		Mouse.setGrabbed(true);
	}
	private static void endMouse ()
	{
		Mouse.destroy();
	}
	public static void updateDisplay ()
	{
		Display.sync(FPS_CAP);
		Display.update();
	}
	public static void closeDisplay ()
	{
		endMouse();
		Display.destroy();
	}
}
