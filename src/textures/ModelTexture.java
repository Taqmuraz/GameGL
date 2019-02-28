package textures;

public class ModelTexture {
	
	private int textureID;
	
	private float shineDamper = 1f;
	private float reflectivity = 0f;
	
	public ModelTexture (int id)
	{
		this.textureID = id;
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
