package gameEngine;
public class GameTime {
	
	private static float deltaTime;
	
	private static long milliseconds;
	
	public static void begin ()
	{
		milliseconds = System.currentTimeMillis();
	}
	public static void end ()
	{
		deltaTime = (float)(System.currentTimeMillis() - milliseconds) * 0.001f;
	}
	public static float getDeltaTime() {
		return deltaTime;
	}
}
