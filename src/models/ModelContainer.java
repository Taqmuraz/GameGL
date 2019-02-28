package models;

import org.lwjgl.util.vector.Vector3f;

import textures.ModelTexture;

public interface ModelContainer {
	RawModel getRawModel ();
	ModelTexture getTexture ();
	TexturedModel getTexturedModel();
	void processWind();
	Vector3f getWIND_DIRECTION(float windForce);
}
