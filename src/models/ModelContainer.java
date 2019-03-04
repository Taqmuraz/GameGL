package models;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import textures.ModelTexture;

public interface ModelContainer {
	RawModel getRawModel ();
	ModelTexture getTexture ();
	TexturedModel getTexturedModel();
	void processWind();
	Vector3f getWIND_DIRECTION(float windForce);
	
	static final List<ModelContainer> modelContainers = new ArrayList<ModelContainer>();
	
	default void initializeModelContainer ()
	{
		modelContainers.add(this);
	}
}
