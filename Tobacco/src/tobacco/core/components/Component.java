package tobacco.core.components;

/**
 * This contain all of the game's data.
 * @author nirei
 *
 */
public interface Component {
	
	public static final int CONTAINER_C = 0;
	public static final int DEBUGGING_C = 1;

	/**
	 * Returns this component's name.
	 * @return {@link String} representing the name of the component.
	 */
	public int getComponentType();

}
