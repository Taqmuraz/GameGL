package rendererEngine;

import java.util.List;

import org.lwjgl.util.vector.Matrix4f;

import shaders.TerrainShader;
import terrains.Terrain;

public class TerrainRenderer extends EntityRenderer {
	
	public TerrainRenderer (TerrainShader shader, Matrix4f projectionMatrix)
	{
		super (shader, projectionMatrix);
	}
}
