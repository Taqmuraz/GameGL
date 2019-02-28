package entities;

import org.lwjgl.util.vector.Vector3f;

import models.ModelContainer;
import models.RawModel;
import models.TexturedModel;
import textures.ModelTexture;

public class Entity extends Transformable implements ModelContainer {

	private TexturedModel model;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
		super (position, rotation, scale);
		this.model = model;
	}

	public TexturedModel getTexturedModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}
	
	public RawModel getRawModel ()
	{
		return model.getRawModel();
	}
	public ModelTexture getTexture ()
	{
		return model.getTexture();
	}
}
