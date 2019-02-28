package rendererEngine;

import shaders.StaticShader;
import shaders.TerrainShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.ModelContainer;
import models.TexturedModel;

public class MasterRenderer {
	
	private static final float FOV = 70f;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000f;
	
	private Matrix4f projectionMatrix;

	private List<RendererContainer> renderers = new ArrayList<RendererContainer> ();
	
	private Map<ModelContainer, List<ModelContainer>> entities = new HashMap<ModelContainer, List<ModelContainer>>();
	
	public MasterRenderer ()
	{
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		createProjectionMatrix ();
		
		StaticShader entitiesShader = new StaticShader();
		TerrainShader terrainShader = new TerrainShader();
		
		renderers.add(new RendererContainer(new EntityRenderer(entitiesShader, projectionMatrix), entitiesShader));
		renderers.add(new RendererContainer(new TerrainRenderer(terrainShader, projectionMatrix), terrainShader));
	}
	
	public void render (Light sun, Camera camera)
	{
		prepare();
		for (RendererContainer renderer : renderers)
		{
			
			renderer.getShader().start();
			renderer.getShader().loadLight(sun);
			renderer.getShader().loadViewMatrix(camera);
			
			renderer.getRenderer().render(entities);
			
			renderer.getShader().stop();
		}
		entities.clear();
	}
	
	public void processModelContainer (ModelContainer entity)
	{
		TexturedModel entityModel = entity.getTexturedModel();
		List<ModelContainer> batch = entities.get(entityModel);
		
		if (batch == null)
		{
			batch = new ArrayList<ModelContainer>();
			entities.put(entityModel, batch);
		}
		batch.add(entity);
	}
	
	public static void setCulling (boolean enable)
	{
		if (enable)
		{
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glCullFace(GL11.GL_BACK);
		} else {
			GL11.glDisable(GL11.GL_CULL_FACE);
		}
	}
	
	public void cleanUp ()
	{
		for (RendererContainer renderer : renderers)
		{
			renderer.getShader().cleanUp();
		}
	}
	
	public void prepare ()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.7f, 0.7f, 1.0f, 1);
	}
	
	private void createProjectionMatrix ()
	{
		float aspectRatio = ((float)Display.getDisplayMode().getWidth() / (float)Display.getDisplayMode().getHeight());
		float y_scale = 1f / (float) Math.tan(Math.toRadians(FOV / 2f));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.setIdentity();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1f;
		projectionMatrix.m32 = -((2f * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0f;
	}
}
