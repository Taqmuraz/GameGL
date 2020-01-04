package components;

import entities.Camera;
import entities.InputControl;
import entities.Transformable;
import gameEngine.GameTime;
import toolbox.Maths;

public class PlayerMove extends Component {

	private final Transformable player;
	
	public PlayerMove(Transformable player) {
		super("PlayerMove");
		this.player = player;
	}
	
	public void update ()
	{
		player.increasePosition(Maths.vMult(Camera.getMAIN_CAMERA().getGlobal(InputControl.getMOVE_INPUT()), GameTime.getDeltaTime()));
	}
}
