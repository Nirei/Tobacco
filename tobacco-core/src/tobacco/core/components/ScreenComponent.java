package tobacco.core.components;

import tobacco.core.util.Vector2D;

/**
 * Marks the entity that holds screen information such as width and height
 * @author nirei
 *
 */
public class ScreenComponent implements Component {

	private Vector2D screenSize;
	
	public ScreenComponent(Vector2D screenSize) {
		this.screenSize = screenSize;
	}
	
	@Override
	public String getComponentType() {
		return SCREEN_C;
	}
	
	public Vector2D getScreenSize() {
		return screenSize;
	}
	
	public void setScreenSize(Vector2D screenSize) {
		this.screenSize = screenSize;
	}
}
