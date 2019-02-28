package terrains;

import org.lwjgl.util.vector.Vector3f;

import entities.Transformable;
import models.RawModel;
import models.TexturedModel;
import models.ModelContainer;
import rendererEngine.Loader;
import textures.ModelTexture;

public class Terrain extends Transformable implements ModelContainer {
	private static float SIZE = 800f;
	private static int VERTEX_COUNT = 128;
	
	private RawModel model;
	private ModelTexture texture;
	private TexturedModel texturedModel;
	
	public Terrain (int gridX, int gridZ, Loader loader, ModelTexture texture)
	{
		super(new Vector3f(gridX * SIZE, 0f, gridZ * SIZE), new Vector3f(), 1f);
		
		this.texture = texture;
		this.setPosition(new Vector3f(gridX * SIZE, 0f, gridZ * SIZE));
		
		this.model = generateTerrain (loader);
		
		this.texturedModel = new TexturedModel (this.model, this.texture);
	}
	
	public TexturedModel getTexturedModel() {
		return texturedModel;
	}

	public static float getSIZE() {
		return SIZE;
	}

	public RawModel getRawModel() {
		return model;
	}

	public ModelTexture getTexture() {
		return texture;
	}

	public static void setSIZE(float size) {
		SIZE = size;
	}
	public static int getVERTEX_COUNT() {
		return VERTEX_COUNT;
	}
	public static void setVERTEX_COUNT(int vertexCount) {
		VERTEX_COUNT = vertexCount;
	}
	
	private RawModel generateTerrain(Loader loader){
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer*3+1] = 0;
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				normals[vertexPointer*3] = 0;
				normals[vertexPointer*3+1] = 1;
				normals[vertexPointer*3+2] = 0;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
	public void processWind ()
	{
		
	}
	public Vector3f getWIND_DIRECTION(float windForce)
	{
		return new Vector3f();
	}
}
