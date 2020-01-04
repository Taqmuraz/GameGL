package components;

import org.lwjgl.util.vector.Vector3f;

import entities.InputControl;
import gameEngine.GameTime;
import toolbox.Maths;

public class FirstPersonCamera extends OrbitalCamera {
	public FirstPersonCamera ()
	{
		super();
	}
	
	protected void update ()
	{
		Vector3f rot = cameraTransform.getRotation();
		rot = Maths.vSum(rot, Maths.vMult(InputControl.getCAMERA_INPUT(), GameTime.getDeltaTime() * getRotSpeed()));
		rot.x = Maths.clamp(rot.x, -getMaxAngle(), getMaxAngle());
		cameraTransform.setRotation(rot);
		Vector3f offset = getTarget().getGlobal(getOffset());
		Vector3f pos = Maths.vSum(getTarget().getPosition(), offset);
		cameraTransform.setPosition(pos);
	}
}
