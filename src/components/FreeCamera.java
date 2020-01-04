package components;

import org.lwjgl.util.vector.Vector3f;

import entities.InputControl;
import gameEngine.GameTime;
import toolbox.Maths;

public class FreeCamera extends OrbitalCamera 
{
	
	public FreeCamera ()
	{
		super();
	}
	
	protected void update ()
	{
		Vector3f rot = cameraTransform.getRotation();
		rot = Maths.vSum(rot, Maths.vMult(InputControl.getCAMERA_INPUT(), GameTime.getDeltaTime() * getRotSpeed()));
		rot.x = Maths.clamp(rot.x, -getMaxAngle(), getMaxAngle());
		cameraTransform.setRotation(rot);
		Vector3f position = Maths.vSum(cameraTransform.getPosition(), Maths.vMult(cameraTransform.getGlobal(InputControl.getMOVE_INPUT()), GameTime.getDeltaTime()));
		cameraTransform.setPosition(position);
	}
	
}
