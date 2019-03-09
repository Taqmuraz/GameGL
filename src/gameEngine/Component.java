package gameEngine;

public abstract class Component {
	
	private String name;

	public Component (String name)
	{
		setName(name);
		GameLauncher.START.add(() -> start());
		GameLauncher.UPDATE.add(() -> update());
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
}
