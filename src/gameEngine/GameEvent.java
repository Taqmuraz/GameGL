package gameEngine;

import java.util.ArrayList;
import java.util.List;

public final class GameEvent {
	private List<GameEventListener> listeners = new ArrayList<GameEventListener>();
	
	public void add (GameEventListener listener)
	{
		listeners.add(listener);
	}
	public void remove (GameEventListener listener)
	{
		listeners.remove(listener);
	}
	public void invoke ()
	{
		for (GameEventListener gel : listeners)
		{
			gel.invoke();
		}
	}
}
