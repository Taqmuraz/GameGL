package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.ModelContainer;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import rendererEngine.DisplayManager;
import rendererEngine.Loader;
import rendererEngine.MasterRenderer;
import shaders.TerrainShader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {
	
	public static void main (String[] args)
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		ModelData modelData = OBJFileLoader.loadOBJ("SOLDIER");
		RawModel model = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
		ModelTexture texture = new ModelTexture (loader.loadTexture("SOLDIER"));
		
		texture.setReflectivity(0f);
		texture.setShineDampen(0f);
		
		TexturedModel staticModel = new TexturedModel (model, texture);
		
		texture.setShineDampen(10f);
		texture.setReflectivity(0f);
		
		List<ModelContainer> modelContainers = new ArrayList<ModelContainer>();
		
		Terrain terrain = new Terrain (0,0, loader, new ModelTexture (loader.loadTexture("GRASS"), new Vector2f(50f, 50f)));
		Entity.setWIND_ORIGIN(new Vector3f(1f, 0f, 0f));
		
		
		
		ModelTexture grassTexture = new ModelTexture(loader.loadTexture("fern"));
		
		grassTexture.setEnableGlobalNormal(true);
		grassTexture.setEnableCulling(false);
		grassTexture.setWindEffect(0.1f);
		
		ModelData grassData = OBJFileLoader.loadOBJ("fern");
		RawModel grassRawModel = loader.loadToVAO(grassData.getVertices(), grassData.getTextureCoords(), grassData.getNormals(), grassData.getIndices());
		TexturedModel grassModel = new TexturedModel (grassRawModel, grassTexture);
		
		Random random = new Random();
		
		for (int i = 0; i < 100; i++)
		{
			float x = random.nextFloat() * Terrain.getSIZE();
			float y = 0;//random.nextFloat() * 100;
			float z = random.nextFloat() * Terrain.getSIZE();
			
			Entity grass = new Entity(grassModel, new Vector3f(x,y,z), new Vector3f(), 1.0f);
			modelContainers.add(grass);
		}
		
		modelContainers.add(terrain);
		
		Entity entity = new Entity(staticModel, new Vector3f(400f, 0f, 400f), new Vector3f (), 1.0f);
		
		modelContainers.add(entity);
		
		
		Light light = new Light (new Vector3f(Terrain.getSIZE() / 2,1000f, Terrain.getSIZE() / 2), new Vector3f(1f, 1f, 1f));
		
		Camera camera = new Camera ();
		camera.setPosition(new Vector3f(400f, 1f, 400f));
		
		MasterRenderer masterRenderer = new MasterRenderer ();
		
		while (!Display.isCloseRequested())
		{
			//entity.increaseRotation(new Vector3f(0f, 0.5f, 0f));
			//entity.increasePosition(new Vector3f (0f, 0f, -0.02f));
			
			camera.move();
			
			for (ModelContainer mc : modelContainers)
			{
				masterRenderer.processModelContainer(mc);
			}
			
			masterRenderer.render(light, camera);
			
			DisplayManager.updateDisplay();
		}
		masterRenderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
