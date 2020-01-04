package components;

import gameEngine.GameEventListener;
import gameEngine.GameLauncher;
import gameEngine.GameObject;

public abstract class Component {
	
	private String name;
	
	private final GameEventListener startListener = () -> start();
	private final GameEventListener updateListener = () -> update();

	public Component (String name)
	{
		setName(name);
		GameLauncher.START.add(startListener);
		GameLauncher.UPDATE.add(updateListener);
	}
	
	protected void start ()
	{
		
	}
	
	protected void update ()
	{
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void destroy ()
	{
		destroy(null);
	}
	
	public void destroy (GameObject owner)
	{
		GameLauncher.START.remove(startListener);
		GameLauncher.UPDATE.remove(updateListener);
		if (owner != null)
		{
			owner.removeComponent(this);
		}
	}
}
