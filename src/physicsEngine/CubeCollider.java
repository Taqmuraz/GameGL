package physicsEngine;

import java.util.Arrays;

import org.lwjgl.util.vector.Vector3f;

import entities.Transformable;
import toolbox.Maths;
import toolbox.Predicate;

public class CubeCollider extends Collider {

	private final Vector3f[] normals =
	{
		new Vector3f(0f, 1f, 0f),
		new Vector3f(0f, 0f, 1f),
		new Vector3f(1f, 0f, 0f),
		new Vector3f(-1f, 0f, 0f),
		new Vector3f(0f, 0f, -1f),
		new Vector3f(0f, -1f, 0f)
	};
	
	public CubeCollider(Transformable transform) {
		super(transform);
	}
	public boolean inContact(Collider other) {
		return true;//isInside(other.getNearestPoint(transform.getPosition()));
	}
	public boolean isInside(Vector3f point) {
		Vector3f local = transform.getLocal(point);
		local.x = Math.abs(local.x);
		local.y = Math.abs(local.y);
		local.z = Math.abs(local.z);
		
		return local.x < 1f && local.y < 1f && local.z < 1f;
	}
	
	public void process ()
	{
		//super.process();
	}
	
	public Vector3f getNearestPoint(Vector3f point) {
		return Maths.vSum(transform.getPosition(), getNormal(point));
	}
	public Vector3f getNormal (Vector3f at)
	{
		Predicate<Vector3f, Float> predicate = (Vector3f n) -> 
		{
			n = transform.getGlobal(n);
			return Vector3f.angle(at, n);
		};
		
		return Maths.getMinimum(Arrays.asList(normals), predicate);
	}
}















