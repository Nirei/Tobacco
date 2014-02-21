package tobacco.core.components;

/**
 * This contain all of the game's data.
 * @author nirei
 *
 */
public interface Component {
	
	public static final int CONTAINER_C = 0;
	public static final int DEBUGGING_C = 1;
	public static final int DRAWABLE_C = 2;
	public static final int POSITION_C = 3;
	public static final int ROTATION_C = 4;
	public static final int SCALE_C = 5;
	public static final int KEYMAP_C = 6;
	public static final int CONTROLABLE_C = 7;

	/**
	 * Returns this component's name.
	 * @return {@link String} representing the name of the component.
	 */
	public int getComponentType();

}
