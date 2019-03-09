package gameEngine;

import engineTester.MainGameLoop;
import entities.Transformable;
import toolbox.Maths;

public class GameLauncher {
	
	public static final GameEvent UPDATE = new GameEvent();
	public static final GameEvent START = new GameEvent();
	
	public static void main (String[] args)
	{
		
		MainGameLoop.start();
		
		gameStart ();
		
		START.invoke();
		
		while (!MainGameLoop.haveToClose())
		{
			GameTime.begin();
			gameUpdate();
			UPDATE.invoke();
			MainGameLoop.loop();
			GameTime.end();
		}
		
		MainGameLoop.end();
	}
	public static void gameStart ()
	{
		Transformable player = MainGameLoop.createTestScene();
		OrbitalCamera camera = new OrbitalCamera();
		camera.setTarget(player);
		camera.setOffset(Maths.vUp);
	}
	public static void gameUpdate ()
	{
		
	}
}
