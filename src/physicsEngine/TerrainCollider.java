package physicsEngine;

import org.lwjgl.util.vector.Vector3f;

import terrains.Terrain;

public class TerrainCollider extends Collider {

	public TerrainCollider (Terrain terrain)
	{
		super(terrain);
	}
	
	@Override
	public boolean inContact(Collider other) {
		return isInside(other.transform.getPosition());
	}

	@Override
	public boolean isInside(Vector3f point) {
		return point.y <= ((Terrain)transform).getHeight(point).y;
	}

	@Override
	public Vector3f getNearestPoint(Vector3f point) {
		point.y = ((Terrain)transform).getHeight(point).y;
		return point;
	}

	@Override
	public void process() {
	}
	public Vector3f getNormal (Vector3f at)
	{
		return new Vector3f(0f, 1f, 0f);
	}
}
