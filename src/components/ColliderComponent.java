package components;

import physicsEngine.Collider;

public class ColliderComponent extends Component {
	
	private Collider collider;
	
	public ColliderComponent (Collider collider)
	{
		super("Collider");
		this.collider = collider;
	}
	
	protected void update ()
	{
		collider.process();
	}
}
