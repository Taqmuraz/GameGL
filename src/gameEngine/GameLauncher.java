package gameEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.lwjgl.util.vector.Vector3f;

import components.ColliderComponent;
import components.FirstPersonCamera;
import components.FreeCamera;
import components.OrbitalCamera;
import components.PlayerMove;
import engineTester.MainGameLoop;
import entities.Camera;
import entities.Entity;
import entities.Transformable;
import models.TexturedModel;
import physicsEngine.Collider;
import physicsEngine.CubeCollider;
import physicsEngine.PlayerCollider;
import rendererEngine.MasterRenderer;
import toolbox.Maths;

public class GameLauncher {
	
	public static final GameEvent UPDATE = new GameEvent();
	public static final GameEvent START = new GameEvent();
	
	public static void main (String[] args)
	{
		MasterRenderer.setSKY_COLOR(new Vector3f(0.4f,0.6f,0.8f));
		
		GameTime.start();
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
		
		new ColliderComponent(new PlayerCollider(player));
		
		OrbitalCamera camera = new FirstPersonCamera();
		camera.setOffset(new Vector3f(0f, 2.5f, -2f));
		camera.setTarget(player);
		
		new PlayerMove (player);
		
		GameObject gObj = new GameObject(camera);
		
		TexturedModel cube = new TexturedModel("SOLDIER", "WALL");
		
		Entity entityCube = new Entity(cube, new Vector3f(405f, 0f, 400f), player.getRotation(), player.getScale());
		
		new ColliderComponent(new CubeCollider(entityCube));
	}
	
	public static void gameUpdate ()
	{
	}
}
