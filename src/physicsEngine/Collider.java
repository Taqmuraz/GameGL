package physicsEngine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import entities.Transformable;
import gameEngine.GameTime;
import toolbox.Maths;

public abstract class Collider {
	
	private final static List<Collider> colliders = new ArrayList<Collider>();
	
	private Vector3f velocity = new Vector3f();

	protected static List<Collider> getColliders() {
		return colliders;
	}

	protected Transformable transform;
	
	protected Collider (Transformable transform)
	{
		this.transform = transform;
		colliders.add(this);
	}
	
	public abstract boolean inContact (Collider other);
	public abstract boolean isInside (Vector3f point);
	public abstract Vector3f getNearestPoint (Vector3f point);
	
	public Vector3f getNormal (Vector3f at)
	{
		Vector3f n = Maths.vDelta(at, getTransform().getPosition());
		n.normalise(n);
		return n;
	}
	
	public void process ()
	{
		Collection<Collider> contacts = Maths.getWhereAll(getColliders(), (Collider coll) -> coll.inContact(this));
		for (Collider contact : contacts)
		{
			float k = 1f;
			Vector3f normal = new Vector3f();
			
			normal = contact.getNormal(transform.getPosition());
			
			k = Vector3f.dot(normal, velocity);
			
			
			transform.increasePosition(Maths.vMult(normal, GameTime.getDeltaTime()));
		}
	}
	
	public Transformable getTransform() {
		return transform;
	}
	
	public Vector3f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3f velocity) {
		if (velocity == null)
		{
			this.velocity = new Vector3f();
		} else {
			this.velocity = velocity;
		}
	}
	public void increaseVelocity (Vector3f delta)
	{
		this.velocity = Maths.vSum(velocity, delta);
	}
}





