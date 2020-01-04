package components;

import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.InputControl;
import entities.Transformable;
import gameEngine.GameTime;
import toolbox.Maths;

public class OrbitalCamera extends Component {
	
	protected final Transformable cameraTransform;
	private Transformable target;
	private float distance = 3f;
	private float rotSpeed = 3f;
	private float maxAngle = 89f;
	private Vector3f offset = new Vector3f();

	public OrbitalCamera() {
		super("MainCamera");
		cameraTransform = Camera.getMAIN_CAMERA();
	}
	
	protected void update ()
	{
		super.update();
		
		distance += InputControl.getSCROLL_INPUT() * GameTime.getDeltaTime();
		
		if (target != null)
		{
			Vector3f rot = cameraTransform.getRotation();
			rot = Maths.vSum(rot, Maths.vMult(InputControl.getCAMERA_INPUT(), GameTime.getDeltaTime() * rotSpeed));
			rot.x = Maths.clamp(rot.x, -maxAngle, maxAngle);
			cameraTransform.setRotation(rot);
			Vector3f fwd = cameraTransform.getGlobal(Maths.vForward);
			Vector3f position = Maths.vSum(target.getPosition(), Maths.vMult(fwd, distance));
			cameraTransform.setPosition(Maths.vSum(position, offset));
		}
	}
	
	public Transformable getTarget() {
		return target;
	}

	public void setTarget(Transformable target) {
		this.target = target;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
	public Vector3f getOffset() {
		return offset;
	}

	public float getRotSpeed() {
		return rotSpeed;
	}

	public void setRotSpeed(float rotSpeed) {
		this.rotSpeed = rotSpeed;
	}

	public void setOffset(Vector3f offset) {
		this.offset = offset;
	}

	public float getMaxAngle() {
		return maxAngle;
	}

	public void setMaxAngle(float maxAngle) {
		this.maxAngle = maxAngle;
	}

	public Transformable getCameraTransformable() {
		return cameraTransform;
	}
}
