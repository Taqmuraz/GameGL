package gameEngine;
public class GameTime {
	
	private static float deltaTime;
	
	private static final float MILLIS_TO_SEC = 0.001f;
	
	private static long milliseconds;
	private static long startMilliseconds;
	
	public static void start ()
	{
		startMilliseconds = System.currentTimeMillis();
	}
	
	public static void begin ()
	{
		milliseconds = getMilis();
	}
	public static void end ()
	{
		deltaTime = (float)(getMilis() - milliseconds) * MILLIS_TO_SEC;
	}
	public static float getDeltaTime() {
		return deltaTime;
	}
	public static long getMilis ()
	{
		return System.currentTimeMillis() - startMilliseconds;
	}
	public static float getTime ()
	{
		return getMilis() * MILLIS_TO_SEC;
	}
}
