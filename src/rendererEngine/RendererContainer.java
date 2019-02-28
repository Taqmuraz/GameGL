package rendererEngine;

import shaders.StaticShader;

public class RendererContainer {
	private EntityRenderer renderer;
	private StaticShader shader;
	
	public RendererContainer(EntityRenderer renderer, StaticShader shader) {
		this.renderer = renderer;
		this.shader = shader;
	}

	public EntityRenderer getRenderer() {
		return renderer;
	}

	public StaticShader getShader() {
		return shader;
	}
}
