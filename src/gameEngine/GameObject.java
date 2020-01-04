package gameEngine;

import java.util.ArrayList;
import java.util.List;

import components.Component;

public class GameObject {
	
	private List<Component> components = new ArrayList<Component>();
	
	public GameObject (Component...components)
	{
		for (Component c : components)
		{
			this.components.add(c);
		}
	}
	
	public void addComponent (Component component)
	{
		components.add(component);
	}
	public void removeComponent (Component component)
	{
		components.remove(component);
		component.destroy();
	}
	public void destroy ()
	{
		for (Component c : components)
		{
			c.destroy();
		}
		components.clear();
	}
}
