package models;

import org.lwjgl.util.vector.Vector3f;

import textures.ModelTexture;

public class TexturedModel implements ModelContainer {
	
	private RawModel rawModel;
	private ModelTexture texture;
	
	public TexturedModel (RawModel rawModel, ModelTexture texture)
	{
		this.rawModel = rawModel;
		this.texture = texture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}
	
	public TexturedModel getTexturedModel() {
		return this;
	}
	
	public void processWind ()
	{
		
	}
	public Vector3f getWIND_DIRECTION(float windForce)
	{
		return new Vector3f();
	}
}
