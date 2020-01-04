package entities;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import toolbox.Maths;

public abstract class Transformable {
	private Vector3f position;
	private Vector3f rotation;
	private float scale;
	
	public Transformable(Vector3f position, Vector3f rotation, float scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void increasePosition (float x, float y, float z)
	{
		this.position.x += x;
		this.position.y += y;
		this.position.z += z;
	}
	public void increasePosition (Vector3f dPosition)
	{
		if (dPosition != null)
		{
			increasePosition(dPosition.x, dPosition.y, dPosition.z);
		}
	}
	
	public void increaseRotation (float x, float y, float z)
	{
		this.rotation.x += x;
		this.rotation.y += y;
		this.rotation.z += z;
	}
	public void increaseRotation (Vector3f dRotation)
	{
		if (dRotation != null)
		{
			increaseRotation(dRotation.x, dRotation.y, dRotation.z);
		}
	}
	
	public float getRotX ()
	{
		return this.rotation.x;
	}
	public float getRotY ()
	{
		return this.rotation.y;
	}
	public float getRotZ ()
	{
		return this.rotation.z;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	private Matrix4f createMatrix ()
	{
		return Maths.createTransformationMatrix(position, rotation, scale);
	}
	public Matrix4f getMatrix ()
	{
		return createMatrix();
	}
	public void translatePosition (Vector3f direction)
	{
		increasePosition(getGlobal(direction));
	}
	public Vector3f getGlobal (Vector3f local)
	{
		Vector4f v4 = new Vector4f (local.x, local.y, local.z, 0f);
		Matrix4f mx = getMatrix();
		mx.invert();
		Matrix4f.transform(mx, v4, v4);
		return new Vector3f(v4.x, v4.y, v4.z);
	}
	public Vector3f getLocal (Vector3f global)
	{
		Vector4f v4 = new Vector4f (global.x, global.y, global.z, 0f);
		Matrix4f mx = getMatrix();
		Matrix4f.transform(mx, v4, v4);
		return new Vector3f(v4.x, v4.y, v4.z);
	}
}














