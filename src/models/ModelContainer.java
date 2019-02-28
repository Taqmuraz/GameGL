package models;

import textures.ModelTexture;

public interface ModelContainer {
	RawModel getRawModel ();
	ModelTexture getTexture ();
	TexturedModel getTexturedModel();
}
