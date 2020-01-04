package entities;

import org.lwjgl.util.vector.Vector3f;

import models.ModelContainer;
import models.RawModel;
import models.TexturedModel;
import rendererEngine.EntityRenderer;
import textures.ModelTexture;
import toolbox.Maths;

public class Entity extends Transformable implements ModelContainer {
	
	private static Vector3f WIND_ORIGIN = new Vector3f();
	private float WIND_FORCE = 1f;

	private float WIND_Q = 0.01f;

	private TexturedModel model;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
		super (position, rotation, scale);
		this.model = model;
		
		initWind();
		
		this.initializeModelContainer();
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
	
	public void processWind ()
	{
		WIND_FORCE += WIND_Q;
	}
	
	public void initWind ()
	{
		WIND_FORCE = Maths.randomFloat(0, 10f);
	}
	
	public static Vector3f getWIND_ORIGIN() {
		return WIND_ORIGIN;
	}

	public static void setWIND_ORIGIN(Vector3f wIND_ORIGIN) {
		WIND_ORIGIN = wIND_ORIGIN;
	}

	public Vector3f getWIND_DIRECTION(float windForce) {
		windForce *= (float)Math.sin(WIND_FORCE);
		return new Vector3f (WIND_ORIGIN.x * windForce, WIND_ORIGIN.y * windForce, WIND_ORIGIN.z * windForce);
	}
	
	public void setWIND_FORCE(float wIND_FORCE) {
		WIND_FORCE = wIND_FORCE;
	}
}
