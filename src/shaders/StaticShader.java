package shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.ModelContainer;
import textures.ModelTexture;
import toolbox.Maths;

public class StaticShader extends ShaderProgram {

	protected static String VERTEX_FILE;
	protected static String FRAGMENT_FILE;
	
	private static float AMBIENCE_INTENCIVITY = 0.4f;
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColor;
	private int location_shineDamper;
	private int location_reflectivity;
	private int location_ambienceIntencivity;
	private int location_tiling;
	private int location_alphaCutOff;
	private int location_globalNormal;
	private int location_windEffect;
	
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
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColor = super.getUniformLocation("lightColor");
		
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		
		location_ambienceIntencivity = super.getUniformLocation("ambienceIntencivity");
		
		location_tiling = super.getUniformLocation("tiling");
		
		location_alphaCutOff = super.getUniformLocation("alphaCutOff");
		
		location_windEffect = super.getUniformLocation("windEffect");
	}
	
	public void loadTransformationMatrix (Matrix4f matrix)
	{
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	public void loadProjectionMatrix (Matrix4f projection)
	{
		super.loadMatrix(location_projectionMatrix, projection);
	}
	public void loadViewMatrix (Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	public void loadLight (Light light)
	{
		super.loadFloat(location_ambienceIntencivity, AMBIENCE_INTENCIVITY);
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColor, light.getColor());
	}
	public void loadTextureParams (ModelTexture texture)
	{
		super.loadBoolean(location_globalNormal, texture.isEnableGlobalNormal());
		super.loadFloat(location_alphaCutOff, texture.getAlphaCutOff());
		super.loadVector2(location_tiling, texture.getTiling());
		super.loadFloat(location_shineDamper, texture.getShineDampen());
		super.loadFloat(location_reflectivity, texture.getReflectivity());
	}
	public void loadWind (ModelContainer modelContainer) 
	{
		super.loadVector(location_windEffect, modelContainer.getWIND_DIRECTION(modelContainer.getTexture().getWindEffect()));
	}
}


 




