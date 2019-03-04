package models;

import objConverter.ModelData;
import objConverter.OBJFileLoader;
import rendererEngine.Loader;

public class RawModel {
	private int vaoID;
	private int vertexCount;
	
	public RawModel (int vaoID, int vertexCount)
	{
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
}
