package tobacco.core.components;

/**
 * This contain all of the game's data.
 * @author nirei
 *
 */
public interface Component {
	
	public static final String CONTAINER_C = "CONTAINER_C";
	public static final String DEBUGGING_C = "DEBUGGING_C";
	public static final String DRAWABLE_C = "DRAWABLE_C";
	public static final String DURATION_C = "DURATION_C";
	public static final String KEYMAP_C = "KEYMAP_C";
	public static final String MOVEMENT_C = "MOVEMENT_C";
	public static final String PLAYER_C = "PLAYER_C";
	public static final String POSITION_C = "POSITION_C";
	public static final String REMOVE_C = "REMOVE_C";
	public static final String ROTATION_C = "ROTATION_C";
	public static final String SCALE_C = "SCALE_C";

	/**
	 * Returns this component's name.
	 * @return {@link String} representing the name of the component.
	 */
	public String getComponentType();
	
}
