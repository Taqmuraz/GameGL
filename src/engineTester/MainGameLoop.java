package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import rendererEngine.DisplayManager;
import rendererEngine.Loader;
import rendererEngine.MasterRenderer;
import textures.ModelTexture;

public class MainGameLoop {
	
	public static void main (String[] args)
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		ModelData modelData = OBJFileLoader.loadOBJ("MOSINA_T");
		RawModel model = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
		ModelTexture texture = new ModelTexture (loader.loadTexture("MOSINA"));
		TexturedModel staticModel = new TexturedModel (model, texture);
		
		texture.setShineDampen(10f);
		texture.setReflectivity(1f);
		
		
		
		List<Entity> entities = new ArrayList<Entity>();
		
		Random random = new Random();
		
		for (int i = 0; i < 100; i++)
		{
			float x = (random.nextFloat() - 0.5f) * 25;
			float y = (random.nextFloat() - 0.5f) * 25;
			float z = (random.nextFloat() - 0.5f) * 25;
			
			float rx = (random.nextFloat() - 0.5f) * 360;
			float ry = (random.nextFloat() - 0.5f) * 360;
			float rz = (random.nextFloat() - 0.5f) * 360;
			
			float s = (random.nextFloat()) * 0.3f;
			
			
			Entity entity = new Entity(staticModel, new Vector3f(x, y, z), new Vector3f (rx, ry, rz), s);
			
			entities.add(entity);
		}
		
		
		Light light = new Light (new Vector3f(0f,100f,20f), new Vector3f(1f, 1f, 1f));
		
		Camera camera = new Camera ();
		camera.setPosition(new Vector3f(0f, 1f, 0f));
		
		MasterRenderer masterRenderer = new MasterRenderer ();
		
		while (!Display.isCloseRequested())
		{
			//entity.increaseRotation(new Vector3f(0f, 0.5f, 0f));
			//entity.increasePosition(new Vector3f (0f, 0f, -0.02f));
			
			camera.move();
			
			for (Entity entity : entities)
			{
				masterRenderer.processEntity(entity);
			}
			
			masterRenderer.render(light, camera);
			
			DisplayManager.updateDisplay();
		}
		masterRenderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
