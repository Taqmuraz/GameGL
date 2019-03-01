package shaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniformContainer {
	private ShaderProgram shaderProgram;
	private String uniformName;
	private int location;
	
	private static Map<ShaderProgram, List<UniformContainer>> allUniforms = new HashMap<ShaderProgram, List<UniformContainer>> ();
	
	public static List<UniformContainer> getForShader (ShaderProgram shader)
	{
		return allUniforms.getOrDefault(shader, null);
	}
	
	public UniformContainer (ShaderProgram shaderProgram, String uniformName)
	{
		this.shaderProgram = shaderProgram;
		this.uniformName = uniformName;
		List<UniformContainer> buffer = allUniforms.getOrDefault(shaderProgram, null);
		if (buffer == null)
		{
			buffer = new ArrayList<UniformContainer>();
			allUniforms.put(shaderProgram, buffer);
		}
		buffer.add(this);
	}
	public void Initialize ()
	{
		location = shaderProgram.getUniformLocation(uniformName);
	}
	public int getLocation() {
		return location;
	}

	public String getUniformName() {
		return uniformName;
	}
}
