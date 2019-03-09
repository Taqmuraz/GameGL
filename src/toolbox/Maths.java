package toolbox;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;

public class Maths {
	
	private static final Random random = new Random();
	
	public static final Vector3f vForward = new Vector3f(0f, 0f, 1f);
	public static final Vector3f vUp = new Vector3f(0f, 1f, 0f);
	public static final Vector3f vRight = new Vector3f(1f, 0f, 0f);
	
	public static Matrix4f createTransformationMatrix (Vector3f translation, Vector3f rotation, float scale)
	{
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f (1f, 0f, 0f), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.y), new Vector3f (0f, 1f, 0f), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.z), new Vector3f (0f, 0f, 1f), matrix, matrix);
		Matrix4f.scale(new Vector3f (scale, scale, scale), matrix, matrix);
		return matrix;
	}
	public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix,
                viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
    }
	
	public static float randomFloat (float min, float max)
	{
		float d = max - min;
		float resoult = min + d * random.nextFloat();
		return resoult;
	}
	public static Vector3f vSum (Vector3f a, Vector3f b)
	{
		return new Vector3f (a.x + b.x, a.y + b.y, a.z + b.z);
	}
	public static Vector3f vDelta (Vector3f a, Vector3f b)
	{
		return new Vector3f (a.x - b.x, a.y - b.y, a.z - b.z);
	}
	public static Vector3f vMult (Vector3f a, float b)
	{
		return new Vector3f(a.x * b, a.y * b, a.z * b);
	}
	public static float clamp (float origin, float min, float max)
	{
		if (origin > max)
		{
			return max;
		}
		if (origin < min)
		{
			return min;
		}
		return origin;
	}
}





