package textures;

import org.lwjgl.util.vector.Vector2f;

import rendererEngine.Loader;

public class ModelTexture {
	
	private int textureID;
	
	private float shineDamper = 1f;
	private float reflectivity = 0f;
	
	private boolean enableCulling = true;
	private boolean enableGlobalNormal = false;
	private float alphaCutOff = 0.5f;
	private float windEffect = 0f;
	private float waterEffect = 0f;
	
	
	private Vector2f offset = new Vector2f(0f, 0f);
	private Vector2f tiling = new Vector2f (1f, 1f);
	
	public ModelTexture (String name)
	{
		this(Loader.getLoader().loadTexture(name));
	}
	public ModelTexture (String name, Vector2f tiling)
	{
		this(Loader.getLoader().loadTexture(name), tiling);
	}
	
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
	public boolean isEnableCulling() {
		return enableCulling;
	}

	public void setEnableCulling(boolean enableCulling) {
		this.enableCulling = enableCulling;
	}
	
	public float getAlphaCutOff() {
		return alphaCutOff;
	}

	public void setAlphaCutOff(float alphaCutOff) {
		this.alphaCutOff = alphaCutOff;
	}

	public boolean isEnableGlobalNormal() {
		return enableGlobalNormal;
	}

	public void setEnableGlobalNormal(boolean enableGlobalNormal) {
		this.enableGlobalNormal = enableGlobalNormal;
	}

	public float getWindEffect() {
		return windEffect;
	}

	public void setWindEffect(float windEffect) {
		this.windEffect = windEffect;
	}

	public Vector2f getOffset() {
		return offset;
	}
	public void setOffset(Vector2f offset) {
		this.offset = offset;
	}
	public float getWaterEffect() {
		return waterEffect;
	}
	public void setWaterEffect(float waterEffect) {
		this.waterEffect = waterEffect;
	}
}
