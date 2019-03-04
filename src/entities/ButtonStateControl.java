package entities;

public class ButtonStateControl
{
	public static final int NO = 0;
	public static final int DOWN = 1;
	public static final int HOLD = 2;
	public static final int UP = 3;
	
	private int currentState;
	
	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}

	public void update (boolean isActive)
	{
		switch (currentState)
		{
		case NO :
			currentState = isActive ? DOWN : NO;
			break;
		case DOWN :
			currentState = isActive ? HOLD : UP;
			break;
		case HOLD :
			currentState = isActive ? HOLD : UP;
			break;
		case UP :
			currentState = isActive ? DOWN : NO;
			break;
		}
	}
}