package tobacco.core.components;

/**
 * This contain all of the game's data.
 * @author nirei
 *
 */
public interface Component {
	
	public static final Short CONTAINER = 0;

	/**
	 * Returns this component's name.
	 * @return {@link String} representing the name of the component.
	 */
	public String getComponentName();

}
