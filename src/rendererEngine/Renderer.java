package rendererEngine;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import shaders.StaticShader;
import textures.ModelTexture;
import toolbox.Maths;

public class Renderer {
	
	private static final float FOV = 70f;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000f;
	
	public static final int ATTRIBUTES_COUNT = 3;
	
	private Matrix4f projectionMatrix;
	private StaticShader shader;
	
	public Renderer (StaticShader shader)
	{
		this.shader = shader;
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void prepare ()
	{
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.5f, 0.5f, 0.5f, 1);
	}
	
	public void render (Map<TexturedModel, List<Entity>> entities)
	{
		for (TexturedModel model : entities.keySet())
		{
			prepareTexturedModel (model);
			List<Entity> batch = entities.get(model);
			for (Entity entity : batch)
			{
				prepareInstance (entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel ();
		}
	}
	
	private void prepareTexturedModel (TexturedModel texturedModel)
	{
		RawModel model = texturedModel.getRawModel();
		GL30.glBindVertexArray(model.getVaoID());
		
		int attributesCount = 3;
		
		for (int i = 0; i < attributesCount; i++)
		{
			GL20.glEnableVertexAttribArray(i);
		}
		
		ModelTexture texture = texturedModel.getTexture();
		
		shader.loadShineVariables(texture.getShineDampen(), texture.getReflectivity());
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
	}
	
	private void unbindTexturedModel ()
	{
		for (int i = 0; i < ATTRIBUTES_COUNT; i++)
		{
			GL20.glDisableVertexAttribArray(i);
		}
		GL30.glBindVertexArray(0);
	}
	
	private void prepareInstance (Entity entity)
	{
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotation(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
	}
	private void createProjectionMatrix ()
	{
		float aspectRatio = ((float)Display.getWidth() / (float)Display.getHeight());
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV * 0.5f))) * aspectRatio);
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












