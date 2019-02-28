package rendererEngine;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import entities.Entity;
import entities.Transformable;
import models.RawModel;
import models.ModelContainer;
import models.TexturedModel;
import shaders.StaticShader;
import textures.ModelTexture;
import toolbox.Maths;

public class EntityRenderer {
	
	public static final int ATTRIBUTES_COUNT = 3;
	private StaticShader shader;
	
	public EntityRenderer (StaticShader shader, Matrix4f projectionMatrix)
	{
		this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void render (Map<ModelContainer, List<ModelContainer>> entities)
	{
		for (ModelContainer model : entities.keySet())
		{
			prepareTexturedModel (model);
			List<ModelContainer> batch = entities.get(model);
			for (ModelContainer entity : batch)
			{
				if (entity instanceof Transformable)
				{
					prepareInstance ((Transformable)entity);
					GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
				}
			}
			unbindTexturedModel ();
		}
	}
	
	private void prepareTexturedModel (ModelContainer modelContainer)
	{
		RawModel model = modelContainer.getRawModel();
		GL30.glBindVertexArray(model.getVaoID());
		
		int attributesCount = 3;
		
		for (int i = 0; i < attributesCount; i++)
		{
			GL20.glEnableVertexAttribArray(i);
		}
		
		ModelTexture texture = modelContainer.getTexture();
		
		shader.loadShineVariables(texture.getShineDampen(), texture.getReflectivity());
		shader.loadTiling(texture.getTiling());
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, modelContainer.getTexture().getTextureID());
	}
	
	private void unbindTexturedModel ()
	{
		for (int i = 0; i < ATTRIBUTES_COUNT; i++)
		{
			GL20.glDisableVertexAttribArray(i);
		}
		GL30.glBindVertexArray(0);
	}
	
	protected void prepareInstance (Transformable transformable)
	{
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(transformable.getPosition(), transformable.getRotation(), transformable.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
	}
}












