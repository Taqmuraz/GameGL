package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light extends Transformable {

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
	}
}
