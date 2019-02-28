package textures;

import org.lwjgl.util.vector.Vector2f;

public class ModelTexture {
	
	private int textureID;
	
	private float shineDamper = 1f;
	private float reflectivity = 0f;
	
	private Vector2f tiling;
	
	public ModelTexture (int id)
	{
		this (id, new Vector2f(1f,1f));
	}
	
	public ModelTexture (int id, Vector2f tiling)
	{
		this.tiling = tiling;
		this.textureID = id;
	}
	public Vector2f getTiling() {
		return tiling;
	}

	public int getTextureID ()
	{
		return textureID;
	}
	public float getShineDampen() {
		return shineDamper;
	}
	public void setShineDampen(float shineDampen) {
		this.shineDamper = shineDampen;
	}
	public float getReflectivity() {
		return reflectivity;
	}
	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}
}
