package shaders;

public class TerrainShader extends StaticShader {

	public TerrainShader ()
	{
		super();
	}
	
	protected String fragmentFile ()
	{
		return "src/shaders/gridShader.glsl";
	}
	protected String vertexFile ()
	{
		return "src/shaders/gridVertex.glsl";
	}
}





