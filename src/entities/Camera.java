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
