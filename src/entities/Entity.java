package entities;

import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;

public class Entity extends Transformable {

	private TexturedModel model;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
		super (position, rotation, scale);
		this.model = model;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}
}
