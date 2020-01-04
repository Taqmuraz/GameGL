package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.InputControl;
import entities.Light;
import entities.Transformable;
import models.ModelContainer;
import models.TexturedModel;
import rendererEngine.DisplayManager;
import rendererEngine.Loader;
import rendererEngine.MasterRenderer;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {
	
	public static Transformable createTestScene ()
	{
		TexturedModel staticModel = new TexturedModel ("SOLDIER", "SOLDIER");
		
		float waterEffect = 0.25f;
		
		//staticModel.getTexture().setWaterEffect(waterEffect);
		
		List<ModelContainer> modelContainers = new ArrayList<ModelContainer>();
		
		Terrain terrain = new Terrain (0,0, Loader.getLoader(), new ModelTexture ("GRASS", new Vector2f(10f, 10f)));
		
		
		
		terrain.getTexture().setWaterEffect(waterEffect);
		terrain.getTexture().setReflectivity(1f);
		terrain.getTexture().setShineDampen(10f);
		
		TexturedModel grassModel = new TexturedModel ("tree", "tree");
		ModelTexture grassTexture = grassModel.getTexture();
		
		grassTexture.setWindEffect(0.1f);
		
		/*Random random = new Random();
		
		for (int i = 0; i < 100; i++)
		{
			float x = random.nextFloat() * Terrain.getSIZE();
			float y = 0;//random.nextFloat() * 100;
			float z = random.nextFloat() * Terrain.getSIZE();
			
			Entity grass = new Entity(grassModel, new Vector3f(x,y,z), new Vector3f(), 10.0f);
			modelContainers.add(grass);
			//grass.getTexture().setWaterEffect(waterEffect);
		}*/
		
		modelContainers.add(terrain);
		
		Entity entity = new Entity(staticModel, new Vector3f(400f, 0f, 400f), new Vector3f (), 0.6f);
		
		modelContainers.add(entity);
		
		return entity;
	}
	
	public static void start ()
	{
		MasterRenderer.setENABLE_FOG(true);
		MasterRenderer.setFOG_GRADIENT(1f);
		MasterRenderer.setFOG_DENCITY(0.001f);
		//MasterRenderer.setSKY_COLOR(new Vector3f(0.4f, 0.4f, 0.4f));
		
		DisplayManager.createDisplay();
		
		Loader.createLoader();
		MasterRenderer.createMasterRenderer();
		
		
		new Light (new Vector3f(Terrain.getSIZE() / 2,1000f, Terrain.getSIZE() / 2), new Vector3f(1f, 1f, 1f));
		
		Camera camera = new Camera ();
		camera.setPosition(new Vector3f(400f, 2f, 400f));
	}
	public static void loop ()
	{
		
		MasterRenderer masterRenderer = MasterRenderer.getMasterRenderer();
		Camera camera = Camera.getMAIN_CAMERA();
		
		InputControl.inputUpdate();
		
		//entity.increaseRotation(new Vector3f(0f, 0.5f, 0f));
		//entity.increasePosition(new Vector3f (0f, 0f, -0.02f));
		
		for (ModelContainer mc : ModelContainer.modelContainers)
		{
			masterRenderer.processModelContainer(mc);
		}
		
		masterRenderer.render(Light.getMAIN_LIGHT(), camera);
		
		DisplayManager.updateDisplay();
	}
	public static void end ()
	{
		MasterRenderer.getMasterRenderer().cleanUp();
		Loader.getLoader().cleanUp();
		DisplayManager.closeDisplay();
	}
	public static boolean haveToClose ()
	{
		return Display.isCloseRequested();
	}
}
















