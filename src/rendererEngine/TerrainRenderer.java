package rendererEngine;

import java.util.List;
import java.util.Map;

import javax.swing.text.html.parser.Entity;

import org.lwjgl.util.vector.Matrix4f;

import models.ModelContainer;
import shaders.TerrainShader;
import terrains.Terrain;

public class TerrainRenderer extends EntityRenderer {
	
	public TerrainRenderer (TerrainShader shader, Matrix4f projectionMatrix)
	{
		super (shader, projectionMatrix);
	}
	public void render (Map<ModelContainer, List<ModelContainer>> entities)
	{
		super.render(entities);
	}
	public boolean haveToRender (ModelContainer entity)
	{
		return (entity instanceof Terrain);
	}
}
