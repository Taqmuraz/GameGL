package physicsEngine;

import org.lwjgl.util.vector.Vector3f;

import entities.ButtonStateControl;
import entities.InputControl;
import entities.Transformable;
import gameEngine.GameTime;
import toolbox.Maths;

public class PlayerCollider extends Collider {

	public PlayerCollider(Transformable transform) {
		super(transform);
	}

	@Override
	public boolean inContact(Collider other) {
		return false;
	}

	@Override
	public boolean isInside(Vector3f point) {
		return false;
	}

	@Override
	public Vector3f getNearestPoint(Vector3f point) {
		return transform.getPosition();
	}
	
	public void process ()
	{
		if (Maths.getWhere(getColliders(), (Collider coll) -> coll.isInside(transform.getPosition())) == null)
		{
			increaseVelocity(new Vector3f(0f, -GameTime.getDeltaTime() / (getVelocity().length() * 10f + 1f), 0f));
		} else {
			setVelocity(new Vector3f());
		}
		if (InputControl.getMouseButtonState(2) == ButtonStateControl.HOLD)
		{
			transform.increasePosition(new Vector3f(0f, GameTime.getDeltaTime() * 99.8f, 0f));
		}
		//super.process();
	}
}
