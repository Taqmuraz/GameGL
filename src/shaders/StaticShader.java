package shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import entities.Camera;
import entities.Light;
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
	}
	
	public void loadShineVariables (float damper, float reflectivity)
	{
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
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
	public void loadTiling (Vector2f tiling)
	{
		super.loadVector2(location_tiling, tiling);
	}
	public void loadLight (Light light)
	{
		super.loadFloat(location_ambienceIntencivity, AMBIENCE_INTENCIVITY);
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColor, light.getColor());
	}
}







