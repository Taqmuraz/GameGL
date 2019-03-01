package shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.ModelContainer;
import rendererEngine.MasterRenderer;
import textures.ModelTexture;
import toolbox.Maths;

public class StaticShader extends ShaderProgram {

	protected static String VERTEX_FILE;
	protected static String FRAGMENT_FILE;
	
	private static float AMBIENCE_INTENCIVITY = 0.4f;
	
	private UniformContainer location_transformationMatrix;
	private UniformContainer location_projectionMatrix;
	private UniformContainer location_viewMatrix;
	private UniformContainer location_lightPosition;
	private UniformContainer location_lightColor;
	private UniformContainer location_shineDamper;
	private UniformContainer location_reflectivity;
	private UniformContainer location_ambienceIntencivity;
	private UniformContainer location_tiling;
	private UniformContainer location_alphaCutOff;
	private UniformContainer location_globalNormal;
	private UniformContainer location_windEffect;

	private UniformContainer location_fogDencity;
	private UniformContainer location_fogGradient;
	private UniformContainer location_enableFog;
	private UniformContainer location_skyColor;
	
	static
	{
		VERTEX_FILE = "src/shaders/vertexShader.txt";
		FRAGMENT_FILE = "src/shaders/fragmentShader.txt";
	}

	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	public static void setAmbienceIntencivity (float value)
	{
		AMBIENCE_INTENCIVITY = value;
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = new UniformContainer(this, "transformationMatrix");
		location_projectionMatrix = new UniformContainer(this, "projectionMatrix");
		location_viewMatrix = new UniformContainer(this, "viewMatrix");
		location_lightPosition = new UniformContainer(this, "lightPosition");
		location_lightColor = new UniformContainer(this, "lightColor");
		location_shineDamper = new UniformContainer(this, "shineDamper");
		location_reflectivity = new UniformContainer(this, "reflectivity");
		location_ambienceIntencivity = new UniformContainer(this, "ambienceIntencivity");
		location_tiling = new UniformContainer(this, "tiling");
		location_alphaCutOff = new UniformContainer(this, "alphaCutOff");
		location_globalNormal = new UniformContainer(this, "globalNormal");
		location_windEffect = new UniformContainer(this, "windEffect");
		
		location_fogDencity = new UniformContainer(this, "fogDencity");
		location_fogGradient = new UniformContainer(this, "fogGradient");
		location_enableFog = new UniformContainer(this, "enableFog");
		location_skyColor = new UniformContainer(this, "skyColor");
		
		for (UniformContainer uc : UniformContainer.getForShader(this))
		{
			uc.Initialize();
		}
	}
	
	public void loadTransformationMatrix (Matrix4f matrix)
	{
		super.loadMatrix(location_transformationMatrix.getLocation(), matrix);
	}
	public void loadProjectionMatrix (Matrix4f projection)
	{
		super.loadMatrix(location_projectionMatrix.getLocation(), projection);
	}
	public void loadViewMatrix (Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix.getLocation(), viewMatrix);
	}
	public void loadLight (Light light)
	{
		super.loadFloat(location_ambienceIntencivity.getLocation(), AMBIENCE_INTENCIVITY);
		super.loadVector(location_lightPosition.getLocation(), light.getPosition());
		super.loadVector(location_lightColor.getLocation(), light.getColor());
		
		super.loadFloat(location_fogDencity.getLocation(), MasterRenderer.getFOG_DENCITY());
		super.loadFloat(location_fogGradient.getLocation(), MasterRenderer.getFOG_GRADIENT());
		super.loadBoolean(location_enableFog.getLocation(), MasterRenderer.isENABLE_FOG());
		super.loadVector(location_skyColor.getLocation(), MasterRenderer.getSKY_COLOR());
	}
	public void loadTextureParams (ModelTexture texture)
	{
		super.loadBoolean(location_globalNormal.getLocation(), texture.isEnableGlobalNormal());
		super.loadFloat(location_alphaCutOff.getLocation(), texture.getAlphaCutOff());
		super.loadVector2(location_tiling.getLocation(), texture.getTiling());
		super.loadFloat(location_shineDamper.getLocation(), texture.getShineDampen());
		super.loadFloat(location_reflectivity.getLocation(), texture.getReflectivity());
	}
	public void loadWind (ModelContainer modelContainer) 
	{
		super.loadVector(location_windEffect.getLocation(), modelContainer.getWIND_DIRECTION(modelContainer.getTexture().getWindEffect()));
	}
}


 




