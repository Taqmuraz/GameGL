package toolbox;
import java.util.Collection;

public class NamedContainer<T> {
	private String name;
	private T element;
	
	public NamedContainer(String name, T element) {
		super();
		this.name = name;
		this.element = element;
	}
	public String getName() {
		return name;
	}
	public T getElement() {
		return element;
	}
	
	public static <T> NamedContainer<T> hasIt (Collection<NamedContainer<T>> collection, String name)
	{
		for (NamedContainer<T> nc : collection)
		{
			if (nc.getName().equals(name))
			{
				return nc;
			}
		}
		return null;
	}
}
