package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light extends Transformable {
	
	private static Light MAIN_LIGHT;

	private Vector3f color;
	
	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public Light(Vector3f position, Vector3f color) {
		super(position, new Vector3f(), 1f);
		this.color = color;
		setMAIN_LIGHT(this);
	}

	public static Light getMAIN_LIGHT() {
		return MAIN_LIGHT;
	}

	private static void setMAIN_LIGHT(Light mAIN_LIGHT) {
		MAIN_LIGHT = mAIN_LIGHT;
	}
}
