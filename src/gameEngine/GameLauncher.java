package gameEngine;

import engineTester.MainGameLoop;

public class GameLauncher {
	
	public static void main (String[] args)
	{
		MainGameLoop.start();
		MainGameLoop.createTestScene();
		
		while (!MainGameLoop.haveToClose())
		{
			MainGameLoop.loop();
		}
		
		MainGameLoop.end();
	}
}
