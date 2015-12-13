package tobacco.core.components;

import tobacco.core.datatypes.GVector2D;

/**
 * Marks the entity that holds screen information such as width and height
 * @author nirei
 */
public class ScreenComponent implements Component {

	private GVector2D screenSize;
	
	public ScreenComponent() {}
	
	public ScreenComponent(GVector2D screenSize) {
		this.screenSize = screenSize;
	}
	
	@Override
	public Type getComponentType() {
		return SCREEN_C;
	}
	
	public GVector2D getScreenSize() {
		return screenSize;
	}
	
	public void setScreenSize(GVector2D screenSize) {
		this.screenSize = screenSize;
	}
	
	@Override
	public String toString() {
		return "Screen: " + screenSize;
	}
}
