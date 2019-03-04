package models;

import org.lwjgl.util.vector.Vector3f;

import objConverter.ModelData;
import objConverter.OBJFileLoader;
import rendererEngine.Loader;
import textures.ModelTexture;

public class TexturedModel implements ModelContainer {
	
	private RawModel rawModel;
	private ModelTexture texture;
	
	public TexturedModel (String modelName, String textureName)
	{
		ModelData data = OBJFileLoader.loadOBJ(modelName);
		RawModel model = Loader.getLoader().loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
		
		ModelTexture loadedTexture = new ModelTexture(Loader.getLoader().loadTexture(textureName));
		
		
		this.rawModel = model;
		this.texture = loadedTexture;
	}
	
	public TexturedModel (RawModel rawModel, ModelTexture texture)
	{
		this.rawModel = rawModel;
		this.texture = texture;
	}
	
	public void initializeModelContainer ()
	{
		// nothing
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
