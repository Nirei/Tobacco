package tobacco.core.components;

/**
 * This contain all of the game's data.
 * @author nirei
 *
 */
public interface Component {
	
	public static final short CONTAINER_C = 0;
	public static final short DEBUGGING_C = 1;
	public static final short DRAWABLE_C = 2;
	public static final short POSITION_C = 3;
	public static final short ROTATION_C = 4;
	public static final short SCALE_C = 5;
	public static final short KEYMAP_C = 6;
	public static final short CONTROLABLE_C = 7;
	public static final short MOVEMENT_C = 8;

	/**
	 * Returns this component's name.
	 * @return {@link String} representing the name of the component.
	 */
	public short getComponentType();

}
